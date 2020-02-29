package com.degloba.ecommerce;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.util.UriComponentsBuilder;

import com.degloba.CustomXmlWebApplicationContext;
import com.degloba.ecommerce.EcommerceControllerTest.WebConfig;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class EcommerceControllerTest {

	@Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
    public void webAppContextTest() throws Exception {
    	assertTrue(webAppContext.getServletContext() instanceof MockServletContext);
    }	
    @Test
    public void getRequestTest() throws Exception {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/enviaments/enviament").build().encode().toUri();
        mockMvc.perform(get(uri)).andExpect(status().isOk());
    }	
    
    
    @EnableWebMvc
    @Configuration
    static class WebConfig extends WebMvcConfigurationSupport {
       
        @Controller
        private static class EnviamentsController {
     
            @RequestMapping(value = "/enviaments/{id}", method = RequestMethod.GET)
            @ResponseBody
            public String getCircuit(@PathVariable String id) {
                return id + " world";
            }
        }
    }
}
