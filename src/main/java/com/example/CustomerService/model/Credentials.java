package com.example.CustomerService.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

  @NotEmpty(message = "username can not be empty")
    private String username;
  @NotEmpty(message = "password can not be empty")
  @Size(min=4, message = "password should be minimum of 4 characters")

    private String password;



}
