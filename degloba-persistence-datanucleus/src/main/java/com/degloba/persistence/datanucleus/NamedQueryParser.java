package com.degloba.persistence.datanucleus;

import javax.persistence.EntityManager;

/**
 * ç”¨äºŽèŽ·å�–å‘½å��æŸ¥è¯¢çš„æŸ¥è¯¢å­—ç¬¦ä¸²ã€‚ç”±äºŽJPAè§„èŒƒä¸�ç›´æŽ¥æ”¯æŒ�è¿™ä¸€åŠŸèƒ½ï¼Œæ‰€ä»¥è¦�ç”±ä½¿ç”¨JPAå®žçŽ°çš„æœ¬åœ°API
 * å®žçŽ°å®ƒã€‚
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
