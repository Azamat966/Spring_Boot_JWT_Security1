package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public void save(OrdersRequest request) {
        Orders orders = new Orders();
        orders.setFirst_name(request.getFirst_name());
        orders.setLast_name(request.getLast_name());
        orders.setAge(request.getAge());
        ordersRepository.save(orders);
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }



        public Orders getById(Long id) {
            return ordersRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Can't find employee with this id: " + id));
        }
    public void deleteByID(Long id){ordersRepository.deleteById(id);}

}

