package com.antoniovictor.catalogservice.domain.entities.produto;

import java.util.Map;

public record ProdutoResponseDto (
        Long id,
        String nome,
        String descricao,
        Double preco,
        String categoria,
        String marca,
        Map<String,String> informacoes
) {
}
