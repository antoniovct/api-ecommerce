package com.antoniovictor.catalogservice.infra.gateway;

import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;
import com.antoniovictor.catalogservice.infra.mapper.ProdutoMapper;
import com.antoniovictor.catalogservice.infra.persistence.ProdutoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProdutoJpaRepositoryGateway implements ProdutoGateway {
    private final ProdutoRepository produtoRepository;

    public ProdutoJpaRepositoryGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public Produto salvar(Produto produto) {
        System.out.println(produto);
        System.out.println(CategoriaMapper.categoriaToCategoriaEntity(produto.getCategoria()));
        var produtoEntity = ProdutoMapper.produtoToProdutoEntity(produto);
        System.out.println(produtoEntity);
        var retorno = produtoRepository.save(produtoEntity);
        return ProdutoMapper.produtoEntityToProduto(retorno);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll().stream().map(ProdutoMapper::produtoEntityToProduto).toList();
    }

    @Override
    public List<Produto> listarPorCategoria(Long categoriaId) {
        return produtoRepository.findAll().stream().filter(produtoEntity -> produtoEntity.getCategoria().getId().equals(categoriaId)).map(ProdutoMapper::produtoEntityToProduto).toList();
    }

    @Override
    public Produto buscarPorId(Long id) {
        var produtoEntity = produtoRepository.getReferenceById(id);
        return ProdutoMapper.produtoEntityToProduto(produtoEntity);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
