package com.alkemy.disney.app.security;

import com.alkemy.disney.app.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/register";

    public static String getTokenSecret() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
    	return appProperties.getTokenSecret();
    }
}
