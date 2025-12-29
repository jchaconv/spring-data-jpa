package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.*;
import com.vilelo.order_service.domain.enums.ProductStatus;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;
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

    @Autowired
    CustomerRepository customerRepository;

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
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        Customer savedCustomer = customerRepository.save(customer);

        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        //orderHeader.setOrderLines(Set.of(orderLine));
        //orderLine.setOrderHeader(orderHeader);
        orderHeader.addOrderLine(orderLine);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        //OrderApproval savedOrderApproval = orderApprovalRepository.save(orderApproval);
        //orderHeader.setOrderApproval(savedOrderApproval);
        orderHeader.setOrderApproval(orderApproval);

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
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        orderHeader.setCustomer(customerRepository.save(customer));
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        Optional<OrderHeader> fetchedOrder = orderHeaderRepository.findById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertTrue(fetchedOrder.isPresent());
        assertNotNull(fetchedOrder.get().getCreatedDate());
        assertNotNull(fetchedOrder.get().getLastModifiedDate());
    }

    @Test
    void deleteOrderHeader_Cascade_Success() {

        Customer customer = new Customer();
        customer.setCustomerName("New Customer");

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        orderHeader.addOrderLine(orderLine);

        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("====== Order saved and flushed");

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();

        assertThrows(NoSuchElementException.class, () -> {
            orderHeaderRepository.findById(savedOrder.getId()).orElseThrow();
        });

    }

    @Test
    void deleteOrderApproval_Cascade_Success() {

        Customer customer = new Customer();
        customer.setCustomerName("New Customer");

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("Julio Chacon");

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        orderHeader.addOrderLine(orderLine);
        orderHeader.setOrderApproval(orderApproval);

        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("====== Order saved and flushed");

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();

        assertThrows(NoSuchElementException.class, () -> {
            orderHeaderRepository.findById(savedOrder.getId()).orElseThrow();
        });

    }

    @Test
    void saveCustomer_ValidationErrors() {

        Address address = new Address();
        address.setAddress("New Address 0123445678890 0123445678890 0123445678890");
        address.setCity("New City 0123445678890 0123445678890 0123445678890");
        address.setState("New State 0123445678890 0123445678890 0123445678890");
        address.setZipCode("12345 0123445678890 0123445678890 0123445678890");

        Customer customer = new Customer();
        customer.setCustomerName("New Customer 012456778890012456778890012456778890012456778890012456778890012456778890");
        customer.setPhone("123456789012345678901234567890");
        customer.setEmail("juliochacon.com");

        customer.setAddress(address);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            customerRepository.save(customer);
        });

        System.out.println(exception.getMessage());
        assertNotNull(exception.getMessage());

    }
}