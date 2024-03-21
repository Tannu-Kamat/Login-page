package com.example.CustomerService.services;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.CustomerService.Validator.CredentialsValidator;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerLoginServiceTest {

  @Autowired
  CustomerCredetialsRepository testCustomerCredetialsRepository;
  CustomerLoginService customerLoginService;
  private CredentialsValidator credentialsValidator;

  @BeforeEach
  void setUp() {

    customerLoginService = new CustomerLoginService(testCustomerCredetialsRepository,
        credentialsValidator);

  }

  @AfterEach
  void tearDown() {
    testCustomerCredetialsRepository.deleteAll();
  }
  @Test
  void login()  {
    String name = "niyati";

    Credentials expectedCredentials = Credentials.builder()
        .username("niyati")
        .password("niyati123")
        .build();

    customerLoginService.login(expectedCredentials);
    CustomerCredentials resultCredentials = testCustomerCredetialsRepository.findByUsername(name);

    assertThat(resultCredentials.getUsername()).isEqualTo(expectedCredentials.getUsername());
  }

  @Test
  void getUserDetails_name() throws ExecutionException, InterruptedException {
    String name = "niyati";
    CustomerCredentials expectedCustomerCredentials = CustomerCredentials.builder()
        .username("niyati").password("niyati123").build();

    testCustomerCredetialsRepository.save(expectedCustomerCredentials);

    Credentials resultCustomerCredentials = customerLoginService.getUserDetails_name(name);

    assertThat(expectedCustomerCredentials.getUsername()).isEqualTo(
        resultCustomerCredentials.getUsername());

  }

  @Test
  void getUserDetails_password() throws Exception {
    String password = "niyati123";
    CustomerCredentials expectedCustomerCredentials = CustomerCredentials.builder()
        .username("niyati").password("niyati123").build();

    testCustomerCredetialsRepository.save(expectedCustomerCredentials);

    Credentials resultCustomerCredentials = customerLoginService.getUserDetails_password(password);

    assertThat(expectedCustomerCredentials.getUsername()).isEqualTo(
        resultCustomerCredentials.getUsername());
    assertThat(expectedCustomerCredentials.getPassword()).isEqualTo(
        resultCustomerCredentials.getPassword());

  }

}