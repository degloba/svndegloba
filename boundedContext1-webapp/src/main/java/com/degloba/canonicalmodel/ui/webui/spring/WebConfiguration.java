package com.degloba.canonicalmodel.ui.webui.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.js.ajax.AjaxUrlBasedViewResolver;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

// Domini
import com.degloba.travel.*;
import com.degloba.travel.domain.Amenity;
import com.degloba.travel.domain.Booking;
import com.degloba.travel.domain.Bookings;
import com.degloba.travel.domain.Hotel;
import com.degloba.travel.domain.Hotels;
import com.degloba.travel.domain.User;
import com.degloba.travel.ui.webui.spring.webflow.BookingFlowHandler;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.mvc.view.FlowAjaxTilesView;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Sets up all artifacts related to the web
 */
@Configuration
@EnableWebMvc
//@Import(RestConfiguration.class)
@ComponentScan({"com.degloba.travel.rest", "com.degloba.travel.ui.webui.spring.controller", "com.degloba.travel.ui.webui.spring.webflow" })
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Inject
    private FlowDefinitionRegistry flowDefinitionRegistry;

    @Inject
    private FlowExecutor flowExecutor;

    private Class[] jaxbClasses = {Hotels.class, Bookings.class, Amenity.class, Booking.class, User.class, Hotel.class};

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // json support
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJacksonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(mappingJacksonHttpMessageConverter);

        // jaxb support
        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter(this.marshaller());
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
        converters.add(converter);
    }

    @Bean
    public Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(this.jaxbClasses);
        return marshaller;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users/login");
        registry.addViewController("/users/logout");
        registry.addViewController("/users/logoutSuccess");
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "hotels/booking")
    public BookingFlowHandler bookingFlowHandler() {
        return new BookingFlowHandler();
    }

    @Bean
    public AjaxUrlBasedViewResolver ajaxUrlBasedViewResolver() {
        AjaxUrlBasedViewResolver aubvr = new AjaxUrlBasedViewResolver();
        aubvr.setViewClass(FlowAjaxTilesView.class);
        return aubvr;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF*//**//*tiles.xml"});
        return tilesConfigurer;
    }

    @Bean
    public FlowHandlerMapping mapping() {
        FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
        flowHandlerMapping.setOrder(-1);
        flowHandlerMapping.setFlowRegistry(flowDefinitionRegistry);
        return flowHandlerMapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        FlowHandlerAdapter fha = new FlowHandlerAdapter();
        fha.setFlowExecutor(flowExecutor);
        return fha;
    }

    @Bean
    public MvcViewFactoryCreator viewFactoryCreator() {
        MvcViewFactoryCreator mvcViewFactoryCreator = new MvcViewFactoryCreator();
        //mvcViewFactoryCreator.setViewResolvers(Arrays.asList(ajaxUrlBasedViewResolver()));
        return mvcViewFactoryCreator;
    }


    @Bean
    public SecurityFlowExecutionListener securityFlowExecutionListener() {
        return new SecurityFlowExecutionListener();
    }
}
