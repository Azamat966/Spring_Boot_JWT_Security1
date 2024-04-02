package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.ClientRequest;
import com.example.spring_boot_jwt_security.dto.response.ClientResponse;
import com.example.spring_boot_jwt_security.dto.response.OrdersResponse;
import com.example.spring_boot_jwt_security.model.Client;
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
    private final OrdersRepository ordersRepository;

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


    public ClientResponse getById(Long id) {
         Client client =clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
         ClientResponse client1 = new ClientResponse();
         client1.setId(client.getId());
         client1.setName(client.getName());
         client1.setEmail(client.getEmail());
         client1.setCompanyId(client.getCompanyId());
         return client1;



        //Orders orders = ordersRepository.getById(client.getOrderId);
         //client.setOrderName(orders.getName);

    }

    public void deleteByID(Long id){clientRepository.deleteById(id);}

}
