package com.example.CustomerService.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.CustomerService.entity.CustomerCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class CustomerCredetialsRepositoryTest {

  @Autowired
  private CustomerCredetialsRepository testCustomerCredetialsRepository;

  @Test
  void findByUsername() {
    String name = "niyati";

    CustomerCredentials expectedCredentials= CustomerCredentials.builder().username("niyati").password("niyati123").build();

    testCustomerCredetialsRepository.save(expectedCredentials);

    CustomerCredentials resultCredentials = testCustomerCredetialsRepository.findByUsername(name);

    assertThat(resultCredentials).isEqualTo(expectedCredentials);


  }

  @Test
  void findByPassword() {
    String password="niyati123";
    CustomerCredentials expectedCustomerCredentials= CustomerCredentials.builder().username("niyati").password("niyati123").build();
    testCustomerCredetialsRepository.save(expectedCustomerCredentials);


    CustomerCredentials resultCustomerCredentials = testCustomerCredetialsRepository.findByPassword(password);

    assertThat(resultCustomerCredentials.getPassword()).isEqualTo(expectedCustomerCredentials.getPassword());
  }

  @Test
  public void findByNonExistentUsername() {

    CustomerCredentials foundCredentials = testCustomerCredetialsRepository.findByUsername("nonExistentUser");

    assertNull(foundCredentials);
  }

  @Test
  public void testFindByNonExistentPassword() {

    CustomerCredentials foundCredentials = testCustomerCredetialsRepository.findByPassword("nonExistentPassword");

    assertNull(foundCredentials);
  }

}