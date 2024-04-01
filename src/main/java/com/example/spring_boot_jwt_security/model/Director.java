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
    private String firstName;
    private String lastName;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToOne(mappedBy = "director",cascade = CascadeType.ALL)
        private Company company;
    }
