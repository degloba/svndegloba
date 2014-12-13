package com.degloba.ioc.spring.factory;


import domain.support.InstanceProvider;
import domain.sharedkernel.exceptions.IocInstanceNotUniqueException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * å®žä¾‹æ��ä¾›è€…æŽ¥å�£çš„Springå®žçŽ°ã€‚
 * SpringProviderå†…éƒ¨é€šè¿‡Spring IoCçš„ApplicationContextå®žçŽ°å¯¹è±¡åˆ›å»ºã€‚
 *
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 */
public class SpringInstanceProvider implements InstanceProvider {

    private ApplicationContext applicationContext;

    /**
     * ä»¥ä¸€æ‰¹springé…�ç½®æ–‡ä»¶çš„è·¯å¾„åˆ�å§‹åŒ–springå®žä¾‹æ��ä¾›è€…ã€‚
     *
     * @param locations springé…�ç½®æ–‡ä»¶çš„è·¯å¾„çš„é›†å�ˆã€‚springå°†ä»Žç±»è·¯å¾„å¼€å§‹èŽ·å�–è¿™æ‰¹èµ„æº�æ–‡ä»¶ã€‚
     */
    public SpringInstanceProvider(String... locations) {
        applicationContext = new ClassPathXmlApplicationContext(locations);
    }

    /**
     * ä»ŽApplicationContextç”Ÿæˆ�SpringProvider
     *
     * @param applicationContext
     */
    public SpringInstanceProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * æ ¹æ�®ä¸€æ‰¹Springé…�ç½®æ–‡ä»¶åˆ�å§‹åŒ–springå®žä¾‹æ��ä¾›è€…ã€‚
     *
     * @param annotatedClasses
     */
    public SpringInstanceProvider(Class<?>... annotatedClasses) {
        applicationContext = new AnnotationConfigApplicationContext(annotatedClasses);
    }

    /**
     * æ ¹æ�®ç±»åž‹èŽ·å�–å¯¹è±¡å®žä¾‹ã€‚è¿”å›žçš„å¯¹è±¡å®žä¾‹æ‰€å±žçš„ç±»æ˜¯Tæˆ–å®ƒçš„å®žçŽ°ç±»æˆ–å­�ç±»ã€‚å¦‚æžœæ‰¾ä¸�åˆ°è¯¥ç±»åž‹çš„å®žä¾‹åˆ™è¿”å›žnullã€‚
     * å¦‚æžœæœ‰éƒ¨ç½²äº†å¤šä¸ªç±»åž‹ä¸ºTçš„Beanåˆ™æŠ›å‡ºNoUniqueBeanDefinitionExceptionå¼‚å¸¸ã€‚
     *
     * @param <T>      ç±»åž‹å�‚æ•°
     * @param beanType å®žä¾‹çš„ç±»åž‹
     * @return æŒ‡å®šç±»åž‹çš„å®žä¾‹ã€‚
     */
    @Override
    public <T> T getInstance(Class<T> beanType) {
        try {
            return applicationContext.getBean(beanType);
        } catch (NoUniqueBeanDefinitionException e) {
            throw new IocInstanceNotUniqueException(e);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }

    /**
     * æ ¹æ�®ç±»åž‹å’ŒBean idèŽ·å�–å¯¹è±¡å®žä¾‹ã€‚å¦‚æžœæ‰¾ä¸�åˆ°è¯¥ç±»åž‹çš„å®žä¾‹åˆ™è¿”å›žnullã€‚
     * å�‡å¦‚æœ‰ä¸¤ä¸ªç±»MyService1å’ŒMyService2éƒ½å®žçŽ°äº†æŽ¥å�£Serviceï¼Œåœ¨applicationContextä¸­è¿™æ ·éƒ¨ç½²ï¼š
     * <blockquote>
     * <pre>
     * <bean id="service1" class="MyService1"/>
     * <bean id="service2" class="MyService2"/>
     * </pre>
     * </blockquote>
     * æˆ–è€…ä»¥é…�ç½®ç±»çš„æ–¹å¼�éƒ¨ç½²ï¼š
     * <blockquote>
     * <pre>
     *
     * @param <T>      ç±»åž‹å�‚æ•°
     * @param beanName å®žçŽ°ç±»åœ¨å®¹å™¨ä¸­é…�ç½®çš„å��å­—
     * @param beanType å®žä¾‹çš„ç±»åž‹
     * @return æŒ‡å®šç±»åž‹çš„å®žä¾‹ã€‚
     * @Configuration public class SpringConfiguration {
     * <p/>
     * @Bean(name = "service1")
     * public Service service1() {
     * return new MyService1();
     * }
     * <p/>
     * @Bean(name = "service2")
     * public Service service2() {
     * return new MyService2();
     * }
     * }
     * </pre>
     * </blockquote>
     * é‚£ä¹ˆgetInstance(Service.class, "service2")å°†è¿”å›žMyService2çš„å®žä¾‹ã€‚
     */
    @Override
    public <T> T getInstance(Class<T> beanType, String beanName) {
        try {
            return (T) applicationContext.getBean(beanName, beanType);
        } catch (NoUniqueBeanDefinitionException e) {
            throw new IocInstanceNotUniqueException(e);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }


    /**
     * æ ¹æ�®ç±»åž‹å’ŒAnnotationèŽ·å�–å¯¹è±¡å®žä¾‹ã€‚å¦‚æžœæ‰¾ä¸�åˆ°è¯¥ç±»åž‹çš„å®žä¾‹åˆ™è¿”å›žnullã€‚
     * å�‡å¦‚æœ‰ä¸¤ä¸ªç±»MyService1å’ŒMyService2éƒ½å®žçŽ°äº†æŽ¥å�£Serviceï¼Œå…¶ä¸­MyService2æ ‡è®°ä¸º
     * TheAnnotationï¼Œé‚£ä¹ˆgetInstance(Service.class, TheAnnotation.class)å°†è¿”å›ž
     * MyService2çš„å®žä¾‹ã€‚
     *
     * @param <T>            ç±»åž‹å�‚æ•°
     * @param beanType       å®žä¾‹çš„ç±»åž‹
     * @param annotationType å®žçŽ°ç±»çš„annotationç±»åž‹
     * @return æŒ‡å®šç±»åž‹çš„å®žä¾‹ã€‚
     */
    @Override
    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            return getInstance(beanType);
        }
        Map<String, T> results = applicationContext.getBeansOfType(beanType);
        List<T> resultsWithAnnotation = new ArrayList<T>();
        for (Map.Entry<String, T> entry : results.entrySet()) {
            if (applicationContext.findAnnotationOnBean(entry.getKey(), annotationType) != null) {
                resultsWithAnnotation.add(entry.getValue());
            }
        }
        if (resultsWithAnnotation.isEmpty()) {
            return null;
        }
        if (resultsWithAnnotation.size() == 1) {
            return resultsWithAnnotation.get(0);
        }
        throw new IocInstanceNotUniqueException();
    }

    @SuppressWarnings("unchecked")
    public <T> T getByBeanName(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }
}
