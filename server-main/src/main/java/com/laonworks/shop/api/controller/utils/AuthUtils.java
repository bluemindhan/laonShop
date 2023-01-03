package com.laonworks.shop.api.controller.utils;

import com.amazonaws.http.HttpResponse;
import com.laonworks.shop.api.controller.request.auth.SignInRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class AuthUtils {

    // access token secret key
    private static final String ACCESS_TOKEN_SECRET_KEY = "G4kSQNqEJ8GTznL6";
    // refresh token secret key

    // 토근 유효시간
    final static int expiresIn = 60 * 60 * 5  * 1000;// 3시간

    public static HashMap<String, String> generateToken(String userId, int userType) {
        HashMap<String,String> tokens = new HashMap<>();
        String userPk = String.format("%s:%d", userId, userType);
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        Date expirationAcc;
        Date expirationRefresh;


        expirationAcc = new Date(now.getTime() + expiresIn);
        expirationRefresh = new Date(now.getTime() + expiresIn );


        String accessToken = Jwts.builder()
                            .setClaims(claims) // 정보저장
                            .setIssuedAt(now) // 토큰 발행시간 정보
                            .setExpiration(expirationAcc) // 만료시간 설정
                            .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                            .compact();

        String refreshToken = Jwts.builder()

                            .setIssuedAt(now) // 토큰 발행시간 정보
                            .setExpiration(expirationRefresh) // 만료시간 설정
                            .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                            .compact();

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public static String CreateAccessToken(String userId, int userType) {
        String userPk = String.format("%s:%d", userId, userType);
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        Date expiration;


        expiration = new Date(now.getTime() + expiresIn);


        return Jwts.builder()
                .setClaims(claims) // 정보저장
                .setIssuedAt(now) // 토큰 발행시간 정보
                .setExpiration(expiration) // 만료시간 설정
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                .compact();
    }

    public static String createRefresh(HttpServletResponse reponse) {

        Date now = new Date();
        Date expiration;

        expiration = new Date(now.getTime() + expiresIn * 10);

        String RefreshToken =Jwts.builder()
                .setIssuedAt(now) // 토큰 발행시간 정보
                .setExpiration(expiration) // 만료시간 설정
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY)
                .compact();

        Cookie cookie = new Cookie("refreshToken",RefreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        reponse.addCookie(cookie);

        return RefreshToken;
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

//    public static String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getId);
//    }
//
//    // token으로 사용자 속성정보 조회
//    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // 모든 token에 대한 사용자 속성정보 조회
//    private static Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
//    }
    public static String validateRefreshToken(String token){
        return Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET_KEY).parseClaimsJws(token).getBody().toString();
    }
}
