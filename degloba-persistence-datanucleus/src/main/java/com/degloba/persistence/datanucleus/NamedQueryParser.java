package com.degloba.persistence.datanucleus;

// JPA
import javax.persistence.EntityManager;

/**
 * @author degloba
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
     * èŽ·å�–å‘½å��æŸ¥è¯¢çš„æŸ¥è¯¢å­—ç¬¦ä¸²
     * @param queryName å‘½å��æŸ¥è¯¢çš„å��å­—
     * @return å‘½å��æŸ¥è¯¢çš„æŸ¥è¯¢å­—ç¬¦ä¸²
     */
    public abstract String getQueryStringOfNamedQuery(String queryName);
    
}
