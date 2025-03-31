package com.antoniovictor.catalogservice.infra.amqp;

import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.domain.ProdutosPedidoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AmqpListener {
    private final RabbitTemplate rabbitTemplate;
    private final ProdutoUseCase produtoUseCase;

    public AmqpListener(RabbitTemplate rabbitTemplate, ProdutoUseCase produtoUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.produtoUseCase = produtoUseCase;
    }

    @RabbitListener(queues = "queue.pedidos")
    public void filaPedidos(@Payload ProdutosPedidoDto produtosPedido) {
        var produtos = produtoUseCase.saidaDeProduto(produtosPedido);
        rabbitTemplate.convertAndSend("catalog.ex", "estoque.reserva", produtos);
    }
}
