package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.CategoriaUseCase;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    public final CategoriaUseCase categoriaUseCase;

    public CategoriaController(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaRequestDto, UriComponentsBuilder uriBuilder) {
        var categoria = categoriaUseCase.cadastrarCategoria(categoriaRequestDto);
        var uri = uriBuilder.path("categoria/{id}").buildAndExpand(categoria.id()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping
    public ResponseEntity<PageResponse<Categoria>> listarCategorias(Pageable pageable) {
        return ResponseEntity.ok(categoriaUseCase.listarCategorias(new PageRequestDto(pageable.getPageNumber(), pageable.getPageSize())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@PathVariable Long id) throws Exception {
            return ResponseEntity.ok(categoriaUseCase.buscarCategoriaPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDto categoriaRequestDto) throws Exception {
            return ResponseEntity.ok(categoriaUseCase.atualizarCategoria(id, categoriaRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCategoriaPorId(@PathVariable Long id){
        categoriaUseCase.removerCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
