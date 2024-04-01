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
        private String companyId;
        private String email;
        private String name;

        @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
        private List<Orders> orders;

        @ManyToOne
        @JoinColumn(name = "company_client_id")
        private Company company;

    }

