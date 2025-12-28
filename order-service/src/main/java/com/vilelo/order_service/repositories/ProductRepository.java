package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);

}
