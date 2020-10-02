package com.degloba.ecommerce.trader.webapp.reactive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.degloba.ecommerce.trader.webapp.reactive.MyAuthenticationProvider;
import com.degloba.ecommerce.trader.webapp.reactive.jwt.JwtTokenFilterConfigurer;
import com.degloba.ecommerce.trader.webapp.reactive.jwt.JwtTokenProvider;



@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAuthenticationProvider authProvider;
	
	// JSON Web Token
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		/////////////.authorizeRequests().antMatchers("/**/orderbook").authenticated().and()
		.authorizeRequests().anyRequest().anonymous().and()
		.csrf().disable()
		.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
	
}
