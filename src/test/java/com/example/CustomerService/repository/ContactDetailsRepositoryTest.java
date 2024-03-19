package com.example.CustomerService.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.services.ContactDetailsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ContactDetailsRepositoryTest {

  @Autowired
  private ContactDetailsRepository testContactDetailsRepository;


  @AfterEach
  void tearDown() {
    testContactDetailsRepository.deleteAll();
  }
  @Test
  void findByUsername() {
    String name="alpha";
    List<ContactDetails> expectedContactDetailsList = new ArrayList<>();
    ContactDetails expectedContactDetails1= ContactDetails.builder().username(name).phoneNumber("1234567890").build();
    testContactDetailsRepository.save(expectedContactDetails1);
    ContactDetails expectedContactDetails2= ContactDetails.builder().username(name).phoneNumber("0987654321").build();
    testContactDetailsRepository.save(expectedContactDetails2);

    expectedContactDetailsList.add(expectedContactDetails1);
    expectedContactDetailsList.add(expectedContactDetails2);

    List<ContactDetails> resultContactDetailsList = testContactDetailsRepository.findByUsername(name);
    assertThat(resultContactDetailsList).isEqualTo(expectedContactDetailsList);

  }
}