package com.btumu.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.btumu.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)
					.permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.addFilter(getAuthenticationFilter())
					.addFilter(new AuthorizationFilter(authenticationManager()))
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/users/login");
		return authenticationFilter;
	}
	
}
