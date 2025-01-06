package com.sec.hamza.deepunderstandsec.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDetails implements   org.springframework.security.core.userdetails.UserDetails {

    private final String username ;
    private final String password ;

    public UserDetails(String username , String password){
        this.password = password ;
        this.username = username ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public  boolean isAccountNonExpired(){
        return true ;
    }


}
