package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.dto.response.OrdersResponse;
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
        orders.setOrder(request.getOrder());
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
            response.setOrder(orders.getOrder());
            //response.setClientd(orders.getClientId());
            return response;
        }
    public void deleteByID(Long id){ordersRepository.deleteById(id);}


    int count;
    public int findAlllkjhg (){
        int count = 0;
        for (Orders orders: ordersRepository.findAll()){
            count++;
        }
        return count;
    }

}


