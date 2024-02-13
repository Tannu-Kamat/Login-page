package com.example.CustomerService.controller;

import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.model.CustomerContact;
import com.example.CustomerService.model.ResponseDTO;
import com.example.CustomerService.services.ContactDetailsService;
import com.example.CustomerService.services.CustomerLoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin
@RestController
public class CustomerController {

    CustomerLoginService loginService;
    ContactDetailsService contactDetailsService;

    ResponseDTO response=new ResponseDTO();
    CustomerController(CustomerLoginService service, ContactDetailsService contactService) {

        this.loginService = service;
        this.contactDetailsService = contactService;
    }




    @Operation(summary = "Login customer", description = "Authenticate customer with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "customer logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again"),
            @ApiResponse(responseCode = "401", description = "enter valid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody Credentials credential) throws CustomerNotFoundException,ExecutionException, InterruptedException  {
        log.info("Thread info: {}",Thread.currentThread());

        CompletableFuture<Void> loginFuture = loginService.login(credential);
        loginFuture.get();
        ResponseDTO response=new ResponseDTO();
        response.setMessage("user logged in successfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }


    @Operation(summary = "Customer Contact details", description = "Adding customer contact details and username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "customer contact added successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again"),
            @ApiResponse(responseCode = "401", description = "enter valid credentials")
    })
    @PostMapping("/addContact/{name}")
    public ResponseEntity<?> addContact(@PathVariable("name") String name, @Valid @RequestBody CustomerContact customerContact)throws CustomerNotFoundException, ExecutionException, InterruptedException  {
        if(!customerContact.getUsername().equals(name)){
            log.info("enter valid credentials");
            throw new CustomerNotFoundException("given name does not match with username");
        }
        CompletableFuture<Void> addContactFuture = contactDetailsService.addContact(name, customerContact);
        addContactFuture.get();
        response.setMessage("customer's contact detail added");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @Operation(summary = "Display customer details", description = "Get customer details by name : ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "402", description = "user doesn't exist"),
            @ApiResponse(responseCode = "200", description = "user fetched successfully"),

    })
    @GetMapping("/loginName/{name}")
    public CompletableFuture<Credentials> showUserName(@PathVariable("name") String name) throws CustomerNotFoundException {
        return this.loginService.getUserDetails_name(name);

    }

    @Operation(summary = "Display customer details", description = "Get customer details by password: ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "402", description = "user doesn't exist"),
            @ApiResponse(responseCode = "200", description = "user fetched successfully"),

    })
    @GetMapping("/loginPassword/{password}")
    public CompletableFuture<Credentials> showUserPassword(@PathVariable("password") String password) throws PasswordNotFoundEcxeption {
        return this.loginService.getUserDetails_password(password);

    }

    @Operation(summary = "Display customer contact details", description = "Get customer contact details by username: ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "402", description = "user doesn't exist"),
            @ApiResponse(responseCode = "200", description = "user fetched successfully"),

    })
    @GetMapping("/getContact/{name}")
    public CompletableFuture<List<CustomerContact>> showContactDetails(@PathVariable("name") String name) throws CustomerNotFoundException {
        return this.contactDetailsService.getUserContact(name);
    }


}
