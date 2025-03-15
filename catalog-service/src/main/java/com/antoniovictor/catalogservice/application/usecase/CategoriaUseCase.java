package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;

import java.util.List;

public class CategoriaUseCase {
    public final CategoriaGateway categoriaGateway;

    public CategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public CategoriaResponseDto cadastrarCategoria(CategoriaRequestDto categoriaRequestDto) {
        var categoria = CategoriaMapper.categoriaRequestDtoToCategoria(categoriaRequestDto);
        return CategoriaMapper.categoriaToCategoriaResponseDto(categoriaGateway.salvar(categoria));
    }


    public List<CategoriaResponseDto> listarCategorias() {

        return categoriaGateway.listarTodos().stream().map(CategoriaMapper::categoriaToCategoriaResponseDto).toList();
    }

    public CategoriaResponseDto buscarCategoriaPorId(Long id) {

        return CategoriaMapper.categoriaToCategoriaResponseDto(categoriaGateway.buscarPorId(id));
    }
}
