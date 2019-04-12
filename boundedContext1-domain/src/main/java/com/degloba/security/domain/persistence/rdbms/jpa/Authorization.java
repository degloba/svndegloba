package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.security.domain.exceptions.DuplicateAuthorizationException;

import java.util.*;

/**
 * Authorization information recording permissions granted to participants
 */
@Entity
@Table(name = "security_authorizations")
public class Authorization extends BaseAggregateRoot {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Participants
    @ManyToOne
    private Actor actor;

    //Competence
    @ManyToOne
    private Authority authority;

    //Mandate
    @ManyToOne
    private AuthorizationScope scope;

    protected Authorization() {
    }

    public Authorization(Actor actor, Authority authority) {
        this(actor, authority, GlobalAuthorizationScope.get());
    }

    public Authorization(Actor actor, Authority authority, AuthorizationScope scope) {
        this.actor = actor;
        this.authority = authority;
        this.scope = scope;
    }

    public Actor getActor() {
        return actor;
    }

    public Authority getAuthority() {
        return authority;
    }

    public AuthorizationScope getScope() {
        return scope;
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"actor", "authority", "scope"};
    }

    /**
     * According to the participants to find licensing information
     * @param actor Participants
     * @return All valid authorization information participants
     */
    static List<Authorization> findByActor(Actor actor) {
        return getRepository().createCriteriaQuery(Authorization.class)
                .eq("actor", actor)
                .eq("disabled", false)
                .list();
    }

    static List<Authorization> findByActor(Actor actor, AuthorizationScope scope) {
        return getRepository().createCriteriaQuery(Authorization.class)
                .eq("actor", actor)
                .eq("scope", scope)
                .eq("disabled", false)
                .list();
    }

    /**
     * Find authorization information based on the permissions
     * @param authority Competence
     * @return All valid authorization information rights
     */
    static List<Authorization> findByAuthority(Authority authority) {
        return getRepository().createCriteriaQuery(Authorization.class)
                .eq("authority", authority)
                .eq("disabled", false)
                .list();
    }

    /**
     * Find all the powers to be granted within a specified range of the specified participants of the specified type
     * @param actor Participants
     * @param scope Mandate
     * @param authorityClass Class power
     * @param <T> Type of power
     * @return The participants set permissions
     */
	static <T extends Authority> Set<T> getAuthoritiesOfActor(Actor actor, AuthorizationScope scope, Class<T> authorityClass) {
        Set<T> results = new HashSet<T>();
        for (Authorization authorization : findByActor(actor, scope)) {
            Authority authority = authorization.getAuthority();
            if (authorityClass.isInstance(authority)) {
                results.add((T) authority);
            }
        }
        return results;
    }

    static Authorization get(Actor actor, Authority authority, AuthorizationScope scope) {
        return getRepository().createCriteriaQuery(Authorization.class)
                .eq("actor", actor)
                .eq("authority", authority)
                .eq("scope", scope)
                .eq("disabled", false)
                .singleResult();
    }

    static void grantAuthority(Actor actor, Authority authority, AuthorizationScope scope) {
        if (Authorization.get(actor, authority, scope) != null) {
            throw new DuplicateAuthorizationException();
        }
        new Authorization(actor, authority, scope).save();
    }

    static void withdrawAuthority(Actor actor, Authority authority, AuthorizationScope scope) {
        Authorization authorization = Authorization.get(actor, authority, scope);
        if (authorization != null) {
            authorization.remove();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authorization)) {
            return false;
        }
        Authorization that = (Authorization) o;
        return Objects.equals(getActor(), that.getActor()) &&
                Objects.equals(getAuthority(), that.getAuthority()) &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActor(), getAuthority(), scope);
    }
}
