package com.laonworks.shop.api.controller.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class AuthUtils {

    // access token secret key
    private static final String ACCESS_TOKEN_SECRET_KEY = "G4kSQNqEJ8GTznL6";
    // refresh token secret key


    final static int expiresIn = 60 * 60 * 24 * 1000; // 1 days

    public static String generateToken(String userId, int userType) {
        String userPk = String.format("%s:%d", userId, userType);
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        Date expiration;

        expiration = new Date(now.getTime() + expiresIn);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                .compact();
    }


    public static String parseToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY)
                    .parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // log.error("validateToken error", e);
            return false;
        }
    }
}
