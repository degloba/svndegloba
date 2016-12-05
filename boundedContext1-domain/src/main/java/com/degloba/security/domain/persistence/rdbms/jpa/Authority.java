package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;

import java.util.Date;


/**
 * Privilege is the common base class of license Permission and roles Role. Authority can be granted to the participants Actor (common base class user and user group UserGroup User's)
 */
@Entity
@Table(name = "security_authorities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Authority extends BaseAggregateRoot {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

    private String description;

    protected Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"name"};
    }

    /**
     * Failure Permission, while failures relating to authorization information
     * @param date Expiration date
     */
    @Override
    public void disable(Date date) {
        for (Authorization authorization : Authorization.findByAuthority(this)) {
            authorization.disable(date);
        }
        super.disable(date);
    }

    /**
     * Delete permissions, and delete information relating to authorized
     */
    @Override
    public void remove() {
        for (Authorization authorization : Authorization.findByAuthority(this)) {
            authorization.remove();
        }
        super.remove();
    }

    /**
     * Get some type of Authority by name
     * @param authorityClass Authority the type
     * @param name name
     * @param <T> Authority type
     * @return If found, return to the Authority, otherwise return null
     */
    public static <T extends Authority> T getByName(Class<T> authorityClass, String name) {
        return BaseAggregateRoot.getByProperty(authorityClass, "name", name);
    }
}
