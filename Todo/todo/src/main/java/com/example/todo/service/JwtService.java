package com.example.todo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    private static final String SECRET = "abcdefghijklmnop";

    public String generateToken(String username){
        HashMap<String, Object> claims= new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(2*60*60*1000)))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        Claims claims= this.extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiryTime(String token){
        Claims claims= this.extractAllClaims(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token){
        return extractExpiryTime(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username= extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
