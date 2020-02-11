package com.degloba.organitzacio.domain.persistence.rdbms.jpa.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.organitzacio.domain.exceptions.security.DuplicateUsernameException;
import com.degloba.organitzacio.domain.exceptions.security.PasswordUnmatchException;

import javax.persistence.*;
import java.security.Principal;
import java.util.*;

/**
 * Los usuarios del sistema. Los usuarios del sistema a menudo se corresponde con un personal
 */
@Entity
@DiscriminatorValue("USER")
public class Usuari extends Actor implements Principal {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Password
    private String password;

    //Password Hint
    private String passwordHint;

    // Si se ha bloqueado. usuarios bloqueados no se pueden volver a conectarse hasta desbloqueado
    private boolean locked = false;

    public Usuari() {
    }

    public Usuari(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"name"};
    }

    /**
     * Lock employees. Locked employees can not log back in until unlocked
     */
    public void lock() {
        this.locked = true;
        save();
    }

    /**
     * Unlock employees. Employees can log into the system after unlocking
     */
    public void unlock() {
        this.locked = false;
        save();
    }

    public void changePassword(String origPassword, String newPassword) {
        if (!matchPassword(origPassword)) {
            throw new PasswordUnmatchException();
        }
        setPassword(newPassword);
        save();
    }

    /**
     * Determine whether the password matches
     * @param password
     * @return
     */
    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public boolean unmatchPassword(String password) {
        return !matchPassword(password);
    }

    /**
     * Whether there has been the name of the judge designated system user
     * @param username Error
     * @return If it already exists returns true, false otherwise
     */
    public static boolean existsUsername(String username) {
        return getByName(username) != null;
    }

    /**
     * Se basa en el ID de usuario. Este usuario puede estar en el estado fallido.
     * @param id User ID
     * @return Si encuentra el usuario especifica se devuelve el ID de usuario, si no regreso null
     */
    /*public static User get(AggregateId id) {
        return get(User.class, id);
    }*/

    /**
     * Get the user based on the user name. This user may be in the failed state.
     * @param name username
     * @return If you find the name of the specified user is returned to the user, otherwise return null
     */
    public static Usuari getByName(String name) {
        return getByName(Usuari.class, name);
    }

    /**
     * Lists all system users
     * @return All users of the system users
     */
    public static List<Usuari> list() {
        return findAll(Usuari.class);
    }

    /**
     * Verify the validity of the user based on user name and password provided
     * @param username username
     * @param password Password
     * @return If the user exists, passwords match, and has not expired or locked, the validation is successful, returns true; otherwise the validation fails, returns false
     */
    public static boolean authenticate(String username, String password) {
        Usuari usuari = getByName(username);
        if (usuari == null || usuari.isDisabled() || usuari.isLocked()) {
            return false;
        }
        return usuari.matchPassword(password);
    }

    public static Usuari create(String username) {
        return create(username, "");
    }

    public static Usuari create(String username, String password) {
        if (existsUsername(username)) {
            throw new DuplicateUsernameException();
        }
        Usuari usuari = new Usuari(username, password);
        usuari.save();
        return usuari;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 23).append(getName()).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Usuari)) {
            return false;
        }
        Usuari that = (Usuari) other;
        return new EqualsBuilder().append(this.getName(), that.getName()).isEquals();
    }

    @Override
    public String toString() {
        return getName();
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}