package com.sec.hamza.deepunderstandsec.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class User extends org.springframework.security.core.userdetails.User {

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public User(String userName , String password){
        super(userName,password, List.of());
    }
}
