package com.antoniovictor.catalogservice.infra.mapper;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
import com.antoniovictor.catalogservice.infra.entities.CategoriaEntity;

public class CategoriaMapper {

    public static Categoria categoriaRequestDtoToCategoria(CategoriaRequestDto categoriaRequestDto) {
        return new Categoria.CategoriaBuilder().nome(categoriaRequestDto.nome()).build();
    }

    public static CategoriaEntity categoriaToCategoriaEntity(Categoria categoria) {
        return new CategoriaEntity.CategoriaEntityBuilder()
                .id(categoria.getId() == null ? null : categoria.getId())
                .nome(categoria.getNome())
                .build();
    }


    public static Categoria categoriaEntityToCategoria(CategoriaEntity categoriaEntity) {
        return new Categoria.CategoriaBuilder()
                .id(categoriaEntity.getId())
                .nome(categoriaEntity.getNome())
                .build();
    }


    public static CategoriaResponseDto categoriaToCategoriaResponseDto(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getId(), categoria.getNome());
    }

}
