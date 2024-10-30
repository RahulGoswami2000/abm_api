package com.abm.abm.Utils;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Repository.AuthRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtUtil {
     private final AuthRepository authRepository;
     public static final int JWT_TOKEN_START_INDEX = 7;


     Date now = new Date(System.currentTimeMillis());
     Date validity = new Date(now.getTime() + 1000*60*60*24*2);
     private static String secret = "abmapp";

     public String generateToken(MstUsers user) {
          Map<String, Object> claims = new HashMap<>();
          return createToken(claims, user);
     }

     public String createToken(Map<String, Object> claims, MstUsers user) {
          System.out.println(now);
          System.out.println(validity);
          claims.put("first_name", user.getFirst_name());
          claims.put("last_name", user.getLast_name());
          return Jwts.builder()
                  .setClaims(claims)
                  .setSubject(user.getEmail())
                  .setIssuedAt(now)
                  .setExpiration(validity)
                  .signWith(SignatureAlgorithm.HS256, secret)
                  .compact();
     }

     public boolean validateToken(String token){
          try {
               Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
               return true;
          } catch (Exception e) {
               throw new AuthenticationCredentialsNotFoundException("Token has expired or incorrect");
          }
     }

     public Authentication validateTokenStrongly(String token) {
          MstUsers user = getUserByToken(token);
          return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
     }

     public MstUsers getUserByToken(String token) {
          Claims claim = Jwts.parser()
                              .setSigningKey(secret)
                              .parseClaimsJws(token)
                              .getBody();

          MstUsers user = authRepository.findOneByEmail(claim.getSubject());

          if(user == null) {
               return null;
          }

          return user;
     }

     public MstUsers getUser(HttpServletRequest request) {
          String authHeader = request.getHeader("Authorization");
          String token = null;
          token = authHeader.substring(JWT_TOKEN_START_INDEX);
          MstUsers user = getUserByToken(token);
          return user;
     }
}
