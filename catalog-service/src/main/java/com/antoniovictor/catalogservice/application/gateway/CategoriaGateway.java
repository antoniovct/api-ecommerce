package com.antoniovictor.catalogservice.application.gateway;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;

import java.util.List;

public interface CategoriaGateway {

    Categoria salvar(Categoria categoria);
    List<Categoria> listarTodos();
    Categoria buscarPorId(Long id);
    void remover(Long id);
}
