package com.antoniovictor.catalogservice.domain.entities.produto;

import java.util.Map;

public record ProdutoRequestDto(
        String nome,
        String descricao,
        Double preco,
        Long categoriaId,
        String marca,
        Map<String,String> informacoes
) {
}
