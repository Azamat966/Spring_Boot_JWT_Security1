package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_security.dto.response.CompanyResponse;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.repository.CompanyRepository;
import com.example.spring_boot_jwt_security.repository.DirectorRepository;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final DirectorRepository directorRepository;

    public void save(CompanyRequest request) {
        Company company = new Company();
        Optional<Director> director = directorRepository.findById(request.getDirectorid());
        company.setDirector(director.get());
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
            return response;
        }
    public void deleteByID(Long id){companyRepository.deleteById(id);}

}



