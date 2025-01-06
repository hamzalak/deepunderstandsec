package com.sec.hamza.deepunderstandsec.controller;


import com.sec.hamza.deepunderstandsec.auth.JwtAuthenticationToken;
import com.sec.hamza.deepunderstandsec.auth.JwtService;
import com.sec.hamza.deepunderstandsec.dto.UserDto;
import com.sec.hamza.deepunderstandsec.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserAuth {

    private final  UserService userService ;
    private final AuthenticationManager authenticationManager ;
    private  final JwtService jwtService;
    public UserAuth(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("sign-up")
    public String newUser(@RequestBody UserDto userDto){
        try {
            userService.addNewUser(userDto);
            return  "uer added Yes"  ;
        }catch (Exception ex){
            return "User was not added something went wrong" ;
        }
    }

    @PostMapping("sign-in")
    public String login(@RequestBody UserDto userDto){
        var auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.username(),userDto.password())) ;
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "Login success" ;
    }

    @PostMapping("sign-in/jwt")
    public ResponseEntity<?> jwtLogin(@RequestBody UserDto userDto){
        var tokenObject = jwtService.generateToken(userDto) ;
        //var auth =  authenticationManager.authenticate(tokenObject) ;
        var auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.username(),userDto.password())) ;
        // what should we really store in the context
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>(tokenObject, HttpStatus.OK) ;
    }

}
