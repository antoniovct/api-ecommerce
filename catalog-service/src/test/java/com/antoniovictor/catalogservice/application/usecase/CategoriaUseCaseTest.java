package com.antoniovictor.catalogservice.application.usecase;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCaseTest {

    @InjectMocks
    private CategoriaUseCase categoriaUseCase;
    @Mock
    private CategoriaGateway categoriaGateway;
    private CategoriaRequestDto categoriaRequestDto;

    @BeforeEach
    void setUp() {
        categoriaRequestDto = new CategoriaRequestDto("SmartPhones");
    }
    @Test
    @DisplayName("Verifica se a resposta do método não é nula, se é um objeto CategoriaResponseDto e se o método salvar está sendo chamado.")
    void cadastrarCategoriaCenario1() {
        var categoriaSalva = new Categoria.CategoriaBuilder().id(1L).nome("SmartPhones").build();
        var categoriaResponse = CategoriaMapper.categoriaToCategoriaResponseDto(categoriaSalva);
        when(categoriaGateway.salvar(any(Categoria.class))).thenReturn(categoriaSalva);

        var response = categoriaUseCase.cadastrarCategoria(categoriaRequestDto);

        assertNotNull(response);
        assertEquals(response, categoriaResponse);
        verify(categoriaGateway).salvar(any(Categoria.class));
    }

    @Test
    @DisplayName("Verifica se a resposta do método não é nula, se a resposta é uma PageResponse e se o método listarTodos foi chamado.")
    void listarCategorias() {
        var pageRequest = new PageRequestDto(1, 10);
        var categorias = List.of(new Categoria.CategoriaBuilder().id(1L).nome("categoria 1").build(),
                new Categoria.CategoriaBuilder().id(2L).nome("categoria 2").build(),
                new Categoria.CategoriaBuilder().id(3L).nome("categoria 3").build()
        );
        var pageResponse = new PageResponse<Categoria>(1, 10, 1, 3L, false, false, categorias);
        when(categoriaGateway.listarTodos(any(PageRequestDto.class))).thenReturn(pageResponse);

        var response = categoriaUseCase.listarCategorias(pageRequest);

        assertNotNull(response);
        assertEquals(response, pageResponse);
        verify(categoriaGateway).listarTodos(any(PageRequestDto.class));
    }

    @Test
    @DisplayName("Verifica se a resposta do método não é nula, se é um objeto CategoriaResponseDto e se o método buscarPorId está sendo chamado.")
    void buscarCategoriaPorId() {
        var categoriaSalva = new Categoria.CategoriaBuilder().id(1L).nome("SmartPhones").build();
        var categoriaResponse = CategoriaMapper.categoriaToCategoriaResponseDto(categoriaSalva);
        when(categoriaGateway.buscarPorId(any(Long.class))).thenReturn(categoriaSalva);

        var response = categoriaUseCase.buscarCategoriaPorId(1L);

        assertNotNull(response);
        assertEquals(response, categoriaResponse);
        verify(categoriaGateway).buscarPorId(any(Long.class));
    }

    @Test
    @DisplayName("Verifica se a categoria está sendo atualizada.")
    void atualizarCategoria() {
        var categoriaRequest = new CategoriaRequestDto("Áudio");
        var categoriaSalva = new Categoria.CategoriaBuilder().id(1L).nome("SmartPhones").build();
        categoriaSalva.setNome("Áudio");
        var categoriaResponse = CategoriaMapper.categoriaToCategoriaResponseDto(categoriaSalva);
        when(categoriaGateway.buscarPorId(any(Long.class))).thenReturn(categoriaSalva);
        when(categoriaGateway.salvar(any(Categoria.class))).thenReturn(categoriaSalva);

        var response = categoriaUseCase.atualizarCategoria(1L, categoriaRequest);

        assertNotNull(response);
        assertEquals(response, categoriaResponse);
        verify(categoriaGateway).buscarPorId(any(Long.class));
        verify(categoriaGateway).salvar(any(Categoria.class));
    }

    @Test
    @DisplayName("Verifica se a categoria está sendo removida.")
    void removerCategoriaPorId() {
        categoriaUseCase.removerCategoriaPorId(1L);

        verify(categoriaGateway).remover(any(Long.class));
    }
}