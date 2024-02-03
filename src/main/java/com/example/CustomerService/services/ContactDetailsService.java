package com.example.CustomerService.services;

import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.repository.ContactDetailsRepository;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactDetailsService {
    ContactDetailsRepository contactDetailsRepository;
    CustomerCredetialsRepository customerCredetialsRepository;

    ContactDetailsService(ContactDetailsRepository contactDetailsRepository, CustomerCredetialsRepository customerCredetialsRepository){
        this.contactDetailsRepository=contactDetailsRepository;
        this.customerCredetialsRepository=customerCredetialsRepository;
    }

    public void addContact(String name, CustomerContact customerContact) throws CustomerNotFoundException{
        CustomerCredentials findCustomer=customerCredetialsRepository.findByUsername(customerContact.getUsername());

        if(findCustomer==null){
            throw new CustomerNotFoundException(customerContact.getUsername()+" not found in database");
        }
        ContactDetails contactDetails=ContactDetails.builder().username(customerContact.getUsername())
                .phoneNumber(customerContact.getPhoneNumber()).build();
        contactDetailsRepository.save(contactDetails);
        log.info("contact added for user : {}",customerContact.getUsername());
    }

    public List<CustomerContact> getUserContact(String name) throws CustomerNotFoundException{
        List<ContactDetails> contactDetails =  contactDetailsRepository.findByUsername(name);

            if(!contactDetails.isEmpty()){

                return contactDetails.stream()
                        .map(contactDetails1 -> CustomerContact.builder()
                                .username(contactDetails1.getUsername())
                                .phoneNumber(contactDetails1.getPhoneNumber())
                                .build())
                        .collect(Collectors.toList());

            }
            else{
                log.error("Customer not found in database with given name {}",name);
                throw new CustomerNotFoundException(name+" not found in database");

            }


    }
}
