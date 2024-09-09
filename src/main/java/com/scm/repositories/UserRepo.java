package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.scm.entities.User;
@Repository
public interface UserRepo extends CrudRepository<User,String>, JpaRepository<User,String> {
   //extra methods db related operations 
   //custom query methods 
   Optional<User> findByEmail(String email);
   Optional<User> findByEmailAndPassword(String email, String password);
}
