package com.michaelyi.personalwebsite.auth;

import com.michaelyi.personalwebsite.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.michaelyi.personalwebsite.util.Constants.BEARER_PREFIX_LENGTH;
import static com.michaelyi.personalwebsite.util.Constants.JWT_EXPIRATION;
import static com.michaelyi.personalwebsite.util.Constants.SECURITY_AUTH_ADMIN_PW;
import static com.michaelyi.personalwebsite.util.Constants.SECURITY_JWT_SECRET_KEY;

@Service
public class AuthService {
    private final String adminPassword;
    private final String signingKey;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            @Value(SECURITY_AUTH_ADMIN_PW)
            String adminPassword,
            @Value(SECURITY_JWT_SECRET_KEY)
            String signingKey,
            PasswordEncoder passwordEncoder
    ) {
        this.adminPassword = adminPassword;
        this.signingKey = signingKey;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(AuthRequest req) {
        boolean authorized = passwordEncoder.matches(
                req.password(),
                adminPassword
        );

        if (!authorized) {
            throw new UnauthorizedException();
        }

        Map<String, Object> claims = new HashMap<>();
        long currentTime = System.currentTimeMillis();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(Constants.ADMIN_EMAIL)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + JWT_EXPIRATION))
                .signWith(
                        AuthUtil.getSigningKey(signingKey),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public void validateToken(String authHeader) {
        String token = authHeader.substring(BEARER_PREFIX_LENGTH);

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(AuthUtil.getSigningKey(signingKey))
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();

        if (expiration.before(new Date())) {
            throw new UnauthorizedException();
        }
    }
}
