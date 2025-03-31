package com.antoniovictor.orderservice.domain.entities.Pedido;

import com.antoniovictor.orderservice.domain.ProdutoDto;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDateTime data;
    private Status status;
    private String cliente;
    private List<ProdutoDto> itens;
    private Double valorTotal;

    public Pedido(PedidoBuilder builder) {
        this.id = builder.id;
        this.data = builder.data;
        this.status = builder.status;
        this.cliente = builder.cliente;
        this.itens = builder.itens;
        this.valorTotal = builder.valorTotal;
    }

    public static class PedidoBuilder {
        private Long id;
        private LocalDateTime data;
        private Status status;
        private String cliente;
        private List<ProdutoDto> itens;
        private Double valorTotal;

        public PedidoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PedidoBuilder data(LocalDateTime data) {
            this.data = data;
            return this;
        }

        public PedidoBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public PedidoBuilder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public PedidoBuilder itens(List<ProdutoDto> itens) {
            this.itens = itens;
            return this;
        }

        public PedidoBuilder valorTotal(Double valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}
