package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.CategoriaUseCase;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
import com.antoniovictor.catalogservice.domain.exception.CategoriaNaoEncontradaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private CategoriaUseCase categoriaUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Verifica se o status da requisição é 201 Created e o header Location existe")
    void cadastrarCategoriaCenario1() throws Exception {
        var categoriaResponse = new CategoriaResponseDto(1L, "Categoria Teste");
        when(categoriaUseCase.cadastrarCategoria(any(CategoriaRequestDto.class))).thenReturn(categoriaResponse);
        mvc.perform(post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CategoriaRequestDto("Categoria Teste"))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 400 Bad Request")
    void cadastrarCategoriaCenario2() throws Exception {
        mvc.perform(post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CategoriaRequestDto(null))))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK e o conteúdo é o esperado")
    void listarCategorias() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var pageResponse = new PageResponse<>(0, 10, 1, 1L, false, false, List.of(new Categoria.CategoriaBuilder().id(1L).nome("Categoria Teste").build()));
        when(categoriaUseCase.listarCategorias(any())).thenReturn(pageResponse);
        mvc.perform(get("/categoria"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pageResponse)));
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK)")
    void buscarCategoriaPorIdCenario1() throws Exception {
        var categoriaResponse = new CategoriaResponseDto(1L, "Categoria Teste");
        when(categoriaUseCase.buscarCategoriaPorId(1L)).thenReturn(categoriaResponse);
        mvc.perform(get("/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 404 Not Found")
    void buscarCategoriaPorIdCenario2() throws Exception {
        when(categoriaUseCase.buscarCategoriaPorId(1L)).thenThrow(new CategoriaNaoEncontradaException("Categoria não encontrada"));
        mvc.perform(get("/categoria/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 200 OK")
    void atualizarCategoriaCenario1() throws Exception {
        var categoriaResponse = new CategoriaResponseDto(1L, "Categoria Atualizada");
        when(categoriaUseCase.atualizarCategoria(1L, new CategoriaRequestDto("Categoria Atualizada"))).thenReturn(categoriaResponse);
        mvc.perform(patch("/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CategoriaRequestDto("Categoria Atualizada"))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 404 Not Found")
    void atualizarCategoriaCenario2() throws Exception {
        when(categoriaUseCase.atualizarCategoria(1L, new CategoriaRequestDto("Categoria Atualizada"))).thenThrow(new CategoriaNaoEncontradaException("Categoria não encontrada"));
        mvc.perform(patch("/categoria/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CategoriaRequestDto("Categoria Atualizada"))))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Verifica se o status da requisição é 204 No Content")
    void removerCategoriaPorId() throws Exception {
        mvc.perform(delete("/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}