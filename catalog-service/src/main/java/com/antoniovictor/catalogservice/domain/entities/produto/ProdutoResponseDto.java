package com.antoniovictor.catalogservice.domain.entities.produto;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;

import java.util.Map;

public record ProdutoResponseDto (
        Long id,
        String nome,
        String descricao,
        Double preco,
        Categoria categoria,
        String marca,
        Map<String,String> informacoes,
        Integer quantidadeEstoque
) {
}
