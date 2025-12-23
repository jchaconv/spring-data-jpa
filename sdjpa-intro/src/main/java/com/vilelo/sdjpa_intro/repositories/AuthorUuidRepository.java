package com.vilelo.sdjpa_intro.repositories;

import com.vilelo.sdjpa_intro.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
