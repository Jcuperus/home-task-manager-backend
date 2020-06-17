package com.HomeTaskManager.HomeTaskManagerBackend.security;

public class SecurityConstants
{
    public static final String SECRET = "yo mamma";
    public static final long EXPIRATION_DATE = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/create";
}
