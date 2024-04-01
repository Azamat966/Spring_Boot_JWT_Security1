package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.repository.CompanyRepository;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void save(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setAddress(request.getAddress());
        company.setPhone(request.getPhone());
        company.setDirectorld(request.getDirectorld());
        companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }



        public Company getById(Long id) {
            return companyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
        }
    public void deleteByID(Long id){companyRepository.deleteById(id);}

}



