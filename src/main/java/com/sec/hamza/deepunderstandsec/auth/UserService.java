package com.sec.hamza.deepunderstandsec.auth;


import com.sec.hamza.deepunderstandsec.dao.UserDao;
import com.sec.hamza.deepunderstandsec.dto.UserDto;
import com.sec.hamza.deepunderstandsec.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final  UserDao userDao ;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder ;
    UserService(UserDao userDao , BCryptPasswordEncoder bCryptPasswordEncoder){
         this.userDao = userDao ;
         this.bCryptPasswordEncoder = bCryptPasswordEncoder;
     }

    public void addNewUser(UserDto userDto){
            var user = new User(userDto.username(),bCryptPasswordEncoder.encode(userDto.password())) ;
            userDao.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var regularUser = this.userDao.getUserByUserName(username) ;
         return  new UserDetails(regularUser.getUsername(), regularUser.getPassword()) ;
    }
}
