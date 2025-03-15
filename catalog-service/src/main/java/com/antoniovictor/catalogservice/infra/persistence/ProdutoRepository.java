package com.antoniovictor.catalogservice.infra.persistence;

import com.antoniovictor.catalogservice.infra.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
