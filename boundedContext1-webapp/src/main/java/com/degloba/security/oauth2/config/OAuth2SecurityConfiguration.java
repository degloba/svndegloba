package com.degloba.security.oauth2.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.rst.oauth2.google.security.DefaultUserAuthenticationConverter;
import com.rst.oauth2.google.security.GoogleAccessTokenConverter;
import com.rst.oauth2.google.security.GoogleTokenServices;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableOAuth2Client
class OAuth2SecurityConfiguration {
    @Autowired
    private Environment env;

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    @Bean
    @Scope("session")
    public OAuth2ProtectedResourceDetails googleResource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("google-oauth-client");
        details.setClientId(env.getProperty("google.client.id"));
        details.setClientSecret(env.getProperty("google.client.secret"));
        details.setAccessTokenUri(env.getProperty("google.accessTokenUri"));
        details.setUserAuthorizationUri(env.getProperty("google.userAuthorizationUri"));
        details.setTokenName(env.getProperty("google.authorization.code"));
        String commaSeparatedScopes = env.getProperty("google.auth.scope");
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setPreEstablishedRedirectUri(env.getProperty("google.preestablished.redirect.url"));
        details.setUseCurrentUri(false);
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        List<String> scopes = newArrayList();
        Collections.addAll(scopes, commaSeparatedScopes.split(","));
        return scopes;
    }

    @Autowired
    private OAuth2ClientContext oauth2Context;
    
    
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestTemplate googleRestTemplate() {
        return new OAuth2RestTemplate(googleResource(), new DefaultOAuth2ClientContext(accessTokenRequest));
    }
    

    
    
    /* Spring Security OAuth2 + Google for a Spring MVC application 
     * http://jhasaket.blogspot.com.es/2014/09/securing-spring-mvc-application-using.html
     * 
     */
  /*  public OAuth2SecurityConfiguration() {
    	super(true);
    	}


    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
    .and()
    .httpBasic()
    .authenticationEntryPoint(clientAuthenticationEntryPoint())
    .and()
    .logout()
    .and()
    .authorizeRequests()
    .antMatchers("/**")
    .fullyAuthenticated()
    .and()
    .addFilterAfter(oauth2ClientContextFilter,
    ExceptionTranslationFilter.class)
    .addFilterBefore(oAuth2AuthenticationProcessingFilter(),
    FilterSecurityInterceptor.class);
    }

    @Bean
    public OAuth2ClientAuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter()
    throws Exception {
    OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
    "/oauth2callback");
    filter.setRestTemplate(googleRestTemplate());
    filter.setTokenServices(tokenServices());
    return filter;
    }

    @Bean
    public GoogleTokenServices tokenServices() {
    GoogleTokenServices tokenServices = new GoogleTokenServices();
    tokenServices
    .setCheckTokenEndpointUrl("https://www.googleapis.com/oauth2/v1/tokeninfo");
    tokenServices.setClientId(env.getProperty("google.client.id"));
    tokenServices.setClientSecret(env.getProperty("google.client.secret"));
    tokenServices.setAccessTokenConverter(googleAccessTokenConverter());
    return tokenServices;
    }

    @Bean
    public GoogleAccessTokenConverter googleAccessTokenConverter() {
    GoogleAccessTokenConverter googleAccessTokenConverter = new GoogleAccessTokenConverter();

    googleAccessTokenConverter
    .setUserTokenConverter(defaultUserAuthenticationConverter());

    return googleAccessTokenConverter;
    }

    @Bean
    public DefaultUserAuthenticationConverter defaultUserAuthenticationConverter() {
    return new DefaultUserAuthenticationConverter();
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint clientAuthenticationEntryPoint() {
    return new LoginUrlAuthenticationEntryPoint("/oauth2callback");
    }
    */
    
}
