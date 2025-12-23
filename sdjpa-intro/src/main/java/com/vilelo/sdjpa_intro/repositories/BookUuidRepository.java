package com.vilelo.sdjpa_intro.repositories;

import com.vilelo.sdjpa_intro.domain.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
