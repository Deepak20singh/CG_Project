package com.example.LoginSignup.component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
  private final String Secretkey="";

  public String generateToken(Object userDetails){
      return Jwts.builder()
              .setSubject(userDetails.toString())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
              .compact();
  }
  public Claims extractClaims(String token){
      return Jwts.parser()
              .setSigningKey(Secretkey)
              .parseClaimsJwt(token)
              .getBody();
  }
  public String extractUsername(String token){
      return extractClaims(token).getSubject();
  }
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    public boolean validateToken(String token,String userEmail){
      return extractUsername(token).equals(userEmail)&& !isTokenExpired(token);
    }
}
