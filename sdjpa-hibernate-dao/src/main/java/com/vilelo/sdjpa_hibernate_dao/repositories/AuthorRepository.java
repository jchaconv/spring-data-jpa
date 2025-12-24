package com.vilelo.sdjpa_hibernate_dao.repositories;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
