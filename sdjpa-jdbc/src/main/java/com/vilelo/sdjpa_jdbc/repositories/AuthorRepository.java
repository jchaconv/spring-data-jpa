package com.vilelo.sdjpa_jdbc.repositories;

import com.vilelo.sdjpa_jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
