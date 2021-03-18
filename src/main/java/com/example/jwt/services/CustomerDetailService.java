package com.example.jwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerDetailService implements UserDetailsService {
   @Override
   public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
      if (user.equals("tagrohan")) {
         return new User("tagrohan", "pass", new ArrayList<>());

      } else {
         throw new UsernameNotFoundException("user not available in DB");
      }
   }
}
