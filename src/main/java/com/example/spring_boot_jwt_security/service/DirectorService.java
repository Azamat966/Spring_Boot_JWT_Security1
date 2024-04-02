package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
import com.example.spring_boot_jwt_security.dto.response.DirectorResponse;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public void save(DirectorRequest request) {
        Director director  = new Director();
        director.setFirst_name(request.getFirst_name());
        director.setAge(request.getAge());
        directorRepository.save(director);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }


        public DirectorResponse getById(Long id) {
            Director director = directorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
            DirectorResponse response = new DirectorResponse();
            response.setLast_name(director.getLast_Name());
            response.setFirst_name(director.getFirst_name());
            response.setAge(director.getAge());
            response.setGmail(director.getGmail());
            return response;
        }
    public void deleteByID(Long id){directorRepository.deleteById(id);}

}
