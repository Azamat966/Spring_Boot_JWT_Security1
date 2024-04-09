package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.dto.response.OrdersResponse;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.repository.ClientRepository;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final ClientRepository clientRepository;

    public void save(OrdersRequest request) {
        Orders orders = new Orders();
        Optional<Client> client = clientRepository.findById(request.getClientId());
        orders.setClient(client.get());

        orders.setOrderName(request.getOrder());
//        orders.setOrder(request.getOrder());
        // orders.setClientId(request.getClientId());
        ordersRepository.save(orders);

    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }


    public OrdersResponse getById(Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
        OrdersResponse response = new OrdersResponse("Order saved successfully");
//            response.setOrder(orders.getOrder());
        //response.setClientd(orders.getClientId());
        return response;
    }

    public void deleteByID(Long id) {
        ordersRepository.deleteById(id);


    }
}



