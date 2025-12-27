package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.OrderHeader;
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
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    void saveOrder_Success() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        Optional<OrderHeader> fetchedOrder = orderHeaderRepository.findById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.get().getId());
    }



}