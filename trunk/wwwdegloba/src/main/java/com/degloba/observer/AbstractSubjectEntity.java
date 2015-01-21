package com.degloba.observer;

import java.util.List;

import javax.persistence.MappedSuperclass;

import domain.support.AbstractEntity;

@SuppressWarnings("unchecked")
@MappedSuperclass
public abstract class AbstractSubjectEntity extends AbstractEntity implements
        Subject {

    /**
     *
     */
    private static final long serialVersionUID = 8696877724719553088L;

    /**
     * èŽ·å¾—ä¸»é¢˜æ ‡è¯†ã€‚é»˜è®¤è¿”å›žä¸»é¢˜çš„ç±»å��ã€‚
     *
     * @return
     */
    public String getSubjectKey() {
        return getClass().getName();
    }

    @Override
    public List<Observer> getObservers() {
        // æ¯�æ¬¡æŸ¥æ‰¾æœ€æ–°çš„è§‚å¯Ÿè€…ï¼Œå°†æ�¥è€ƒè™‘åŠ å…¥ç¼“å­˜æœºåˆ¶
        return Observer.findBySubject(this);

    }

    @Override
    public void notifyObservers() {
        List<Observer> observers = getObservers();
        for (Observer observer : observers) {
            observer.process(this);
        }
    }

}

