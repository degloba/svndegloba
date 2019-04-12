package com.degloba.security.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.Set;

/**
 * El mandat, el mandat de l’usuari especificat o el grup d’usuaris, com ara Joe Smith, 
 * té un reclutament de permisos d’usuari, però només a Guangzhou Branch és el mandat de "Sucursal de Guangzhou".
 */
@Entity
@Table(name = "security_authority_scopes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AuthorizationScope extends BaseAggregateRoot {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

    protected AuthorizationScope() {
    }

    public AuthorizationScope(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String[] businessKeys() {
        return new String[] {"name"};
    }

    /**
     * 获取所属的上级范围
     * @return 如果找到，返回之，否则返回null
     */
    public abstract AuthorizationScope getParent();

    /**
     * 获取下属范围
     * @return 下属范围。
     */
    public abstract Set<? extends AuthorizationScope> getChildren();

    /**
     * 是否包含指定的范围
     * @param scope 授权范围
     * @return 如果包含，返回true;否则返回false
     */
    public boolean contains(AuthorizationScope scope) {
        if (scope == null) {
            return false;
        }
        AuthorizationScope parent = scope.getParent();
        while (parent != null) {
            if (parent.equals(this)) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
}
