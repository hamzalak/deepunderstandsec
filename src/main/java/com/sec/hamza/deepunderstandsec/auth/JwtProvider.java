package com.sec.hamza.deepunderstandsec.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;

public class JwtProvider implements AuthenticationProvider {

    private final  JwtService jwtService ;

    public  JwtProvider(JwtService jwtService){
        this.jwtService = jwtService ;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;
        return new JwtAuthenticationToken(
                jwtAuth.getPrincipal(),
                jwtAuth.getCredentials(),
                Collections.emptyList()  // No authorities for now
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
