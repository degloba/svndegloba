package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.*;
import java.util.*;

/**
 * License, a representative of the system fine-grained access functionality. Or less precisely, on behalf of a specific system functions
 */
@Entity
@DiscriminatorValue("PERM")
public class Permission extends Authority {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Permission() {
    }

    public Permission(String name) {
        super(name);
    }

    public static Permission create(String name) {
        Permission permission = new Permission(name);
        permission.save();
        return permission;
    }

    public static Permission get(long id) {
        return get(Permission.class, id);
    }

    public static Permission getByName(String name) {
        return getByName(Permission.class, name);
    }

    public static List<Permission> list() {
        return findAll(Permission.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Permission)) {
            return false;
        }
        Permission that = (Permission) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Permission{" + getName() + "}";
    }
}
