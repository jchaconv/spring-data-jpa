package com.vilelo.sdjpa_hibernate_dao.repositories;

import com.vilelo.sdjpa_hibernate_dao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
