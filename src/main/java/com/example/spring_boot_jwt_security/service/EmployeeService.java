package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.EmployeeRequest;
import com.example.spring_boot_jwt_security.dto.response.EmployeeResponse;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Employee;
import com.example.spring_boot_jwt_security.repository.CompanyRepository;
import com.example.spring_boot_jwt_security.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public void save(EmployeeRequest request) {
        Employee employee = new Employee();
        Optional<Company> company = companyRepository.findById(request.getCompanyid());
        employee.setCompany(company.get());



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


    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
        EmployeeResponse response = new EmployeeResponse();
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        return response;
    }

    public void deleteByID(Long id){employeeRepository.deleteById(id);}

}


