package com.antoniovictor.catalogservice.domain.entities.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDto(
        @NotBlank
        String nome) {
}
