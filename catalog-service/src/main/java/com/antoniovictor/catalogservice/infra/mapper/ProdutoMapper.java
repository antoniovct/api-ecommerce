package com.antoniovictor.catalogservice.infra.mapper;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import com.antoniovictor.catalogservice.infra.entities.ProdutoEntity;

public class ProdutoMapper {

    public static Produto produtoRequestDtoToProduto(ProdutoRequestDto produtoRequestDto, Categoria categoria) {
        return new Produto.ProdutoBuilder()
                .nome(produtoRequestDto.nome())
                .descricao(produtoRequestDto.descricao())
                .preco(produtoRequestDto.preco())
                .categoria(categoria)
                .marca(produtoRequestDto.marca())
                .informacoes(produtoRequestDto.informacoes())
                .build();
    }

    public static ProdutoEntity produtoToProdutoEntity(Produto produto) {
        return new ProdutoEntity.ProdutoEntityBuilder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categoria(new CategoriaMapper().categoriaToCategoriaEntity(produto.getCategoria()))
                .marca(produto.getMarca())
                .informacoes(produto.getInformacoes())
                .build();
    }

    public static Produto produtoEntityToProduto(ProdutoEntity produtoEntity){
        return new Produto.ProdutoBuilder()
                .nome(produtoEntity.getNome())
                .descricao(produtoEntity.getDescricao())
                .preco(produtoEntity.getPreco())
                .categoria(new CategoriaMapper().categoriaEntityToCategoria(produtoEntity.getCategoria()))
                .marca(produtoEntity.getMarca())
                .informacoes(produtoEntity.getInformacoes())
                .build();
    }

    public static ProdutoResponseDto produtoToProdutoResponseDto(Produto produto) {
        return new ProdutoResponseDto(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getCategoria().getNome(), produto.getMarca(), produto.getInformacoes());
    }
}
