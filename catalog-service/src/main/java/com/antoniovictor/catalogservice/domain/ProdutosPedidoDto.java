package com.antoniovictor.catalogservice.domain;

import java.util.Map;

public record ProdutosPedidoDto(
        Map<Long,Integer> produtos
) {
}
