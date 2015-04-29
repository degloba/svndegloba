package com.degloba.ioc.spring.webapp;

import com.degloba.domain.InstanceFactory;
import com.degloba.ioc.spring.factory.SpringInstanceProvider;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * A Web listener, expand and replace the Spring of ContextLoaderListener, 
 * will be integrated into InstanceFactory in SpringIoC.
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
