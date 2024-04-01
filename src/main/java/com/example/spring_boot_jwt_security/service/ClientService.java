package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.ClientRequest;
import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Employee;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.repository.ClientRepository;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void save(ClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setCompanyId(request.getCompanyId());
        clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }


    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
    }

    public void deleteByID(Long id){clientRepository.deleteById(id);}

}
