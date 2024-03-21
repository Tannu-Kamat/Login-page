package com.example.CustomerService.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import com.example.CustomerService.services.ContactDetailsService;
import com.example.CustomerService.services.CustomerLoginService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerControllerTest {

  @Mock
  private CustomerLoginService loginService;

  @Mock
  private ContactDetailsService contactDetailsService;

  @Mock
  private CustomerCredetialsRepository customerCredetialsRepository;

  @InjectMocks
  private CustomerController customerController;

  @BeforeEach
  void setUp() {
    customerController = new CustomerController(loginService,contactDetailsService);

    MockitoAnnotations.openMocks(this);
  }

  @Test
  void loginWithValidCredentials() {
    Credentials credentials = Credentials.builder()
        .username("niyati")
        .password("niyati123")
        .build();
    ResponseEntity responseEntity = customerController.login(credentials);
    assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    verify(loginService, times(1)).login(credentials);
  }

  @Test
  void addContactWithValidData() throws CustomerNotFoundException {
    String name = "niyati";
    CustomerContact customerContact = CustomerContact.builder().username(name)
        .phoneNumber("1234567890").build();
    ResponseEntity responseEntity = customerController.addContact(name, customerContact);
    assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    verify(contactDetailsService, times(1)).addContact(name, customerContact);
  }

  @Test
  void showUserNameWithValidName() throws CustomerNotFoundException {
    String name = "niyati";
    Credentials expectedCredentials = Credentials.builder()
        .username(name)
        .password("niyati123")
        .build();
    when(loginService.getUserDetails_name(name)).thenReturn(expectedCredentials);
    Credentials resultCredentials = customerController.showUserName("niyati");
    assertEquals(expectedCredentials, resultCredentials);
  }

  @Test
  void showUserPasswordWithValidPassword() throws PasswordNotFoundEcxeption{
    String password = "password";
    Credentials expectedCredentials = Credentials.builder()
        .username("niyati")
        .password(password)
        .build();
    when(loginService.getUserDetails_password("password")).thenReturn(expectedCredentials);
    Credentials resultCredentials = customerController.showUserPassword(password);
    assertEquals(expectedCredentials, resultCredentials);
    assertThat(resultCredentials.getPassword()).isEqualTo(expectedCredentials.getPassword());
  }

  @Test
  void showContactDetailsWithValidName() throws CustomerNotFoundException {
    String name = "niyati";
    List<CustomerContact> expectedContactList = Collections.singletonList( CustomerContact.builder().username(name).phoneNumber("1234567890").build());
    when(contactDetailsService.getUserContact(name)).thenReturn(expectedContactList);
    List<CustomerContact> resultContactList = customerController.showContactDetails(name);
    assertEquals(expectedContactList, resultContactList);
    verify(contactDetailsService, times(1)).getUserContact(name);
  }
}