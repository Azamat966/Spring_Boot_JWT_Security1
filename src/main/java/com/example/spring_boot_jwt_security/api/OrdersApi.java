package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.dto.response.OrdersResponse;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.spring_boot_jwt_security.dto.request.OrdersRequest;
import com.example.spring_boot_jwt_security.dto.response.OrdersResponse;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class OrdersApi {
    private final OrderService service;


    @PostMapping("/api/v1/orders/save")
    public String saveOrders(@RequestBody OrdersRequest request) {
        service.save(request);
        return "Saved";
    }

    @GetMapping("/api/v1/orders/find")
    public List<Orders> getAll() {
        return service.findAll();
    }


    @GetMapping("/api/v1/orders/find/by/{id}")
    public OrdersResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/api/v1/orders/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteByID(id);
        return "deleted 77";
    }

}

