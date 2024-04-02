package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.ClientRequest;
import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;//Azamat7
import com.example.spring_boot_jwt_security.dto.response.ClientResponse;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.service.ClientService;
import com.example.spring_boot_jwt_security.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientApi {
    private final ClientService service;


    @PostMapping("/api/v1/client/save")
    public String saveClient(@RequestBody ClientRequest request){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/api/v1/client/find/all")
    public List<Client> getAll(){return service.findAll();}


    @GetMapping("/api/v1/client/find/by{id}")
    public ClientResponse getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/client/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted sucsecsful";
    }

}
