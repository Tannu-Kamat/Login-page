package com.example.CustomerService.controller;

import com.example.CustomerService.entity.ContactDetails;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.model.CustomerContact;
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
import java.util.concurrent.ExecutionException;

@Slf4j
@CrossOrigin
@RestController
public class CustomerController {

    CustomerLoginService loginService;
    ContactDetailsService contactDetailsService;

    CustomerController(CustomerLoginService service,ContactDetailsService contactService){

        this.loginService=service;
        this.contactDetailsService=contactService;
    }


    @Operation(summary = "Display customer details", description = "Get customer details by name : ")
    @GetMapping("/loginName/{name}")
    public Credentials showUserName(@PathVariable("name") String name) throws CustomerNotFoundException {
        return this.loginService.getUserDetails_name(name);

    }

    @Operation(summary = "Login customer", description = "Authenticate customer with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "customer logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again")
    })
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody Credentials credential){
       this.loginService.login(credential);
        return new ResponseEntity<String>("user created successfully", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Customer Contact details", description = "Adding customer contact details and username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "customer logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again")
    })
    @PostMapping("/addContact/{name}")
    public ResponseEntity addContact(@PathVariable("name") String name, @Valid @RequestBody CustomerContact customerContact)throws CustomerNotFoundException {
        if(!customerContact.getUsername().equals(name)){
            log.info("enter valid credentials");
            throw new CustomerNotFoundException("given name does not match with username");
        }

        this.contactDetailsService.addContact(name,customerContact);
        return new ResponseEntity<String>("contact details added successfully",HttpStatus.ACCEPTED);
    }


    @Operation(summary = "Display customer details", description = "Get customer details by password: ")
    @GetMapping("/loginPassword/{password}")
    public Credentials showUserPassword(@PathVariable("password") String password) throws PasswordNotFoundEcxeption {

        return this.loginService.getUserDetails_password(password);

    }

    @Operation(summary = "Display customer contact details", description = "Get customer contact details by username: ")
    @GetMapping("/getContact/{name}")
    public List<CustomerContact> showContactDetails(@PathVariable("name") String name) throws CustomerNotFoundException{

        return this.contactDetailsService.getUserContact(name);

    }





}
