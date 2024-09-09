package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
   @NotBlank(message = "Name is required")
   private String name;
   @NotBlank(message = "Email is required")
   @Email(message="Invalid Email Address")
   private String email;
    @Size( min=8 ,max=12 ,message="Invalid Phone Number")
   private String phoneNumber;
   @NotBlank(message = "Address is required")
   private String address;
   private String description;
   private boolean favorite;
   private String websiteLink;
   private String LinkedInLink;
   //annotation
   private MultipartFile picture;

}
