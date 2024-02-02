package com.example.CustomerService.repository;

import com.example.CustomerService.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Integer> {

    List<ContactDetails> findByUsername(String name);

}
