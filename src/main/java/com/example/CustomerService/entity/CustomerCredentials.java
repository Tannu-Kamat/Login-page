package com.example.CustomerService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerCredentials {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;
}
