package com.scm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 6000)
    private String description;
    private boolean favorite = false;
     private String websiteLink;
     private String LinkedInLink;
     private String cloudinaryPulicId;
    // private List<SocialLink> socialLink = new ArrayList<>();
     @ManyToOne
     private User user ; 
      @OneToMany(mappedBy ="contact", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
   private List<SocialLink> links = new ArrayList<>();
}
