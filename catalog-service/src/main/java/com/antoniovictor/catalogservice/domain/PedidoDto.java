package com.antoniovictor.catalogservice.domain;

import java.util.List;

public record PedidoDto (
        Long id,
        List<ItemPedidoDto> itens
) {

}



