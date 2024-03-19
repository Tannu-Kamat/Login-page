package com.example.CustomerService.services;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CustomerService.Validator.CredentialsValidator;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerLoginServiceTest {

@Autowired
CustomerCredetialsRepository testCustomerCredetialsRepository;


  @Mock
  private CredentialsValidator credentialsValidator;

  @InjectMocks
  private CustomerLoginService loginService;
  @Test
   void testLogin() {}



  @Test
  void getUserDetails_name() {

  }

  @Test
  void getUserDetails_password() {
  }
}