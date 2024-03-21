package com.example.CustomerService.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CustomerService.Validator.CredentialsValidator;
import com.example.CustomerService.Validator.CustomerContactValidator;
import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.repository.ContactDetailsRepository;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ContactDetailsServiceTest {

  @Mock
  private ContactDetailsRepository testContactDetailsRepository;

  @Mock
  private CustomerCredetialsRepository testCustomerCredetialsRepository;

  @Mock
  private CustomerContactValidator customerContactValidator;

  @InjectMocks
  private ContactDetailsService testContactDetailsService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }
  @AfterEach
  void tearDown() {
    testCustomerCredetialsRepository.deleteAll();
  }

  @Test
  void testAddContact_success() throws CustomerNotFoundException {
    String name="niyati";
    CustomerContact customerContact =  CustomerContact.builder().username(name).phoneNumber("1234567890").build();
    CustomerCredentials customerCredentials= CustomerCredentials.builder().username(name).password("niyati123").build();


    when(testCustomerCredetialsRepository.findByUsername(customerContact.getUsername())).thenReturn(customerCredentials);

    assertDoesNotThrow(() -> testContactDetailsService.addContact(name, customerContact));

    verify(testContactDetailsRepository, times(1)).save(any(ContactDetails.class));
  }


  @Test
  void addContact_CustomerNotFound() {
    String name = "nonExistingUser";
    CustomerContact customerContact =  CustomerContact.builder().username(name).phoneNumber("1234567890").build();

    when(testCustomerCredetialsRepository.findByUsername(name)).thenReturn(null);

    assertThrows(CustomerNotFoundException.class, () -> testContactDetailsService.addContact(name, customerContact));
    verify(testContactDetailsRepository, never()).save(any(ContactDetails.class));
  }

  @Test
  void testGetUserContact_success() throws CustomerNotFoundException {
    String name = "username";
    ContactDetails expectedContactDetails = ContactDetails.builder().username(name).phoneNumber("1234567890").build();

    when(testContactDetailsRepository.findByUsername(name)).thenReturn(Collections.singletonList(expectedContactDetails));

    List<CustomerContact> resultContactDetails = testContactDetailsService.getUserContact(name);

    assertEquals(1, resultContactDetails.size());
    assertEquals(name, resultContactDetails.get(0).getUsername());
    assertEquals("1234567890", resultContactDetails.get(0).getPhoneNumber());
  }

  @Test
  void testGetUserContact_CustomerNotFound() {
    String name = "niyati";

    when(testContactDetailsRepository.findByUsername(name)).thenReturn(Collections.emptyList());

    assertThrows(CustomerNotFoundException.class, () -> testContactDetailsService.getUserContact(name));
  }
}