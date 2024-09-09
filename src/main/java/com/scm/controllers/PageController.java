package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PageController {
    @Autowired
    private UserService userService;
@GetMapping("/")

    public String index(){
       return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(){
        System.out.println("home page handler");
        return "home";
    }
    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("isLogin" , true);
        System.out.println("about page handler");
        return "about";
    }
    @RequestMapping("/services")
    public String services(){
        System.out.println("services page handler");
        return "services";
    }
    @GetMapping("/contact")
    public String contact(){
        System.out.println("contact page handler");
        return "contact";
    }
    @GetMapping("/login")
    public String login(){
        System.out.println("login page handler");
        return "login";
    }
    @GetMapping("/register")
    public String register (Model model){
       UserForm userForm = new  UserForm();
       userForm.setName("palak");
       model.addAttribute("userForm", userForm);
        return "register";
    }
    // processing rester 
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
      public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult result, HttpSession session){
     if(result.hasErrors()){
        return "register";
     }
    User user = new User();
   
       
          user.setName(userForm.getName());
          user.setEmail(userForm.getEmail());
          user.setPassword(userForm.getPassword());
          user.setAbout(userForm.getAbout());
          user.setPhoneNumber(userForm.getPhoneNumber());
          user.setProfilePic("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRk8Gl9eN3We2TcCYbPyAxqG6SqN02Wey-1vB0iuhZfyw&s");
          User savedUser= userService.saveUser(user);
         
        System.out.println("user saved");
        //add message 
        Message message = Message.builder().content("Registration Successfull").type(MessageType.green).build();
       session.setAttribute("message",message);
        return "redirect:/register";
    }



}
