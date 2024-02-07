package com.example.CustomerService.Validator;

import com.example.CustomerService.model.Credentials;
import com.example.CustomerService.model.CustomerContact;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerContactValidator implements Validator {

    @Override
    public boolean supports(Class<?> c){
        return Credentials.class.equals(c);
    }
    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber","phoneNumber.empty");
        CustomerContact customerContact=(CustomerContact) target;
    }
}
