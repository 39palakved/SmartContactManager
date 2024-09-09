package com.scm.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.ContactInfo;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;






@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    // add contact page handler
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private  ImageService imageService;
  @RequestMapping("/add")
  
    public String addContactView(Model model){
       ContactForm contactForm = new ContactForm();
      //  contactForm.setName("palak");
      //  contactForm.setFavorite(true);

        model.addAttribute("contactForm", contactForm);
          return "user/add_contact";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST)

    public String SaveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,Principal principal,HttpSession session){

  if(result.hasErrors()){
    session.setAttribute("message",Message.builder().
    content("correct following error").type(MessageType.red)
    .build());
    return"user/add_contact";
  }

      String name =  principal.getName();
      
       User user= userService.getUserByEmail(name);
       System.out.println("file information"+contactForm.getPicture().getOriginalFilename());
       System.out.println("Favorite value: " + contactForm.isFavorite()); 
      System.out.println(" ***************"+ user); 
      
      ///image upload krne ka code
      String filename = UUID.randomUUID().toString();
      String fileURL = imageService.uploadImage(contactForm.getPicture(),filename);


       System.out.println("*******************************"+fileURL);

        ContactInfo contact = new ContactInfo();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
         contact.setDescription(contactForm.getDescription());
         contact.setWebsiteLink(contactForm.getWebsiteLink());
         contact.setPhoneNumber(contactForm.getPhoneNumber());
          contact.setUser(user);
         contact.setFavorite(contactForm.isFavorite());
         contact.setAddress(contactForm.getAddress());
         contact.setPicture(fileURL);
         contact.setCloudinaryPulicId(filename);
        contactService.save(contact);
        //process form data 
        Message message = Message.builder().content("Contact Added successfully").type(MessageType.green).build();
        session.setAttribute("message",message);
         System.out.println("**********"+contactForm);
          return "redirect:/user/contacts/add";
    }
    
    
    // view contacts
   @RequestMapping
   public String viewContacts(Model model, Authentication authentication) {
 
      String username = authentication.getName();
    //LoadallUsers
   User user= userService.getUserByEmail(username);
  //  System.out.println("----------------------------------------------"+user);
    List<ContactInfo> contacts =contactService.getByUser(user);
    model.addAttribute("contacts",contacts);
   // System.out.println("**************************************************"+contacts);

        return "user/showcontacts";
    }
    
}