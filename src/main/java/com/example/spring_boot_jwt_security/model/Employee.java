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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String salary;


    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private Company company;
//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    private Task task;

//    @ManyToOne
//    private Orders orders;
}

