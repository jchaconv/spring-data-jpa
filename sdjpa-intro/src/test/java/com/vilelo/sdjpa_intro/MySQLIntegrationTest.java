package com.vilelo.sdjpa_intro;

import com.vilelo.sdjpa_intro.domain.AuthorUuid;
import com.vilelo.sdjpa_intro.domain.BookUuid;
import com.vilelo.sdjpa_intro.repositories.AuthorUuidRepository;
import com.vilelo.sdjpa_intro.repositories.BookRepository;
import com.vilelo.sdjpa_intro.repositories.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@ComponentScan(basePackages = {"com.vilelo.sdjpa_intro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Test
    void testBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId()).isNotNull();

        Optional<BookUuid> fetched = bookUuidRepository.findById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        Optional<AuthorUuid> fetched = authorUuidRepository.findById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }


}
