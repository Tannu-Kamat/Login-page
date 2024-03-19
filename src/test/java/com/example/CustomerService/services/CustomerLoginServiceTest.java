package com.example.CustomerService.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerLoginServiceTest {

@Autowired
CustomerCredetialsRepository testCustomerCredetialsRepository;
  @InjectMocks
CustomerLoginService customerLoginService;



  @Test
   void login() {
    String name="niyati";
    CustomerCredentials expectedCustomerCredentials= CustomerCredentials.builder().username("niyati")
        .password("niyati123").build();
    testCustomerCredetialsRepository.save(expectedCustomerCredentials);

    CustomerCredentials savedCustomerCredentials = testCustomerCredetialsRepository.findByUsername(name);

    assertNotNull(savedCustomerCredentials);

  }


  @Test
  void getUserDetails_name() throws Exception  {
//    String name="niyati";
//    CustomerCredentials expectedCustomerCredentials= CustomerCredentials.builder().username("niyati").password("niyati123").build();
//
//    testCustomerCredetialsRepository.save(expectedCustomerCredentials);
//
//    CompletableFuture<Credentials> result = customerLoginService.getUserDetails_name(name);
//    Credentials resultCustomerCredentials = result.get();
//
//    assertThat(expectedCustomerCredentials).isEqualTo(resultCustomerCredentials);

  }

  @Test
  void getUserDetails_password() throws Exception{
//    String password="niyati123";
//    String name="niyati";
//    CustomerCredentials expectedCustomerCredentials= CustomerCredentials.builder().username("niyati").password("niyati123").build();
//
//    testCustomerCredetialsRepository.save(expectedCustomerCredentials);
//
//    CompletableFuture<Credentials> result = customerLoginService.getUserDetails_password(password);
//    Credentials resultCustomerCredentials = result.get();
//
//    assertThat(expectedCustomerCredentials).isEqualTo(resultCustomerCredentials);
//    assertEquals(name, resultCustomerCredentials.getUsername());

  }

}