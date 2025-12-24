package com.vilelo.sdjpa_intro.repositories;

import com.vilelo.sdjpa_intro.domain.composite.AuthorComposite;
import com.vilelo.sdjpa_intro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
