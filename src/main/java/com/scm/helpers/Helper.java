package com.scm.helpers;



import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

@Component
public class Helper {

   

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // agar email is password se login kiya hai to : email kaise nikalenge
        
            System.out.println("Getting data from local database");
            return authentication.getName();
        

    }

}
