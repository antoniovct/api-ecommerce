package com.antoniovictor.catalogservice.infra.persistence;

import com.antoniovictor.catalogservice.infra.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
