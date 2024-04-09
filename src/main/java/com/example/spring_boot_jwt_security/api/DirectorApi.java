package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
import com.example.spring_boot_jwt_security.dto.response.DirectorResponse;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DirectorApi {
    private final DirectorService service;


    @PostMapping("/api/v1/director/save")
    public String saveDirector(@RequestBody DirectorRequest request){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/api/v1/director/find/all")
    public List<Director> getAll(){return service.findAll();}


    @GetMapping("/api/v1/director/find/by{id}")
    public DirectorResponse getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/director/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted 77";
    }

}
