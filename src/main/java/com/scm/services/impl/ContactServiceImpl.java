package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.ContactInfo;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
  private ContactRepo contactRepo;
    @Override
    public ContactInfo save(ContactInfo contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return  contactRepo.save(contact);
    }

    @Override
    public List<ContactInfo> getAll() {
         return contactRepo.findAll();
    }

    @Override
    public ContactInfo getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with this id "));
    }

    @Override
    public void delete(String id) {
        var con = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found with this id "));
        contactRepo.delete(con);
    }

    @Override
    public List<ContactInfo> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<ContactInfo> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public ContactInfo update(ContactInfo contactInfo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<ContactInfo> getByUser(User user) {
      return  contactRepo.findByUser(user);
    }

    
}
