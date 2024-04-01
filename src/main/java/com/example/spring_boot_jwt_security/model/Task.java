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
    public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    private String tittle;
    private String description;

        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;


        @ManyToOne
        @JoinColumn(name = "orders_id")
        private Orders orders;

    }

