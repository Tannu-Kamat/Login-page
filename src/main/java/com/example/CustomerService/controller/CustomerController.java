package com.example.CustomerService.controller;

import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.services.CustomerLoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Slf4j
@RestController
public class CustomerController {

    CustomerLoginService loginService;

    CustomerController(CustomerLoginService service){
        this.loginService=service;
    }


    @Operation(summary = "Display customer details", description = "Get customer details by name : ")
    @GetMapping("/loginName/{name}")
    public Credentials showUserName(@PathVariable("name") String name) throws CustomerNotFoundException {
        return this.loginService.getUserDetails_name(name);

    }

    @Operation(summary = "Login customer", description = "Authenticate customer with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again")
    })
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody Credentials credential){
       this.loginService.login(credential);
        return new ResponseEntity<String>("user created successfully", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Display customer details", description = "Get customer details by password: ")
    @GetMapping("/loginPassword/{password}")
    public Credentials showUserPassword(@PathVariable("password") String password) throws PasswordNotFoundEcxeption {

        return this.loginService.getUserDetails_password(password);

    }



}
