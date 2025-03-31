package com.antoniovictor.orderservice.domain;

import java.util.Map;

public record ProdutoDto(
        String nome,
        String descricao,
        Double preco,
        String marca,
        Map<String,String> informacoes,
        Integer quantidade
) {
}
