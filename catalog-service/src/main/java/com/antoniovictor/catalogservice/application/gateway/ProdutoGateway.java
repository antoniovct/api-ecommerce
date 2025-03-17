package com.antoniovictor.catalogservice.application.gateway;

import com.antoniovictor.catalogservice.domain.entities.produto.Produto;

import java.util.List;

public interface ProdutoGateway {
    Produto salvar(Produto produto);
    List<Produto> listarTodos();
    List<Produto> listarPorCategoria(Long categoriaId);
    Produto buscarPorId(Long id);
    void remover(Long id);
}
