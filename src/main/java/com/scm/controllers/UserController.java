package com.scm.controllers;

import java.security.Principal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.services.UserService;





@Controller
@RequestMapping("/user")
public class UserController {
     //user dashboard pge 
     private Logger logger = LoggerFactory.getLogger(UserController.class);
     @Autowired
     private UserService userService;
    
     @RequestMapping(value="/dashboard", method=RequestMethod.GET)
       public String userDashboard(){
        return "user/dashboard";
       }
       // profile
       @RequestMapping(value="/profile", method=RequestMethod.GET)
       public String userProfile(){
       
        return "user/profile";
       }
     
     // user add contact 
     // user view contact 
     // user edit 
}
