package com.degloba.ioc.spring.webapp;


	import domain.support.InstanceFactory;
	import com.degloba.ioc.spring.factory.SpringInstanceProvider;
	import org.springframework.web.context.ContextLoaderListener;
	import org.springframework.web.context.WebApplicationContext;
	import org.springframework.web.context.support.WebApplicationContextUtils;

	import javax.servlet.ServletContextEvent;

	/**
	 * ä¸€ä¸ªWebç›‘å�¬å™¨ï¼Œæ‰©å±•å¹¶å�–ä»£Springçš„ContextLoaderListenerï¼Œå°†SpringIoCæ•´å�ˆåˆ°InstanceFactoryä¸­ã€‚
	 * @author yyang
	 */
	public class DeglobaContextLoaderListener extends ContextLoaderListener {
	
		@Override
		public void contextInitialized(ServletContextEvent event) {
			super.contextInitialized(event);
			WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
			SpringInstanceProvider springProvider = new SpringInstanceProvider(applicationContext);
			InstanceFactory.setInstanceProvider(springProvider);
		}

	}
