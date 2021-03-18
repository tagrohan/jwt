package com.example.jwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

   @GetMapping("/home")
   public String home() {
      return "for authorised one";
   }


   @GetMapping("/admin")
   public String admin(){
      return "for admin";
   }
}
