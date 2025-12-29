package com.vilelo.sdjpa_inheritance.repositories;

import com.vilelo.sdjpa_inheritance.joined.ElectricGuitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}
