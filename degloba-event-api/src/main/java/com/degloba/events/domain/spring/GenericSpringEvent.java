/**
 * 
 */
package com.degloba.ecommerce.vendes.application.events.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author degloba
 *
 * @category tipus d'event genèric
 */
public class GenericSpringEvent<T> extends ApplicationEvent{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T what;
    protected boolean success;
 
    public GenericSpringEvent(T what, boolean success) {
    	super(what);
    	
        this.what = what;
        this.success = success;
    }
    // ... standard getters

	public T getWhat() {
		return what;
	}

	public void setWhat(T what) {
		this.what = what;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
    
}