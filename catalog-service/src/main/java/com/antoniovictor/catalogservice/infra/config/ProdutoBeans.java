package com.antoniovictor.catalogservice.infra.config;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.infra.gateway.ProdutoJpaRepositoryGateway;
import com.antoniovictor.catalogservice.infra.persistence.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeans {

    @Bean
    public ProdutoUseCase produtoUseCase(ProdutoGateway produtoGateway, CategoriaGateway categoriaGateway) {
        return new ProdutoUseCase(produtoGateway, categoriaGateway);
    }

    @Bean
    public ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoJpaRepositoryGateway(produtoRepository);
    }
}
