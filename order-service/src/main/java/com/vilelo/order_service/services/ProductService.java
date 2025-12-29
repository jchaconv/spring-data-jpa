package com.vilelo.order_service.services;

import com.vilelo.order_service.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQuantityOnHand(Long id, Integer quantityOnHand);

}
