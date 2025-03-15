package com.antoniovictor.catalogservice.domain.entities.categoria;

import java.util.List;

public record CategoriaResponseDto(Long id,String nome, List<String> produtos) {
}
