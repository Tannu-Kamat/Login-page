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

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class CustomerCredentials {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(targetEntity = ContactDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<ContactDetails> CustomerContactDetails;
}
