package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Default mandate. Record in the database contains a range with the inclusion relation.
 */
@Entity
@DiscriminatorValue("DEFAULT")
public class DefaultAuthorizationScope extends AuthorizationScope {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinTable(name = "security_authorization_scope_relationship",
        joinColumns = @JoinColumn(name = "parent_id"),
        inverseJoinColumns = @JoinColumn(name = "child_id"))
    private DefaultAuthorizationScope parent;

    @OneToMany(mappedBy = "parent")
    private Set<DefaultAuthorizationScope> children = new HashSet<DefaultAuthorizationScope>();

    public DefaultAuthorizationScope() {
    }

    public DefaultAuthorizationScope(String name) {
        super(name);
    }

    @Override
    public AuthorizationScope getParent() {
        return parent;
    }

    @Override
    public Set<DefaultAuthorizationScope> getChildren() {
        return children;
    }

    public void addChildren(DefaultAuthorizationScope... scopes) {
        for (DefaultAuthorizationScope scope : scopes) {
            if (contains(scope)) {
                continue;
            }
            scope.parent = this;
            scope.save();
            children.add(scope);
        }
    }

    public void removeChildren(DefaultAuthorizationScope... scopes) {
        Date now = new Date();
        for (DefaultAuthorizationScope scope : scopes) {
            if (!children.contains(scope)) {
                continue;
            }
            scope.remove();
            children.remove(scope);
        }
    }

    public DefaultAuthorizationScope createChild(String name) {
        DefaultAuthorizationScope child = new DefaultAuthorizationScope(name);
        addChildren(child);
        return child;
    }

    public static DefaultAuthorizationScope create(String scopeName) {
        DefaultAuthorizationScope scope = new DefaultAuthorizationScope(scopeName);
        scope.save();
        return scope;
    }
}
