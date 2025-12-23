package com.vilelo.sdjpa_intro.bootstrap;

import com.vilelo.sdjpa_intro.domain.AuthorUuid;
import com.vilelo.sdjpa_intro.domain.Book;
import com.vilelo.sdjpa_intro.domain.BookUuid;
import com.vilelo.sdjpa_intro.repositories.AuthorUuidRepository;
import com.vilelo.sdjpa_intro.repositories.BookRepository;
import com.vilelo.sdjpa_intro.repositories.BookUuidRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();
        authorUuidRepository.deleteAll();

        Book bookDDD = new Book("Domain Driven Design", "48394812", "RandomHouse", null);

        System.out.printf("Id: " + bookDDD.getId()); //at this point is null

        Book savedDDD = bookRepository.save(bookDDD);

        System.out.println("Saved DDD: " + savedDDD.getId());

        Book bookSIA = new Book("Spring in Action", "657565121", "RandomHouse", null);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Julio");
        authorUuid.setLastName("Chacon");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthor.getId());

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All about UUIDs");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId());


    }
}
