package org.example.bootjwt.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

// Java Web Token / JSON Web Token.
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-ms}")
    private long validityInMs; // 만료가 언제되냐 ms(1/1000초)

    // 토큰 생성
    public String createToken(Authentication auth) {
        String username = auth.getName();
        Instant now = Instant.now(); // UTC.
        Date expiryDate = new Date(now.toEpochMilli() + validityInMs);
        SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(expiryDate)
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsername(String token) {
        SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jwts.parser().verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // RuntimeException 이라 Throws 대상 아님.
            return false;
        }
    }
}