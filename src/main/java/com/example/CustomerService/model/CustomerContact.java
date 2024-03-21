package com.example.CustomerService.model;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"phoneNumber"})})
public class CustomerContact {

    @NotEmpty(message = " username can not be empty")
    private String username;

    @Size(min=10,max=10, message = "phone number should be of 10 digit")
    @NotEmpty(message = " phone number field can not be empty")
    private String phoneNumber;

}
