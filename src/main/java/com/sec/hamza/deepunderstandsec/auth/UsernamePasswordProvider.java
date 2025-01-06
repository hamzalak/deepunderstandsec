package com.sec.hamza.deepunderstandsec.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//if more generic use AuthenticationProvider instead of AbstractUserDetailsAuthenticationProvider

public class UsernamePasswordProvider extends AbstractUserDetailsAuthenticationProvider {

    private  final  UserService userService;
    private  final  BCryptPasswordEncoder bCryptPasswordEncoder ;

    public UsernamePasswordProvider(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if(authentication.getCredentials()==null){
            throw new  BadCredentialsException("No credentials provided") ;
        }

        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())){
             throw  new BadCredentialsException("password not okey") ;
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return  userService.loadUserByUsername(username);

    }
}
