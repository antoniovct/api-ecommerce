package com.antoniovictor.catalogservice.infra.amqp;

import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.domain.ItemPedidoDto;
import com.antoniovictor.catalogservice.domain.PedidoDto;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import com.antoniovictor.catalogservice.domain.exception.SaidaProdutoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AmqpListener {
    private final RabbitTemplate rabbitTemplate;
    private final ProdutoGateway produtoGateway;

    public AmqpListener(RabbitTemplate rabbitTemplate, ProdutoGateway produtoGateway) {
        this.rabbitTemplate = rabbitTemplate;
        this.produtoGateway = produtoGateway;
    }

    @RabbitListener(queues = "pedido.criado")
    public void filaPedidos(@Payload PedidoDto pedidoDto) {
        List<ItemPedidoDto> produtosSemEstoque = new ArrayList<>();
        List<ItemPedidoDto> produtosComEstoque = new ArrayList<>();
        for (ItemPedidoDto item : pedidoDto.itens()) {
            try {
                Produto produto = produtoGateway.buscarPorId(item.produtoId());
                produto.saida(item.quantidade());
            } catch (EntityNotFoundException | SaidaProdutoException e) {
                produtosSemEstoque.add(item);
            }
        }
        if (produtosSemEstoque.isEmpty()) {
            rabbitTemplate.convertAndSend("catalog.ex", "estoque.reservado", new PedidoDto(pedidoDto.id(), produtosComEstoque));
        } else {
            rabbitTemplate.convertAndSend("catalog.ex", "estoque.insuficiente", new PedidoDto(pedidoDto.id(), produtosSemEstoque));
        }

    }
}
