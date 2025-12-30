package com.vilelo.sdjpacreditcard.repositories;


import com.vilelo.sdjpacreditcard.domain.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@DataJpaTest
class CreditCardRepositoryTest {

    final String CREDIT_CARD = "65765746546500000";

    @Autowired
    CreditCardRepository creditCardRepository;

    //@Rollback(false)
    @Test
    void testSaveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("856");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCreditCard = creditCardRepository.saveAndFlush(creditCard);

        CreditCard fetchedCreditCard = creditCardRepository.findById(savedCreditCard.getId()).orElseThrow();

        assertEquals(savedCreditCard.getCreditCardNumber(), fetchedCreditCard.getCreditCardNumber());

    }

}