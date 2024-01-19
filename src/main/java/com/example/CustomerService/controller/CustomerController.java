package com.example.CustomerService.controller;

import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.services.CustomerLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    CustomerLoginService loginService;

    CustomerController(CustomerLoginService service){
        this.loginService=service;
    }


    @GetMapping("/test1")
    public void function1(){
        System.out.println("niyati dubey");
    }

    @PostMapping("/test2")
    public void function2(){
        System.out.println("hello world");
    }

    @PostMapping("/test3")
    public void function3(@RequestBody Credentials credential){
       this.loginService.login(credential);
    }


    @GetMapping("/test4/{name}")
    public Credentials function4(@PathVariable("name") String name){
        return this.loginService.getUserDetails_name(name);
       // System.out.println("hello user "+name);
    }

    @PostMapping("/test5")
    public ResponseEntity function5(@RequestBody Credentials credential){
        this.loginService.login(credential);
        return new ResponseEntity<String>("user created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/test6/{password}")
    public Credentials function6(@PathVariable("password") String password){
        return this.loginService.getUserDetails_password(password);
        // System.out.println("hello user "+name);
    }


}
