package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);

}
