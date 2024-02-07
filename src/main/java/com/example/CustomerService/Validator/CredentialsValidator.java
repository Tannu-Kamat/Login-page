package com.example.CustomerService.Validator;

import com.example.CustomerService.model.Credentials;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CredentialsValidator implements Validator {

    @Override
    public boolean supports(Class<?> c){
        return Credentials.class.equals(c);
    }

    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.empty");
        Credentials credentials=(Credentials) target;
    }

}
