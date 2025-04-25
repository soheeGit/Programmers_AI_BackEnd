package org.example.bootjwtkakao.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@Log
public class JwtTokenProvider {
    @Value("${jwt.secret}") // lombok value 아님!
    private String secretKey;
    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Authentication authentication, List<String> roles) { // 패러미터 -> 정보...
        String username = authentication.getName();
        Instant now = Instant.now(); // UTC.
        Date expiration = new Date(now.toEpochMilli() + expirationMs); // sql, <util>!!!!
//        log.info("roles : %s".formatted(roles));
        Claims claims = Jwts.claims()
                .subject(username)
                .add("roles", roles)
                .build();
        return Jwts.builder()
                .subject(username) // uuid를 넣어도 된다...
                .issuedAt(Date.from(now)) // 토큰생성일자
                .expiration(expiration) // 만료일자
                .claims(claims)
                .signWith(getSecretKey(), Jwts.SIG.HS256) // 변환 알고리즘
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)// // 1. 양식을 지켰나? 2. 키와 대응 3. 만료되었는가?
                .getPayload() // 유저 데이터
                .getSubject(); // username
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 의도적으로 Catch를 해줘야한다
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        return (List<String>) Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles");
    }

    public Authentication getAuthentication(String token) {
        // User -> Security. User -> UserAccount, LoginUser
        UserDetails user = new User(getUsername(token), "",
                getRoles(token).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_%s".formatted(role))) // ROLE 붙여야 된다... 자동완성 믿지마!!!
                        .toList());
        // 문자열 -> 권한 클래스 객체
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}