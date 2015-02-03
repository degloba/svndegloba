package com.degloba.ioc.spring.webapp;


import com.degloba.domain.InstanceFactory;
import com.degloba.ioc.spring.factory.SpringInstanceProvider;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yyang on 14-7-1.
 */
public class IocInitializeListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
   /* @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext applicationContext = WebApplicationContextUtils.
                getRequiredWebApplicationContext(servletContextEvent.getServletContext());
        SpringInstanceProvider springProvider = new SpringInstanceProvider(applicationContext);
        InstanceFactory.setInstanceProvider(springProvider);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        InstanceFactory.setInstanceProvider(null);
    }*/
}