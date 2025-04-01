package com.antoniovictor.orderservice.application.gateway;

import com.antoniovictor.orderservice.domain.entities.Pedido.Pedido;

import java.util.List;

public interface PedidoGateway {
    Pedido salvar(Pedido pedido);
    List<Pedido> listarTodos();
    Pedido buscarPorId(Long id);
    void remover(Long id);
}
