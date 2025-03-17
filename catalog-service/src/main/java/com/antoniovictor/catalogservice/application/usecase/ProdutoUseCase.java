package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
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
        var produto = ProdutoMapper.produtoRequestDtoToProduto(produtoRequestDto,categoria);
        return ProdutoMapper.produtoToProdutoResponseDto(produtoGateway.salvar(produto));

    }

    public List<ProdutoResponseDto> listarProdutos(){
        return produtoGateway.listarTodos().stream().map(ProdutoMapper::produtoToProdutoResponseDto).toList();
    }

    public List<ProdutoResponseDto> listarProdutosPorCategoria(Long categoriaId){
        return produtoGateway.listarPorCategoria(categoriaId).stream().map(ProdutoMapper::produtoToProdutoResponseDto).toList();
    }

    public ProdutoResponseDto buscarProdutoPorId(Long id){
        return ProdutoMapper.produtoToProdutoResponseDto(produtoGateway.buscarPorId(id));
    }

    public ProdutoResponseDto atualizarProduto(Long id, ProdutoRequestDto produtoRequestDto){
        var produto = produtoGateway.buscarPorId(id);
        if (produtoRequestDto.nome() != null) {
            produto.setNome(produtoRequestDto.nome());
        } else if (produtoRequestDto.descricao() != null) {
            produto.setDescricao(produtoRequestDto.descricao());
        } else if (produtoRequestDto.preco() != null) {
            produto.setPreco(produtoRequestDto.preco());
        } else if (produtoRequestDto.categoriaId() != null) {
            var categoria = categoriaGateway.buscarPorId(produtoRequestDto.categoriaId());
            produto.setCategoria(categoria);
        } else if (produtoRequestDto.quantidade() != null) {
            produto.setQuantidadeEstoque(produtoRequestDto.quantidade());
        } else if (produtoRequestDto.marca() != null) {
            produto.setMarca(produtoRequestDto.marca());
        } else if (produtoRequestDto.informacoes() != null) {
            produto.setInformacoes(produtoRequestDto.informacoes());
        }
        return ProdutoMapper.produtoToProdutoResponseDto(produtoGateway.salvar(produto));
    }

    public void removerProdutoPorId(Long id){
        produtoGateway.remover(id);
    }
}
