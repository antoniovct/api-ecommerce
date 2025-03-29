package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.application.gateway.ProdutoGateway;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageRequestFilters;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import com.antoniovictor.catalogservice.domain.exception.ProdutoNaoEncontradoException;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;
import com.antoniovictor.catalogservice.infra.mapper.ProdutoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoUseCaseTest {

    @Mock
    private ProdutoGateway produtoGateway;
    @Mock
    private CategoriaGateway categoriaGateway;
    @InjectMocks
    private ProdutoUseCase produtoUseCase;
    private ProdutoRequestDto produtoRequestDto;
    @BeforeEach
    void setUp() {
        this.produtoRequestDto = new ProdutoRequestDto("Caixa de Som", "Caixa de Som Portátil",
                1000.00, 10, 1L, "JBL", Map.of("Proteção", "A prova d'água"));
    }
    @Test
    @DisplayName("Verifica se a resposta está correta e se não é nula.")
    void cadastrarProduto() throws Exception {
        var categoriaSalva = new Categoria.CategoriaBuilder().id(1L).nome("Áudio").build();
        var produtoSalvo = new Produto.ProdutoBuilder().id(1L).categoria(categoriaSalva)
                .descricao("Caixa de Som Portátil")
                .preco(1000.00)
                .quantidadeEstoque(10)
                .categoria(categoriaSalva)
                .marca("JBL")
                .informacoes(Map.of("Proteção", "A prova d'água"))
                .build();
        var produtoResponse = ProdutoMapper.produtoToProdutoResponseDto(produtoSalvo);
        when(categoriaGateway.buscarPorId(1L)).thenReturn(categoriaSalva);
        when(produtoGateway.salvar(any(Produto.class))).thenReturn(produtoSalvo);

        var response = produtoUseCase.cadastrarProduto(produtoRequestDto);

        assertNotNull(response);
        assertEquals(produtoResponse, response);
    }

    @Test
    @DisplayName("Verifica se a resposta do método não é nula, se a resposta é uma PageResponse e se o método listarTodos foi chamado.")
    void listarProdutos() {
        var pageRequest = new PageRequestDto(1, 10);
        var filters = new PageRequestFilters(null,null,null,null,null);
        var pageResponse = new PageResponse<Produto>(1, 10, 1, 3L, false, false,
                List.of(new Produto.ProdutoBuilder().id(1L).nome("Caixa de Som").build(),
                        new Produto.ProdutoBuilder().id(2L).nome("Fone de Ouvido").build(),
                        new Produto.ProdutoBuilder().id(3L).nome("Teclado").build()
                ));
        when(produtoGateway.listarTodos(pageRequest, filters)).thenReturn(pageResponse);

        var response = produtoUseCase.listarProdutos(pageRequest, filters);

        assertNotNull(response);
        assertEquals(pageResponse, response);
        verify(produtoGateway).listarTodos(pageRequest, filters);
    }

    @Test
    @DisplayName("Verifica se a resposta do método não é nula, se é um objeto ProdutoResponseDto e se o método buscarPorId está sendo chamado.")
    void buscarProdutoPorId() throws ProdutoNaoEncontradoException {
        var categoria = new Categoria.CategoriaBuilder().id(1L).nome("Áudio").build();
        var produtoSalvo = ProdutoMapper.produtoRequestDtoToProduto(produtoRequestDto,categoria);
        produtoSalvo.setId(1L);
        var produtoResponse = ProdutoMapper.produtoToProdutoResponseDto(produtoSalvo);
        when(produtoGateway.buscarPorId(1L)).thenReturn(produtoSalvo);

        var response = produtoUseCase.buscarProdutoPorId(1L);

        assertNotNull(response);
        assertEquals(produtoResponse, response);
        verify(produtoGateway).buscarPorId(1L);
    }

    @Test
    @DisplayName("Verifica se o nome do produto está sendo atualizado.")
    void atualizarProduto() throws Exception {
        var produtoRequest = new ProdutoRequestDto("Caixa Bluetooth", null, null, null, null, null, null);
        var categoria = new Categoria.CategoriaBuilder().id(1L).nome("Áudio").build();
        var produto = ProdutoMapper.produtoRequestDtoToProduto(produtoRequestDto,categoria);
        produto.setId(1L);
        when(produtoGateway.buscarPorId(1L)).thenReturn(produto);
        when(produtoGateway.salvar(produto)).thenReturn(produto);

        var response = produtoUseCase.atualizarProduto(1L, produtoRequest);

        assertEquals(produtoRequest.nome(), response.nome());
    }

    @Test
    @DisplayName("Verifica se o produto está sendo removido.")
    void removerProdutoPorId() {
        produtoUseCase.removerProdutoPorId(1L);
        verify(produtoGateway).remover(1L);
    }

    @Test
    void saidaDeProduto() {
    }
}