package com.example.CustomerService.services;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.repository.CustomerCredetialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerLoginService {

    CustomerCredetialsRepository credetialsRepository;
    CustomerLoginService(CustomerCredetialsRepository credetialsRepository){
        this.credetialsRepository = credetialsRepository;
    }

    public void login(Credentials credentials){
//        UserCredentials userCredentials=new UserCredentials();
//        userCredentials.setId(1);
//        userCredentials.setUsername(credentials.getUsername());
//        userCredentials.setPassword(credentials.getPassword());
        CustomerCredentials userCredentials= CustomerCredentials.builder().id(1).username(credentials.getUsername())
                        .password(credentials.getPassword()).build();
        credetialsRepository.save(userCredentials);
        System.out.println("user loggin in "+credentials.getUsername());
        log.info("user logged in is {}",credentials.getUsername());
    }

    public Credentials getUserDetails_name(String name) {
        CustomerCredentials userCredentials = credetialsRepository.findByUsername(name);
       return Credentials.builder().username(userCredentials.getUsername())
                .password(userCredentials.getPassword()).build();

    }

    public Credentials getUserDetails_password(String password) {
        CustomerCredentials userCredentials = credetialsRepository.findByPassword(password);
        return Credentials.builder().username(userCredentials.getUsername())
                .password(userCredentials.getPassword()).build();

    }


}
