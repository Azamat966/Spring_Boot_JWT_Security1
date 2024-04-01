package com.example.spring_boot_jwt_security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String phone;
    private String address;
    private String directorld;
    private String directorName;



    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Client> clients;

    @OneToOne
    @JoinColumn(name = "director_id")
    private Director director;

}