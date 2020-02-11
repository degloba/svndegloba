package com.degloba;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.google.appengine.api.utils.SystemProperty;

/**
 * 
 * @author degloba
 * 
 * @category el {@link ContextLoaderListener} per defecte utilitza {@link XmlWebApplicationContext} per 
 * la carga del contexte. 
 * El {@link XmlWebApplicationContext} busca les configuracions en WEB-INF/applicationContext.xml. 
 * Si nuestro archivo XML de configuraciones no esta en esa ruta o no se llama igual, debemos especificar 
 * su nombre y ubicación de lo contrario Spring nos da un error.
 *
 */
public class CustomXmlWebApplicationContext extends XmlWebApplicationContext {
	
    protected void initBeanDefinitionReader(XmlBeanDefinitionReader beanDefinitionReader) {
              super.initBeanDefinitionReader(beanDefinitionReader);
              if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                beanDefinitionReader.setValidating(false);
                beanDefinitionReader.setNamespaceAware(true);
              }
          }
}
