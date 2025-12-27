package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.OrderHeader;
import com.vilelo.order_service.domain.OrderLine;
import com.vilelo.order_service.domain.Product;
import com.vilelo.order_service.domain.enums.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("New Product");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void saveOrder_WithLine_Success() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        //orderHeader.setOrderLines(Set.of(orderLine));
        //orderLine.setOrderHeader(orderHeader);
        orderHeader.addOrderLine(orderLine);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        orderHeaderRepository.flush();

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(1, savedOrder.getOrderLines().size());

        OrderHeader fetchedOrder = orderHeaderRepository.findById(savedOrder.getId()).get();
        assertNotNull(fetchedOrder);
    }

    @Test
    void saveOrder_Success() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        Optional<OrderHeader> fetchedOrder = orderHeaderRepository.findById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertTrue(fetchedOrder.isPresent());
        assertNotNull(fetchedOrder.get().getCreatedDate());
        assertNotNull(fetchedOrder.get().getLastModifiedDate());
    }
}