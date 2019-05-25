package com.degloba.ioc.spring.webapp;

import com.degloba.ioc.spring.InstanceFactory;
import com.degloba.ioc.spring.factory.SpringInstanceProvider;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author degloba
 * 
 * @category Listener web, que exten {@link ContextLoaderListener} d'Spring 
 */
public class DeglobaContextLoaderListener extends ContextLoaderListener {

    @Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		SpringInstanceProvider springProvider = new SpringInstanceProvider(applicationContext);
		InstanceFactory.setInstanceProvider(springProvider);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		InstanceFactory.setInstanceProvider(null);
		super.contextDestroyed(event);
	}
}
