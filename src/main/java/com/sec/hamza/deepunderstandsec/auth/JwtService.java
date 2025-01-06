package com.sec.hamza.deepunderstandsec.auth;

import com.sec.hamza.deepunderstandsec.dto.UserDto;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

 import java.security.Key;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String generateToken(UserDto user) {
        Map<String, Object> claims
                = new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(user.username())
                .issuer("DCB")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 60*10*1000))
                .and()
                .signWith(generateKey())
                .compact();
    }


    private Key generateKey() {
         return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
