package com.seb45_pre_036.stackoverflow.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {

    @Getter
//    @Value("${jwt.key}")
    private String secretKey = "aaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbb";

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String encodeBase64SecretKey(String secretKey){
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));

        // String secretKey 값 -> base64 인코딩 -> return
        // secretKey -> byte[] -> base64 인코딩 -> return

    }

    public Key getKeyFromBase64EncodedSecretKey(String base64EncodedSecretKey){
        // base64 인코딩된 secret Key 받음

        byte[] keyByte = Decoders.BASE64.decode(base64EncodedSecretKey);
        // base64 인코딩된 secret key -> byte[] 디코딩

        Key key = Keys.hmacShaKeyFor(keyByte);
        // 해싱처리(암호화) -> key 생성 -> return

        return key;

    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey){
        Key key = getKeyFromBase64EncodedSecretKey(base64EncodedSecretKey);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey){
        Key key = getKeyFromBase64EncodedSecretKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey){

        Key key = getKeyFromBase64EncodedSecretKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

        return claims;
    }

    public void verifyRefreshToken(String jws, String base64EncodedSecretKey){

        Key key = getKeyFromBase64EncodedSecretKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public long getMemberIdFromAccessToken(String accessToken, String base64EncodedSecretKey){
        // accessToken / base64 인코딩된 시크릿 키
        // -> memberId 값 return

        String jws = accessToken.replace("Bearer ", "");

        Key key = getKeyFromBase64EncodedSecretKey(base64EncodedSecretKey);

        Map<String, Object> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody();

        // payload(claim)

        int memberId = (int) claims.get("memberId");

        return (long) memberId;

    }


    public Date getTokenExpiration(int expirationMinutes){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }


}
