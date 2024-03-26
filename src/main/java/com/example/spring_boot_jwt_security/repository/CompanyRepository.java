package com.example.spring_boot_jwt_security.repository;

import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
