package com.example.jwt.configuration;

import com.example.jwt.filters.JwtAuthenticationFilter;
import com.example.jwt.services.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private CustomerDetailService customerDetailService;
   @Autowired
   private JwtAuthenticationFilter filter;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customerDetailService);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
              .cors().disable()
              .authorizeRequests()
              .antMatchers("/token").permitAll()
              .anyRequest().authenticated()
              .and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
   }

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }
}
