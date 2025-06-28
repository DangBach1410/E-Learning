package com.example.elearning.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String JWT_SECRET = "2q5GUvEXgJgA9HnF9LMu9YFDfT9xFL0Pl+QmFAOUxyuYIEukcXZxVQFGMNFxgzjzEjYtY9PT09GkKTXv6KYsm4A==";
    private final long JWT_EXPIRATION = 86400000L;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}

