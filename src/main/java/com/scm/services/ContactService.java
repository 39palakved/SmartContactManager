package com.scm.services;

import java.util.List;

import com.scm.entities.ContactInfo;
import com.scm.entities.User;

public interface ContactService {
   ContactInfo save(ContactInfo contact);
    List<ContactInfo> getAll();
    ContactInfo getById(String id);
    ContactInfo update(ContactInfo contactInfo);
    void delete(String id);
    List<ContactInfo> search(String name, String email, String phoneNumber);
    List<ContactInfo> getByUserId(String userId);
    List<ContactInfo> getByUser(User user);
}
