package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.*;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.*;

/**
 * Participants, is the common base class user roles User and Role, authorization is granted to a participant a permission
 */
@Entity
@Table(name = "security_actors")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Actor extends BaseAggregateRoot {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//first name
    private String name;

    //Explanation
    private String remark;

    @ManyToMany(mappedBy = "members")
    private Set<UserGroup> parentGroups = new HashSet<UserGroup>();

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<UserGroup> getParentGroups() {
        return parentGroups;
    }

    public void addParentGroup(UserGroup group) {
        parentGroups.add(group);
    }

    public void removeParentGroup(UserGroup group) {
        parentGroups.remove(group);
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"name"};
    }

    /**
     * Get all mandates, so that you can obtain a grant within the current range of each actor's roles and permissions
     * @return All of the current mandate of the actor
     */
    public Set<AuthorizationScope> getScopes() {
        Set<AuthorizationScope> results = new HashSet<AuthorizationScope>();
        for (Authorization authorization : Authorization.findByActor(this)) {
            results.add(authorization.getScope());
        }
        for (UserGroup group : getParentGroups()) {
            results.addAll(group.getScopes());
        }
        return results;
    }

    /*=====================================Global scope=============================*/

    /**
     * Granted new powers
     * @param authorities The powers to be granted (Role or Permission)
     */
    public void grantAuthorities(Authority... authorities) {
        grantAuthorities(GlobalAuthorizationScope.get(), authorities);
    }

    /**
     * Revocation of existing power
     * @param authorities To revoke the powers (Role or Permission)
     */
    public void withdrawAuthorities(Authority... authorities) {
        withdrawAuthorities(GlobalAuthorizationScope.get(), authorities);
    }

    /**
     * Setting role in the global scope Actor direct grant. Earlier roles have been granted if not within this range is automatically revoked
     * @param roles The power to set
     */
    public void setRoles(Role... roles) {
        setRoles(GlobalAuthorizationScope.get(), roles);
    }

    /**
     * Get Set directly granted Actor's role in the global scope, excluding inherited from user groups and user groups all superior user group roles.
     * @return All the user's role
     */
    public Set<Role> getRoles() {
        return getRoles(GlobalAuthorizationScope.get());
    }

    /**
     * Get all roles Actor owned globally, including inherited from user groups and user groups all superior user group roles.
     * @return All the user's role
     */
    public Set<Role> getAllRoles() {
        return getAllRoles(GlobalAuthorizationScope.get());
    }

    /**
     * Actor determine whether it has global scope within the specified role
     * @param role Character
     * @return If the Actor has a role, it returns true; otherwise false
     */
    public boolean hasRole(Role role) {
        return getAllRoles(GlobalAuthorizationScope.get()).contains(role);
    }

    /**
     * Set permissions granted directly Actor in a global scope. Prior permission has been granted, if not within this range is automatically revoked
     * @param permissions To set permissions
     */
    public void setPermissions(Set<Permission> permissions) {
        setPermissions(GlobalAuthorizationScope.get(), permissions);
    }

    /**
     * Get set permissions granted directly Actor in a global scope, excluding inherited from roles and user group permissions
     * @return All the user has permissions
     */
    public Set<Permission> getPermissions() {
        return getPermissions(GlobalAuthorizationScope.get());
    }

    /**
     * Get full access globally owned by the user, including the role inherited from user groups and permissions
     * @return All the user has permissions
     */
    public Set<Permission> getAllPermissions() {
        return getAllPermissions(GlobalAuthorizationScope.get());
    }

    /**
     * Analyzing Actor globally whether it has the specified permission
     * @param permission Competence
     * @return If you have specific permission to return true, otherwise false
     */
    public boolean hasPermission(Permission permission) {
        return getAllPermissions(GlobalAuthorizationScope.get()).contains(permission);
    }

    /*=====================================With a range of=============================*/

    public void grantAuthorities(AuthorizationScope scope, Authority... authorities) {
        for (Authority authority : authorities) {
            Authorization.grantAuthority(this, authority, scope);
        }
    }

    public void withdrawAuthorities(AuthorizationScope scope, Authority... authorities) {
        for (Authority authority : authorities) {
            Authorization.withdrawAuthority(this, authority, scope);
        }
    }

    public void setRoles(AuthorizationScope scope, Role... roles) {
        setRoles(scope, new HashSet<Role>(Arrays.asList(roles)));
    }

    public void setRoles(AuthorizationScope scope, Set<Role> roles) {
        for (Authorization authorization : Authorization.findByActor(this, scope)) {
            if (!roles.contains(authorization.getAuthority())) {
                authorization.remove();
            }
        }
        for (Role role : roles) {
            Authorization.grantAuthority(this, role, scope);
        }
    }

    public Set<Role> getRoles(AuthorizationScope scope) {
        return Authorization.getAuthoritiesOfActor(this, scope, Role.class);
    }

    public Set<Role> getAllRoles(AuthorizationScope scope) {
        Set<Role> results = new HashSet<Role>();
        results.addAll(getRoles(scope));
        for (UserGroup group : getParentGroups()) {
            results.addAll(group.getAllRoles(scope));
        }
        return results;
    }

    public boolean hasRole(Role role, AuthorizationScope scope) {
        return getAllRoles(scope).contains(role);
    }

    public void setPermissions(AuthorizationScope scope, Permission... permissions) {
        setPermissions(scope, new HashSet<Permission>(Arrays.asList(permissions)));
    }

    public void setPermissions(AuthorizationScope scope, Set<Permission> permissions) {
        for (Authorization authorization : Authorization.findByActor(this, scope)) {
            if (!permissions.contains(authorization.getAuthority())) {
                authorization.remove();
            }
        }
        for (Permission permission : permissions) {
            Authorization.grantAuthority(this, permission, scope);
        }
    }

    public Set<Permission> getPermissions(AuthorizationScope scope) {
        return Authorization.getAuthoritiesOfActor(this, scope, Permission.class);
    }

    public Set<Permission> getAllPermissions(AuthorizationScope scope) {
        Set<Permission> results = new HashSet<Permission>();
        results.addAll(getPermissions(scope));
        for (Role role : getRoles(scope)) {
            results.addAll(role.getPermissions());
        }
        for (UserGroup group : getParentGroups()) {
            results.addAll(group.getAllPermissions(scope));
        }
        return results;
    }

    /**
     * Determine whether it has the specified permission
     * @param permission Competence
     * @return If you have specific permission to return true, otherwise false
     */
    public boolean hasPermission(Permission permission, AuthorizationScope scope) {
        return getAllPermissions(scope).contains(permission);
    }

    /**
     * Failure participants invalidated authorization information it participates
     * @param date Expiration date
     */
   /* @Override
    public void disable(Date date) {
        for (Authorization authorization : Authorization.findByActor(this)) {
            authorization.disable(date);
        }
        for (UserGroup group : getParentGroups()) {
            group.removeMember(this);
        }
        super.disable(date);
    }*/

    /**
     * Remove participants, and remove the authorization of their participation
     */
    @Override
    public void remove() {
        for (Authorization authorization : Authorization.findByActor(this)) {
            authorization.remove();
        }
        for (UserGroup group : getParentGroups()) {
            group.removeMember(this);
        }
        super.remove();
    }

    /**
     * Get some type of Actor by name
     * @param actorClass Actor the type
     * @param name name
     * @param <T> Actor type
     * @return If found, return to the Actor, otherwise return null
     */
    public static <T extends Actor> T getByName(Class<T> actorClass, String name) {
        return BaseAggregateRoot.getByProperty(actorClass, "name", name);
    }
}
