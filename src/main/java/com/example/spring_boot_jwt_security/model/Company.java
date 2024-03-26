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

        @OneToMany(mappedBy = "company")
        private List<Employee> employees;

        @OneToMany(mappedBy = "company")
        private List<Client> clients;
    }

