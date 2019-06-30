package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.Date;


/**
 * El privilegi és la classe base comuna de permisos i permisos de rol. 
 * L’autoritat es pot concedir als participants Actor (usuari de la classe base comú i usuari del grup d’usuaris)
 */
@Entity
@Table(name = "security_authorities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Authority extends BaseAggregateRoot {

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

    public String getDescripcio() {
        return description;
    }

    public void setDescripcio(String description) {
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
