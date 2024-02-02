package com.example.CustomerService.repository;

import com.example.CustomerService.entity.CustomerCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCredetialsRepository extends JpaRepository<CustomerCredentials, Integer> {
   CustomerCredentials findByUsername(String name);

    CustomerCredentials findByPassword(String password);

}
