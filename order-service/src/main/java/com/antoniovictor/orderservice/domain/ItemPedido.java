package com.antoniovictor.orderservice.domain;

public record ItemPedido(
        Long id,
        String nome,
        Double preco,
        Integer quantidade
) {
}
