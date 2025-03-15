package com.antoniovictor.catalogservice.infra.gateway;

import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
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
        var produtoEntity = ProdutoMapper.produtoToProdutoEntity(produto);
        return ProdutoMapper.produtoEntityToProduto(produtoRepository.save(produtoEntity));
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll().stream().map(ProdutoMapper::produtoEntityToProduto).toList();
    }

    @Override
    public Produto buscarPorId(Long id) {
        var produtoEntity = produtoRepository.getReferenceById(id);
        return ProdutoMapper.produtoEntityToProduto(produtoEntity);
    }

    @Override
    public void remover(Long id) {

    }
}
