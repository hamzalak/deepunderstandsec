package com.sec.hamza.deepunderstandsec.dao;

import com.sec.hamza.deepunderstandsec.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDao {

    private final Set<User> usersSet = new HashSet<>();

    public void saveUser(User userDto){
        usersSet.add(userDto) ;
    }

    public User getUserByUserName(String userName){
        return getUser(userName) ;
    }

    private User getUser(String userName){
        return  usersSet.stream().filter(user -> Objects.equals(user.getUsername(),userName)).findFirst().orElse(null) ;
     }

}
