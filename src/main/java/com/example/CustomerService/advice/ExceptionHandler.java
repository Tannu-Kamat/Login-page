package com.example.CustomerService.advice;
import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import com.example.CustomerService.model.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    ResponseDTO response=new ResponseDTO();

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleInvalidArguments(MethodArgumentNotValidException ex){
        String message="";
        message=ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
        response.setMessage(message);
        log.error(message);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidInput(HttpMessageNotReadableException e) {
        log.error("Invalid request payload.Try again with valid credentials");
        return ResponseEntity.badRequest().body("Invalid request payload.Try again with valid credentials");
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNameNotFoundError(CustomerNotFoundException ex){
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(PasswordNotFoundEcxeption.class)
    public ResponseEntity<ResponseDTO> handlePasswordNotFoundError(PasswordNotFoundEcxeption ex){
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        response.setMessage("already exist ! try with something else");
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);

}

}
