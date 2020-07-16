package com.degloba.hotels.webapp.reactive.security;

import java.util.Collection;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.degloba.hotels.webapp.reactive.actuator.FeaturesEndpoint;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .pathMatchers("/", "/admin")
            .hasAuthority("ROLE_ADMIN")
            .matchers(EndpointRequest.to(FeaturesEndpoint.class))
            .permitAll()
            .anyExchange()
            .permitAll()
            .and()
            .formLogin()
            .and()
            .csrf()
            .disable()
            .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
            .withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();

        return new MapReactiveUserDetailsService(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
    	return new AuthenticationConfiguration();
    }
    
    /**
     * https://www.programcreek.com/java-api-examples/?api=org.springframework.security.config.annotation.ObjectPostProcessor
     * 
     * @return
     */
    @Bean
    public ObjectPostProcessor<AffirmativeBased> createRoleProcessor() {
        return new ObjectPostProcessor<AffirmativeBased>() {
            @Override
            public AffirmativeBased postProcess(AffirmativeBased affirmativeBased) {
                WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
                DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
                expressionHandler.setRoleHierarchy(authorities -> {
                    String[] allAlertRoles =  {"ADMIN"}; ////retrieveAllowedRoles(allAlertRoles);
                    return (Collection<? extends GrantedAuthority>) AuthorityUtils.createAuthorityList(allAlertRoles);
                });
                webExpressionVoter.setExpressionHandler(expressionHandler);
                affirmativeBased.getDecisionVoters().add(webExpressionVoter);
                return affirmativeBased;
            }
        };
    }
}
