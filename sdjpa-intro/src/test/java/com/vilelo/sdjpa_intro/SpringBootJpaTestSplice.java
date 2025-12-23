package com.vilelo.sdjpa_intro;

import com.vilelo.sdjpa_intro.domain.Book;
import com.vilelo.sdjpa_intro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class SpringBootJpaTestSplice {

    @Autowired
    private BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {

        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(0);

        bookRepository.save(new Book("My test book", "645634513", "Self"));

        long countAfter =  bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);

    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {

        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(1);

    }


}
