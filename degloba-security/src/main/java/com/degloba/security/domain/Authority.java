package com.degloba.security.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.degloba.domain.persistence.rdbms.jpa.AbstractEntity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Privilege is the common base class of license Permission and roles Role. Authority can be granted to the participants Actor (common base class user and user group UserGroup User's)
 */
@Entity
@Table(name = "security_authorities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Authority extends AbstractEntity {

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
     * 根据名字获取某种类型的Authority
     * @param authorityClass Authority的类
     * @param name 名称
     * @param <T> Authority的类型
     * @return 如果找到，返回该Authority，否则返回null
     */
    public static <T extends Authority> T getByName(Class<T> authorityClass, String name) {
        return AbstractEntity.getByProperty(authorityClass, "name", name);
    }
}
