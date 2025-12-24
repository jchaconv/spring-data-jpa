package com.vilelo.sdjpa_intro.repositories;

import com.vilelo.sdjpa_intro.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
