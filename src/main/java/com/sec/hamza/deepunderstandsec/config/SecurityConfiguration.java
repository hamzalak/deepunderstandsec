package com.sec.hamza.deepunderstandsec.config;

import com.sec.hamza.deepunderstandsec.auth.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userDetailsService ;
    private final JwtService jwtService ;
    private  final FirstAuthenticationFilter firstAuthenticationFilter ;
    public SecurityConfiguration(UserService userDetailsService, JwtService jwtService, FirstAuthenticationFilter firstAuthenticationFilter) {
        this.userDetailsService = userDetailsService ;
        this.jwtService = jwtService;
        this.firstAuthenticationFilter = firstAuthenticationFilter;
    }

    @Bean
     public SecurityFilterChain filterRequest(HttpSecurity httpSecurity) throws  Exception{
         return  httpSecurity.
                 authorizeHttpRequests(authorizeHttp ->
                 {
                     authorizeHttp.requestMatchers("/public-products/list", "/auth/**").permitAll()
                         .anyRequest().authenticated();
                 }).addFilterAt(firstAuthenticationFilter, BasicAuthenticationFilter.class).httpBasic(Customizer.withDefaults()).csrf(CsrfConfigurer::disable)
                .build() ;
     }


    @Bean("jwt")
    public AuthenticationProvider getAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder) {
       return  new JwtProvider(jwtService) ;
     }

    @Bean
     public AuthenticationManager authenticationManager(@Qualifier("jwt") AuthenticationProvider jwtAuthenticationProvider, @Qualifier("usernamePassword") AuthenticationProvider userNamePasswordProvider) throws Exception {
        return new ProviderManager(List.of(jwtAuthenticationProvider,userNamePasswordProvider));
     }

   @Bean("usernamePassword")
    public AuthenticationProvider getAuthenticationProviderUserPassword(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UsernamePasswordProvider(userDetailsService, bCryptPasswordEncoder);
    }


}
