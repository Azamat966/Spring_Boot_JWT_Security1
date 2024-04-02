package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_security.dto.response.CompanyResponse;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.repository.CompanyRepository;
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



        public CompanyResponse getById(Long id) {
            Company company = companyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
            CompanyResponse response = new CompanyResponse();
            response.setAddress(company.getAddress());
            response.setDirectorld(company.getDirectorld());
            response.setCompanyName(company.getCompanyName());
            response.setPhone(company.getPhone());
            response.setDirectorName(company.getDirectorName());
            return response;
        }
    public void deleteByID(Long id){companyRepository.deleteById(id);}

}



