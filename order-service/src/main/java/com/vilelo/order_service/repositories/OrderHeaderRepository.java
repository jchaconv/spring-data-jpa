package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.Customer;
import com.vilelo.order_service.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findAllByCustomer(Customer customer);

}
