package com.michaelyi.personalwebsite.auth;

import java.security.Key;

import com.michaelyi.personalwebsite.util.StringUtil;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class AuthUtil {
    public static final String ADMIN_EMAIL = "admin@michael-yi.com";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    public static boolean isAuthHeaderInvalid(String authHeader) {
        return StringUtil.isStringInvalid(authHeader)
                || !authHeader.startsWith("Bearer ");
    }

    public static Key getSigningKey(String signingKey) {
        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
