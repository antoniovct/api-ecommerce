package com.antoniovictor.catalogservice.infra.gateway;

import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.domain.PageFilters;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageRequestFilters;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.exception.ProdutoNaoEncontradoException;
import com.antoniovictor.catalogservice.infra.mapper.ProdutoMapper;
import com.antoniovictor.catalogservice.infra.persistence.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        var retorno = produtoRepository.save(produtoEntity);
        return ProdutoMapper.produtoEntityToProduto(retorno);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll().stream().map(ProdutoMapper::produtoEntityToProduto).toList();
    }

    @Override
    public PageResponse<Produto> listarTodos(PageRequestDto pageRequest, PageRequestFilters filters) {
        Pageable pageable = PageRequest.of(pageRequest.pageNumber(), pageRequest.pageSize());
        PageFilters pageFilters = new PageFilters(filters);
        var produtos = produtoRepository.findAll(pageable).map(ProdutoMapper::produtoEntityToProduto);
        var produtosFiltrados = pageFilters.aplicarFiltros(produtos.getContent());
        return new PageResponse<Produto>(produtos.getNumber(),produtos.getSize(),produtos.getTotalPages(),
                produtos.getTotalElements(),produtos.hasNext(),produtos.hasPrevious(),produtosFiltrados);

    }

    @Override
    public Produto buscarPorId(Long id) throws ProdutoNaoEncontradoException {
        var produtoEntity = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));
        return ProdutoMapper.produtoEntityToProduto(produtoEntity);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}
