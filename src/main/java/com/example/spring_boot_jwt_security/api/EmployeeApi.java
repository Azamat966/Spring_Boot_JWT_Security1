package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
import com.example.spring_boot_jwt_security.dto.request.EmployeeRequest;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.model.Employee;
import com.example.spring_boot_jwt_security.service.DirectorService;
import com.example.spring_boot_jwt_security.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeApi {
    private final EmployeeService service;


    @PostMapping("/api/v1/employee/save")
    public String saveEmployee(@RequestBody EmployeeRequest request, @RequestParam String id){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/api/v1/employee/find/all")
    public List<Employee> getAll(){return service.findAll();}


    @GetMapping("/api/v1/employee/find/by{id}")
    public Employee getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/employee/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted 77";
    }

}

