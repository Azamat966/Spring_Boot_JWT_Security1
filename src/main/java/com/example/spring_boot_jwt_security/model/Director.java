package com.example.spring_boot_jwt_security.model;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
    public class Director {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    private String age;
    private String gmail;
    private String First_name;
    private String last_Name;


    @OneToOne(mappedBy = "director",cascade = CascadeType.ALL)
        private Company company;

}
