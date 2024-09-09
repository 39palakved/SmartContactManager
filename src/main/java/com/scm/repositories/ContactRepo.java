package com.scm.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.entities.ContactInfo;
import com.scm.entities.User;


@Repository

public interface ContactRepo extends CrudRepository<ContactInfo,String>,JpaRepository<ContactInfo,String>{
    
     //find contacts by user
     List<ContactInfo> findByUser(User user);
    @Query("SELECT c FROM ContactInfo c WHERE c.user.id=:userId")
     List<ContactInfo>findByUserId(@Param ("userId") String userId);

}
