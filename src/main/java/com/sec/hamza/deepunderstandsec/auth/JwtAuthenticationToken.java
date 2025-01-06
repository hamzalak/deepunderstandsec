package com.sec.hamza.deepunderstandsec.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal ;
    private Object credentials ;

    public  JwtAuthenticationToken(String token ){
        super(null);
         setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, Object credentials, List<Object> objects) {
        super(null);
        this.credentials = credentials ;
        this.principal = principal ;
    }

    @Override
    public Object getCredentials() {
        return this.credentials ;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
