package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.service.CompanyService;
import com.example.spring_boot_jwt_security.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService service;


    @PostMapping("/api/v1/company/save")
    public String saveCompany(@RequestBody CompanyRequest request){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/api/v1/company/find/all")
    public List<Company> getAll(){return service.findAll();}


    @GetMapping("/api/v1/company/find/by{id}")
    public Company getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/company/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted sucsecsful";
    }

}

