package com.vilelo.order_service.repositories;

import com.vilelo.order_service.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
