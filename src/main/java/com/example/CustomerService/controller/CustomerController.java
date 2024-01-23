package com.example.CustomerService.controller;

import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.services.CustomerLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class CustomerController {

    CustomerLoginService loginService;

    CustomerController(CustomerLoginService service){
        this.loginService=service;
    }




//    @PostMapping("/login")
//    public void function3(@RequestBody Credentials credential){
//       this.loginService.login(credential);
//    }
//

    @Operation(summary = "Display customer details", description = "Get customer details by name : ")
    @GetMapping("/loginName/{name}")
    public Credentials showUserName(@PathVariable("name") String name){
        return this.loginService.getUserDetails_name(name);
       // System.out.println("hello user "+name);
    }

    @Operation(summary = "Login customer", description = "Authenticate customer with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Not found! Try Again")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Credentials credential){
       this.loginService.login(credential);
        return new ResponseEntity<String>("user created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Display customer details", description = "Get customer details by password: ")
    @GetMapping("/loginPassword/{password}")
    public Credentials showUserPassword(@PathVariable("password") String password){
        return this.loginService.getUserDetails_password(password);
        // System.out.println("hello user "+name);
    }


}
