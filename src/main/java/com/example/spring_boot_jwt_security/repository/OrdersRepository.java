package com.example.spring_boot_jwt_security.repository;

import com.example.spring_boot_jwt_security.model.Director;
import com.example.spring_boot_jwt_security.model.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
