package com.antoniovictor.orderservice.infra.entities;

import com.antoniovictor.orderservice.domain.entities.Pedido.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String cliente;
    private List<ItemPedidoEntity> itens;
}
