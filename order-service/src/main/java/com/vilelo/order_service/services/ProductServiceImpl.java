package com.vilelo.order_service.services;

import com.vilelo.order_service.domain.Product;
import com.vilelo.order_service.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Transactional
    @Override
    public Product updateQuantityOnHand(Long id, Integer quantityOnHand) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setQuantityOnHand(quantityOnHand);
        return productRepository.saveAndFlush(product);
    }
}
