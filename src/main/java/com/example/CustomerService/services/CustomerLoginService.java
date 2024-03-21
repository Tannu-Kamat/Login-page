package com.example.CustomerService.services;
import com.example.CustomerService.Validator.CredentialsValidator;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
public class CustomerLoginService {

    CustomerCredetialsRepository credetialsRepository;
    CredentialsValidator credentialsvalidator;
    CustomerLoginService(CustomerCredetialsRepository credetialsRepository,CredentialsValidator credentialsvalidator){
        this.credetialsRepository = credetialsRepository;
        this.credentialsvalidator= credentialsvalidator;
    }



    public void login(Credentials credentials){

        CustomerCredentials userCredentials= CustomerCredentials.builder().username(credentials.getUsername())
            .password(credentials.getPassword()).build();
        credetialsRepository.save(userCredentials);
        log.info("user logged in is {}",credentials.getUsername());
    }


    public Credentials getUserDetails_name(String name) throws CustomerNotFoundException {
        CustomerCredentials userCredentials = credetialsRepository.findByUsername(name);

        if(userCredentials!=null){
            log.info("user logged in is : {}",userCredentials.getUsername());
            return Credentials.builder().username(userCredentials.getUsername())
                .password(userCredentials.getPassword()).build();
        }
        else{
            log.info("customer not found in database with given name {}",name);
            throw new CustomerNotFoundException(name+" not found in database");
        }



    }


    public Credentials getUserDetails_password(String password) throws PasswordNotFoundEcxeption {
        CustomerCredentials userCredentials = credetialsRepository.findByPassword(password);

        if (userCredentials != null) {
            log.info("user logged in is : {} with given password", userCredentials.getUsername());
            return Credentials.builder().username(userCredentials.getUsername())
                .password(userCredentials.getPassword()).build();
        } else {
            log.info("customer not found in database with given password {}", password);
            throw new PasswordNotFoundEcxeption(
                "customer not found in database with given password");

        }
    }

}
