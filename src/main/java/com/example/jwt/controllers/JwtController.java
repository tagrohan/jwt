package com.example.jwt.controllers;

import com.example.jwt.models.JwtRequest;
import com.example.jwt.models.JwtResponse;
import com.example.jwt.services.CustomerDetailService;
import com.example.jwt.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private CustomerDetailService customerDetailService;
   @Autowired
   private JwtUtil jwtUtil;


   @PostMapping("/token")
   public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
      System.out.println(jwtRequest);

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
              jwtRequest.getPassword()));
      try {

      } catch (Exception e) {
         e.printStackTrace();
      }

      // when reached here
      UserDetails userDetails = customerDetailService.loadUserByUsername(jwtRequest.getUsername());

      String token = jwtUtil.generateToken(userDetails);

      return ResponseEntity.ok().body(new JwtResponse(token));

   }
}
