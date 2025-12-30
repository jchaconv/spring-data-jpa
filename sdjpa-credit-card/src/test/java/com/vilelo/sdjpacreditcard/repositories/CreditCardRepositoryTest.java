package com.vilelo.sdjpacreditcard.repositories;


import com.vilelo.sdjpacreditcard.domain.CreditCard;
import com.vilelo.sdjpacreditcard.services.EncryptionService;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
@SpringBootTest
class CreditCardRepositoryTest {

    final String CREDIT_CARD = "6576574600000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void test() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("Getting CC from database: " + savedCC.getCreditCardNumber());

        System.out.println("CC At Rest");
        System.out.println("CC Encrypted: " + encryptionService.encrypt(CREDIT_CARD));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card " +
                "WHERE id = " + savedCC.getId());

        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertThat(savedCC.getCreditCardNumber()).isNotEqualTo(dbCardValue);
        assertThat(dbCardValue).isEqualTo(encryptionService.encrypt(CREDIT_CARD));

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).get();

        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
    }

    @Disabled
    @Test
    void saveAndStoreCreditCard_Encryption() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("856");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCreditCard = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("==== Getting CC from database: " + savedCreditCard.getCreditCardNumber());

        System.out.println("==== CC At Rest");
        System.out.println("CC encrypted: " + encryptionService.encrypt(CREDIT_CARD));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card WHERE id = " + savedCreditCard.getId());

        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertNotEquals(savedCreditCard.getCreditCardNumber(), dbCardValue);
        assertEquals(dbCardValue, encryptionService.encrypt(dbCardValue));

        CreditCard fetchedCreditCard = creditCardRepository.findById(savedCreditCard.getId()).orElseThrow();

        assertEquals(savedCreditCard.getCreditCardNumber(), fetchedCreditCard.getCreditCardNumber());
    }

    //@Rollback(false)
    @Test
    void saveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("856");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCreditCard = creditCardRepository.saveAndFlush(creditCard);
        CreditCard fetchedCreditCard = creditCardRepository.findById(savedCreditCard.getId()).orElseThrow();

        assertEquals(savedCreditCard.getCreditCardNumber(), fetchedCreditCard.getCreditCardNumber());
    }

}