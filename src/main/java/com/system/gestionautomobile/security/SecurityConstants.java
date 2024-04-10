package com.system.gestionautomobile.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class SecurityConstants {
    public static final String SECRET_KEY_STRING = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final byte[] SECRET_KEY_BYTES = SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8);
    public static final SecretKey SECRET_KEY = new SecretKeySpec(SECRET_KEY_BYTES, "AES");
    public static final int TOKEN_EXPIRATION = 86400000   ; //1 JOUR
    public static final String BEARER = "Bearer ";
    public static final String AUTHENTICATE_PATH = "/authenticate";
    public static final String[] WHITE_LIST = {
            "/auth/signUp"
            ,"/v3/api-docs",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
            ,"/swagger-resources/**"
            ,"/configuration/ui"
            ,"/configuration/security"
            ,"/configuration/security",
    };
    public static  final Long MAX_AGE = 3600L;
    public static final  int CORS_FILTER_ORDER =-102;
}
