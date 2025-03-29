package com.antoniovictor.catalogservice.application.gateway;

import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;

import java.util.List;

public interface CategoriaGateway {

    Categoria salvar(Categoria categoria);
    List<Categoria> listarTodos();
    PageResponse<Categoria> listarTodos(PageRequestDto pageRequest);
    Categoria buscarPorId(Long id) throws Exception;
    void remover(Long id);
}
