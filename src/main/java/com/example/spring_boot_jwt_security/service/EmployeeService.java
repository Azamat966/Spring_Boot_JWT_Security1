package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
import com.example.spring_boot_jwt_security.dto.request.EmployeeRequest;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.model.Employee;
import com.example.spring_boot_jwt_security.repository.DirectorRepository;
import com.example.spring_boot_jwt_security.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void save(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());

        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
    }

    public void deleteByID(Long id){employeeRepository.deleteById(id);}

}


