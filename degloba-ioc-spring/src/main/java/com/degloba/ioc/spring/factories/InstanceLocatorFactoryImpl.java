package com.degloba.ioc.spring.factories;

import java.util.Map;

import com.degloba.ioc.MapInstanceLocator;
import com.degloba.ioc.ServiceLoaderInstanceLocator;
import com.degloba.ioc.spring.locators.IInstanceLocator;
import com.degloba.ioc.spring.locators.InstanceProviderInstanceLocator;
import com.degloba.ioc.spring.providers.IInstanceProvider;


/**
 * @author degloba
 * 
 * @category Defineix un cercador d'instancies java</br>
 * <b>Observació</b> : Crea un cercador a partir d'un {@link ServiceLoaderInstanceLocator}
 * 
 */
public class InstanceLocatorFactoryImpl implements IInstanceLocatorFactory {
	
    public IInstanceLocator create(IInstanceProvider instanceProvider) {
        return new InstanceProviderInstanceLocator(instanceProvider);
    }

    /**
     * Retorna un cercador d'instàncies IOC (pot ser qualsevol)
     */
    public IInstanceLocator createByServiceLoader() {
        return new ServiceLoaderInstanceLocator();
    }

    public IInstanceLocator create(Map<Object, Object> instances) {
        return new MapInstanceLocator(instances);
    }
}
