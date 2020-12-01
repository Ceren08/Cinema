package com.cinema.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	public Class<?>[] getRootConfigClasses() {
        return new Class[] {WebSecurityConfig.class};
    }
	
}