package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import com.antoniovictor.catalogservice.infra.mapper.ProdutoMapper;

import java.util.List;

public class ProdutoUseCase {
    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    public ProdutoUseCase(ProdutoGateway produtoGateway, CategoriaGateway categoriaGateway) {
        this.produtoGateway = produtoGateway;
        this.categoriaGateway = categoriaGateway;
    }

    public ProdutoResponseDto cadastrarProduto(ProdutoRequestDto produtoRequestDto) {
        var categoria = categoriaGateway.buscarPorId(produtoRequestDto.categoriaId());
        var produto = ProdutoMapper.produtoRequestDtoToProduto(produtoRequestDto, categoria);
        return ProdutoMapper.produtoToProdutoResponseDto(produtoGateway.salvar(produto));
    };

    public List<ProdutoResponseDto> listarProdutos(){
        return produtoGateway.listarTodos().stream().map(ProdutoMapper::produtoToProdutoResponseDto).toList();
    };

    public ProdutoResponseDto buscarProdutoPorId(Long id){
        return ProdutoMapper.produtoToProdutoResponseDto(produtoGateway.buscarPorId(id));
    };

    public void removerProdutoPorId(Long id){

    };
}
