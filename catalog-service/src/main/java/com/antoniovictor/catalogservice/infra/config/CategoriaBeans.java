package com.antoniovictor.catalogservice.infra.config;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.application.usecase.CategoriaUseCase;
import com.antoniovictor.catalogservice.infra.gateway.CategoriaJpaRepositoryGateway;
import com.antoniovictor.catalogservice.infra.persistence.CategoriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaBeans {

    @Bean
    public CategoriaUseCase categoriaUseCase(CategoriaGateway categoriaGateway) {
        return new CategoriaUseCase(categoriaGateway);
    }

    @Bean
    public CategoriaGateway categoriaGateway(CategoriaRepository categoriaRepository) {
        return new CategoriaJpaRepositoryGateway(categoriaRepository);
    }
}
