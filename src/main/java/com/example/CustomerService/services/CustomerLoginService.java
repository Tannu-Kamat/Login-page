package com.example.CustomerService.services;
import com.example.CustomerService.entity.CustomerCredentials;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
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

        CustomerCredentials userCredentials= CustomerCredentials.builder().username(credentials.getUsername())
                        .password(credentials.getPassword()).build();
        credetialsRepository.save(userCredentials);
        System.out.println("user loggin in "+credentials.getUsername());
        log.info("user logged in is {}",credentials.getUsername());
    }

    public Credentials getUserDetails_name(String name) throws CustomerNotFoundException {
        CustomerCredentials userCredentials = credetialsRepository.findByUsername(name);
        if(name!=null && !name.isEmpty()){
            if(userCredentials!=null){
                return Credentials.builder().username(userCredentials.getUsername())
                        .password(userCredentials.getPassword()).build();
            }
            else{
                log.info("customer not found in database with given name {}",name);
                throw new CustomerNotFoundException(name+" not found in database");
            }

        }
else{
            return Credentials.builder().username("not found")
                    .password("not found").build();
        }


    }

    public Credentials getUserDetails_password(String password) throws PasswordNotFoundEcxeption {
        CustomerCredentials userCredentials = credetialsRepository.findByPassword(password);
       if(password==null){
           return Credentials.builder().username("not found")
                   .password("not found").build();
       }

       else{
           if(userCredentials!=null){
               return Credentials.builder().username(userCredentials.getUsername())
                       .password(userCredentials.getPassword()).build();
           }
           else{
               log.info("customer not found in database with given password {}",password);
               throw new PasswordNotFoundEcxeption("customer not found in database with given password");

           }
       }



    }


}
