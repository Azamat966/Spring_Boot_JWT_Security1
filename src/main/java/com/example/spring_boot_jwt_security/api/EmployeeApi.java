package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.EmployeeRequest;
import com.example.spring_boot_jwt_security.dto.response.EmployeeResponse;
import com.example.spring_boot_jwt_security.model.Employee;
import com.example.spring_boot_jwt_security.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeApi {
    private final EmployeeService service;


    @PostMapping("/api/v1/employee/save")
    public String saveEmployee(@RequestParam Long companyid,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String salary,@RequestParam String email){
        EmployeeRequest request = new EmployeeRequest();
        request.setCompanyid(companyid);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setSalary(salary);
        request.setEmail(email);
        service.save(request);

        return "Saved";
    }
    @GetMapping("/api/v1/employee/find/all")
    public List<Employee> getAll(){return service.findAll();}


    @GetMapping("/api/v1/employee/find/by{id}")
    public EmployeeResponse getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/employee/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted 77";
    }

}

