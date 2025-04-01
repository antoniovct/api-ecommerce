package com.antoniovictor.orderservice.application.usecase;

import com.antoniovictor.orderservice.application.gateway.PedidoGateway;

public class PedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }


}
