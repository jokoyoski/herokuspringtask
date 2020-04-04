package com.example.demo.security;

import com.example.demo.domain.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtTokenProvider {

    //Generate Token

    public String generateToken(Authentication authentication){
        Map<String, Object> claims =new HashMap<>();

        User user=(User)authentication.getPrincipal();
        claims.put("id",(Long.toString(user.getId())));
        claims.put("username",user.getUsername());
        Date now =new Date(System.currentTimeMillis());
        Date expiryDate=new Date(now.getTime()+SecurityConstants.EXPIRATION);
        String userId=Long.toString(user.getId());

        return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET).compact();

    }


    //Validate Token
    public boolean validateToken(String token){

        try{
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            System.out.println("Invalid jwt signature");

        }catch(MalformedJwtException e){
            System.out.println("Invalid jwt token");
        }catch(ExpiredJwtException e){
            System.out.println(" Expired jwt signature");
        }catch(UnsupportedJwtException e){
            System.out.println("Unsupported jwt signature");
        }catch(IllegalArgumentException e){
            System.out.println("Jwt is empty");
        }
        return false;
    }

      public String getUserNameFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        String username =(String)claims.get("username");
        return username;
      }
}
