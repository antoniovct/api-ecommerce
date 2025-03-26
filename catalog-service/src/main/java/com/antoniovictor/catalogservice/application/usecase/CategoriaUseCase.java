package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageResponse;
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


    public PageResponse<Categoria> listarCategorias(PageRequestDto pageRequest) {
        return categoriaGateway.listarTodos(pageRequest);
    }

    public CategoriaResponseDto buscarCategoriaPorId(Long id) {
        return CategoriaMapper.categoriaToCategoriaResponseDto(categoriaGateway.buscarPorId(id));
    }

    public CategoriaResponseDto atualizarCategoria(Long id, CategoriaRequestDto categoriaRequestDto) {
        var categoria = categoriaGateway.buscarPorId(id);
        if (categoriaRequestDto.nome() != null) {
            categoria.setNome(categoriaRequestDto.nome());
        }
        return CategoriaMapper.categoriaToCategoriaResponseDto(categoriaGateway.salvar(categoria));
    }

    public void removerCategoriaPorId(Long id){
        categoriaGateway.remover(id);
    }
}
