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
    public class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    private String address;
    private String phone;
    private String name;
    private String firstName;
    private String lastName;


        @OneToMany(mappedBy = "client")
        private List<Orders> orders;

        @ManyToOne
        private Company company;

    }

