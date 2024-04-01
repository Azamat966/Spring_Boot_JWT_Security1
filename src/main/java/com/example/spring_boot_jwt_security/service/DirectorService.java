package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.DirectorRequest;
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
        director.setFirstName(request.getFirst_name());
        director.setAge(request.getAge());
        directorRepository.save(director);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }


        public Director getById(Long id) {
            return directorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
        }
    public void deleteByID(Long id){directorRepository.deleteById(id);}

}
