package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.Product;
import com.vilelo.order_service.domain.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void getCategory_Success() {
        Product product = productRepository.findByDescription("PRODUCT1").get();
        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void saveProduct_Success() {
        Product product = new Product();
        product.setDescription("New Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.save(product);

        Optional<Product> fetchedProduct = productRepository.findById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.get().getDescription());
        assertNotNull(fetchedProduct.get().getCreatedDate());
        assertNotNull(fetchedProduct.get().getLastModifiedDate());
    }

    @Test
    void addAndUpdateProduct_Success() {
        Product product = new Product();
        product.setDescription("New Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.saveAndFlush(product);

        savedProduct.setQuantityOnHand(25);

        Product savedProduct2 = productRepository.saveAndFlush(savedProduct);

        System.out.println("==== savedProduct2.getId():" + savedProduct2.getId());

    }

}