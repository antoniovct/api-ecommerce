package com.antoniovictor.catalogservice.infra.mapper;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.infra.entities.CategoriaEntity;

public class CategoriaMapper {

    public static Categoria categoriaRequestDtoToCategoria(CategoriaRequestDto categoriaRequestDto) {
        return new Categoria.CategoriaBuilder().nome(categoriaRequestDto.nome()).build();
    }

    public static CategoriaEntity categoriaToCategoriaEntity(Categoria categoria) {
        return new CategoriaEntity.CategoriaEntityBuilder().nome(categoria.getNome()).build();
    }

    public static Categoria categoriaEntityToCategoria(CategoriaEntity categoriaEntity) {
        return new Categoria.CategoriaBuilder()
                .id(categoriaEntity.getId())
                .nome(categoriaEntity.getNome())
                .produtos(categoriaEntity.getProdutos().stream().map(ProdutoMapper::produtoEntityToProduto).toList())
                .build();
    }

    public static CategoriaResponseDto categoriaToCategoriaResponseDto(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getId(), categoria.getNome(), categoria.getProdutos().stream().map(Produto::getNome).toList());
    }

}
