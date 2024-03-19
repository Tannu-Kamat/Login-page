package com.example.CustomerService.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CustomerService.Validator.CustomerContactValidator;
import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.repository.ContactDetailsRepository;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ContactDetailsServiceTest {
  ContactDetailsRepository testContactDetailsRepository;





  @Test
  void addContact() {
  }

  @Test
  void getUserContact() {
  }
}