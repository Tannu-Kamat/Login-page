package com.example.CustomerService.services;
import com.example.CustomerService.Validator.CustomerContactValidator;
import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.repository.ContactDetailsRepository;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactDetailsService {
    ContactDetailsRepository contactDetailsRepository;
    CustomerCredetialsRepository customerCredetialsRepository;
    CustomerContactValidator customerContactValidator;

    ContactDetailsService(ContactDetailsRepository contactDetailsRepository, CustomerCredetialsRepository customerCredetialsRepository,CustomerContactValidator customerContactValidator){
        this.contactDetailsRepository=contactDetailsRepository;
        this.customerCredetialsRepository=customerCredetialsRepository;
        this.customerContactValidator=customerContactValidator;
    }

    @Async("MultiRequestAsyncThread")
    public  CompletableFuture<Void> addContact(String name, CustomerContact customerContact) throws CustomerNotFoundException{
        CustomerCredentials findCustomer=customerCredetialsRepository.findByUsername(customerContact.getUsername());

        if(findCustomer==null){
            throw new CustomerNotFoundException(customerContact.getUsername()+" not found in database");
        }
        return CompletableFuture.supplyAsync(()->{
            Errors errors= new BeanPropertyBindingResult(customerContact,"customer contact");
            customerContactValidator.validate(customerContact,errors);

            ContactDetails contactDetails=ContactDetails.builder().username(customerContact.getUsername())
                    .phoneNumber(customerContact.getPhoneNumber()).build();
            contactDetailsRepository.save(contactDetails);
            log.info("contact added for user : {}",customerContact.getUsername());
            return null;
        });

    }

    @Async("MultiRequestAsyncThread")
    public CompletableFuture<List<CustomerContact>> getUserContact(String name) throws CustomerNotFoundException{

        List<ContactDetails> contactDetails =  contactDetailsRepository.findByUsername(name);
        if(contactDetails.isEmpty()){
            log.error("Customer not found in database with given name {}",name);
               throw new CustomerNotFoundException(name+" not found in database");
        }
      return CompletableFuture.supplyAsync(()->{

               return contactDetails.stream()
                       .map(contactDetails1 -> CustomerContact.builder()
                               .username(contactDetails1.getUsername())
                               .phoneNumber(contactDetails1.getPhoneNumber())
                               .build())
                       .collect(Collectors.toList());


       });

    }

}