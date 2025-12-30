package com.vilelo.sdjpacreditcard.repositories;

import com.vilelo.sdjpacreditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
