package com.degloba.persistence.jpa;

import javax.persistence.EntityManager;

/**
 * Get named query query strings. Because JPA specification does not directly support this feature, 
 * so it is achieved by the use of local JPA API
 * Achieve it.
 */
public abstract class NamedQueryParser {
    
    private EntityManagerProvider entityManagerProvider;

    public NamedQueryParser() {
    }

    public NamedQueryParser(EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    public void setEntityManagerProvider(EntityManagerProvider entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }
    
    /**
     * Get Named query string query
     * @param queryName Named query's name
     * @return Named query string query
     */
    public abstract String getQueryStringOfNamedQuery(String queryName);
    
}
