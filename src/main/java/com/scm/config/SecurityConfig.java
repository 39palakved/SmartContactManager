package com.scm.config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    @Bean
   public DaoAuthenticationProvider authenticationProvider(){
    
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

    return daoAuthenticationProvider; 
   }
   @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

  httpSecurity.authorizeHttpRequests(authorize->{
  //  authorize.requestMatchers("/home","/register","/services").permitAll();
  authorize.requestMatchers("/user/**").authenticated();
  authorize.anyRequest().permitAll();
  });
       httpSecurity.formLogin(formLogin->{

        //
        formLogin.loginPage("/login");
       formLogin.loginProcessingUrl("/authenticate");
       formLogin.successForwardUrl("/user/dashboard");
      //  formLogin.failureForwardUrl("/login?error=true");
       formLogin.usernameParameter("email");
       formLogin.passwordParameter("password");
       formLogin.defaultSuccessUrl("/user/profile", true);
   
      //  formLogin.failureHandler(new AuthenticationFailureHandler(){

      //   @Override
      //   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      //       AuthenticationException exception) throws IOException, ServletException {
      //     // TODO Auto-generated method stub
      //     throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
      //   }
      //   // 
      //  });
      //    formLogin.successHandler(new AuthenticationSuccessHandler() {

      //     @Override
      //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      //         Authentication authentication) throws IOException, ServletException {
      //       // TODO Auto-generated method stub
      //       throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
      //     }
          
      //    });
       });
       httpSecurity.csrf(AbstractHttpConfigurer::disable);
       httpSecurity.logout(logoutForm->{
        logoutForm.logoutUrl("/do-logout");
        logoutForm.logoutSuccessUrl("/login?logout=true");
       }
       );
       return httpSecurity.build();
 }

   @Bean
   public  PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
   }

}
