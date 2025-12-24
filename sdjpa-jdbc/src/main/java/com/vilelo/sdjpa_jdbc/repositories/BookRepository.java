package com.vilelo.sdjpa_jdbc.repositories;

import com.vilelo.sdjpa_jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
