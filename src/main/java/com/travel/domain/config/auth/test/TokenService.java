package com.travel.domain.config.auth.test;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    private String secretKey = "token-secret-key-tracious-dnd-group-8";
    private static final String AUTHORITIES_KEY = "role";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

//    @Override
    public Token generateToken(String uid, String role) {
        long tokenPeriod = 1000L * 60L * 10L;
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

        Claims claims = Jwts.claims().setSubject(uid);
        claims.put("role", role);

        Date now = new Date();
        return new Token(
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + tokenPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact(),
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + refreshPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact());
    }

//    @Override
    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

//    public Authentication getAuthentication(String accessToken) {
//        Claims claims = parseClaims(accessToken);
//
//        if (claims.get(AUTHORITIES_KEY) == null) {
//            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
//        }
//
////        Collection<? extends GrantedAuthority> authorities =
////                Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
////                        .map(SimpleGrantedAuthority::new)
////                        .collect(Collectors.toList());
//
//        final String socialId = claims.getSubject();
//        final CurrentUserDetails currentUserDetails = (CurrentUserDetails) userDetailsService.loadUserByUsername(socialId);
//
////        User principal = new User(claims.getSubject(), "", authorities);
//        return new UsernamePasswordAuthenticationToken(currentUserDetails, "", currentUserDetails.getAuthorities());
//    }
//
//    private Claims parseClaims(String accessToken) {
//        try {
//            System.out.println(accessToken);
//            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }

    //    @Override
    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
