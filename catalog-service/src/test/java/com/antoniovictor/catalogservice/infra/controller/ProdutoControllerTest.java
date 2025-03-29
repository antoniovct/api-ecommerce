package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import com.antoniovictor.catalogservice.domain.exception.ProdutoNaoEncontradoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private ProdutoUseCase produtoUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Verifica se o status da requisição é 201 Created e o header Location existe")
    void cadastrarProdutoCenario1() throws Exception {
        var produtoResponse = new ProdutoResponseDto(1L, "Produto Teste", "Descricao Teste", 10.0, new Categoria.CategoriaBuilder().id(1L).nome("Categoria teste").build(), "Marca Teste", null, null);
        when(produtoUseCase.cadastrarProduto(any(ProdutoRequestDto.class))).thenReturn(produtoResponse);
        mvc.perform(post("/produto")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new ProdutoRequestDto("Produto Teste", "Descricao Teste", 10.0, 5, 1L, "Teste", new HashMap<>()))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 400 Bad Request")
    void cadastrarProdutoCenario2() throws Exception {
        mvc.perform(post("/produto")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new ProdutoRequestDto(null, "Descricao Teste", 10.0, 5, 1L, "Teste", new HashMap<>()))))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK")
    void listarProdutos() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var pageResponse = new PageResponse<>(0, 10, 1, 1L, false, false, List.of(new Produto.ProdutoBuilder().id(1L).nome("Produto Teste").descricao("Descricao Teste").preco(10.0).quantidadeEstoque(5).marca("Marca Teste").informacoes(null).build()));
        when(produtoUseCase.listarProdutos(any(), any())).thenReturn(pageResponse);
        mvc.perform(get("/produto"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pageResponse)));
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK")
    void buscarProdutoPorIdCenario1() throws Exception {
        var produtoResponse = new ProdutoResponseDto(1L, "Produto Teste", "Descricao Teste", 10.0, new Categoria.CategoriaBuilder().id(1L).nome("Categoria teste").build(), "Marca Teste", null, null);
        when(produtoUseCase.buscarProdutoPorId(1L)).thenReturn(produtoResponse);
        mvc.perform(get("/produto/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 404 Not Found")
    void buscarProdutoPorIdCenario2() throws Exception {
        when(produtoUseCase.buscarProdutoPorId(1L)).thenThrow(new ProdutoNaoEncontradoException("Produto não encontrado"));
        mvc.perform(get("/produto/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK")
    void atualizarProdutoCenario1() throws Exception {
        var produtoRequest = new ProdutoRequestDto("Produto Atualizado", "Descricao Atualizada", 15.0, 10, 1L, null, new HashMap<>());
        var produtoResponse = new ProdutoResponseDto(1L, "Produto Atualizado", "Descricao Atualizada", 15.0, new Categoria.CategoriaBuilder().id(1L).nome("Categoria teste").build(), "Marca Atualizada", null, null);
        when(produtoUseCase.atualizarProduto(1L, produtoRequest)).thenReturn(produtoResponse);
        mvc.perform(patch("/produto/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(produtoRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 404 Not Found")
    void atualizarProdutoCenario2() throws Exception {
        when(produtoUseCase.atualizarProduto(anyLong(), any(ProdutoRequestDto.class))).thenThrow(new ProdutoNaoEncontradoException("Produto não encontrado"));
        mvc.perform(patch("/produto/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new ProdutoRequestDto("Produto Atualizado", "Descricao Atualizada", 15.0, 10, 1L, null, new HashMap<>()))))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 204 No Content")
    void removerProdutoPorId() throws Exception {
        mvc.perform(delete("/produto/1"))
                .andExpect(status().isNoContent());
    }
}