package com.vilelo.sdjpa_intro.bootstrap;

import com.vilelo.sdjpa_intro.domain.Book;
import com.vilelo.sdjpa_intro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Book bookDDD = new Book("Domain Driven Design", "48394812", "RandomHouse");

        System.out.printf("Id: " + bookDDD.getId()); //at this point is null

        Book savedDDD = bookRepository.save(bookDDD);

        System.out.println("Saved DDD: " + savedDDD.getId());

        Book bookSIA = new Book("Spring in Action", "657565121", "RandomHouse");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });


    }
}
