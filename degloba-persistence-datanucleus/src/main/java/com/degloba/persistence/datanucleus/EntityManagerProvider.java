package com.degloba.persistence.datanucleus;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.degloba.domain.IocInstanceNotFoundException;
import com.degloba.domain.InstanceFactory;


/**
 * JPA å®žä½“ç®¡ç�†å™¨æ��ä¾›è€…ã€‚å¦‚æžœå½“å‰�çº¿ç¨‹ä¸­å°šæœªå­˜åœ¨entityManagerçº¿ç¨‹å�˜é‡�ï¼Œåˆ™ä»ŽIoCå®¹å™¨ä¸­èŽ·å�–ä¸€ä¸ªå¹¶å­˜å…¥å½“å‰�çº¿ç¨‹ï¼Œ
 * å¦‚æžœå½“å‰�çº¿ç¨‹å·²ç»�å­˜åœ¨entityManagerçº¿ç¨‹å�˜é‡�ï¼Œç›´æŽ¥è¿”å›žã€‚
 * <p>
 * æœ¬ç±»çš„å­˜åœ¨ï¼Œä¸»è¦�æ˜¯ä¸ºäº†åœ¨å½“å‰�çº¿ç¨‹ä¸­ï¼Œæ¯�æ¬¡è¯·æ±‚éƒ½è¿”å›žç›¸å�Œçš„entityManagerå¯¹è±¡ã€‚é�¿å…�äº‹åŠ¡å’Œâ€œä¼šè¯�å·²å…³é—­â€�é—®é¢˜ã€‚
 *
 * @author yyang
 */
public class EntityManagerProvider {

    private EntityManagerFactory entityManagerFactory;

    private final ThreadLocal<EntityManager> entityManagerHolder = new ThreadLocal<EntityManager>();

    public EntityManagerProvider() {
        entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
    }

    public EntityManagerProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerProvider(EntityManager entityManager) {
        entityManagerHolder.set(entityManager);
    }

    public EntityManager getEntityManager() {
        EntityManager result = entityManagerHolder.get();
        if (result != null && result.isOpen()) {
            return result;
        }
        result = getEntityManagerFromIoC();
        entityManagerHolder.set(result);
        return result;
    }

    public EntityManager getEntityManagerFromIoC() {
        try {
            return InstanceFactory.getInstance(EntityManager.class);
        } catch (IocInstanceNotFoundException e) {
            if (entityManagerFactory == null) {
                entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
            }
            return entityManagerFactory.createEntityManager();
        }
    }
}

