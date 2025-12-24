package com.vilelo.sdjpa_intro;

import com.vilelo.sdjpa_intro.domain.AuthorUuid;
import com.vilelo.sdjpa_intro.domain.BookNatural;
import com.vilelo.sdjpa_intro.domain.BookUuid;
import com.vilelo.sdjpa_intro.domain.composite.AuthorComposite;
import com.vilelo.sdjpa_intro.domain.composite.AuthorEmbedded;
import com.vilelo.sdjpa_intro.domain.composite.NameId;
import com.vilelo.sdjpa_intro.repositories.*;
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

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testAuthorEmbedded() {
        NameId nameId = new NameId("Julio", "Chacon");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);
        authorEmbedded.setCountry("PE");

        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        Optional<AuthorEmbedded> fetched = authorEmbeddedRepository.findById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorComposite() {
        NameId nameId = new NameId("Julio", "Chacon");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("PE");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        Optional<AuthorComposite> fetched = authorCompositeRepository.findById(nameId);
        assertThat(fetched).isNotNull();

    }

    @Test
    void testBookNatural() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        Optional<BookNatural> fetched = bookNaturalRepository.findById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }

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
