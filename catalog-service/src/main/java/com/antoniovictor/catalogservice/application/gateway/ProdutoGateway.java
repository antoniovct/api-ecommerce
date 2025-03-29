package com.antoniovictor.catalogservice.application.gateway;

import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageRequestFilters;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.exception.ProdutoNaoEncontradoException;

import java.util.List;

public interface ProdutoGateway {
    Produto salvar(Produto produto);
    List<Produto> listarTodos();
    PageResponse<Produto> listarTodos(PageRequestDto pageRequest, PageRequestFilters filters);
    Produto buscarPorId(Long id) throws ProdutoNaoEncontradoException;
    void remover(Long id);
}
