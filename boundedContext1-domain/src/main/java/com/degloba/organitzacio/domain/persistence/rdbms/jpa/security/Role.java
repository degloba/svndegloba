package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import java.util.*;

/**
 * User roles. Role is a collection of Permission permission. If a group as a whole is always Permission granted a
 * Or certain Actor, compared with a defined group of Permission Role and name it, and then can grant Actor this role.
 */
@Entity
@DiscriminatorValue("ROLE")
public class Role extends Authority {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany
    @JoinTable(name = "security_role_permission")
    private Set<Permission> permissions = new HashSet<Permission>();

    protected Role() {
    }

    public Role(String name) {
        super(name);
    }

    public Role(String name, Set<Permission> permissions) {
        super(name);
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPermissions(Permission... permissions) {
        this.permissions = new HashSet<Permission>(Arrays.asList(permissions));
    }

    public boolean hasPermission(Permission permission) {
        return getPermissions().contains(permission);
    }

  /*  public static Role get(AggregateId id) {
        return get(Role.class, id);
    }*/

    public static Role getByName(String name) {
        return getByName(Role.class, name);
    }

    public static List<Role> list() {
        return findAll(Role.class);
    }

    public static Role create(String name) {
        Role role = new Role(name);
        role.save();
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role that = (Role) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Role{" + getName() + "}";
    }
}
