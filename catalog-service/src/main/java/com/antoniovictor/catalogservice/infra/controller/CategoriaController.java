package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.CategoriaUseCase;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaRequestDto;
import com.antoniovictor.catalogservice.domain.entities.categoria.CategoriaResponseDto;
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
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@RequestBody CategoriaRequestDto categoriaRequestDto, UriComponentsBuilder uriBuilder) {
        var categoria = categoriaUseCase.cadastrarCategoria(categoriaRequestDto);
        var uri = uriBuilder.path("categoria/{id}").buildAndExpand(categoria.id()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias() {
        return ResponseEntity.ok(categoriaUseCase.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaUseCase.buscarCategoriaPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDto categoriaRequestDto) {
        return ResponseEntity.ok(categoriaUseCase.atualizarCategoria(id, categoriaRequestDto));
    }

    @DeleteMapping("/{id}")
    public void removerCategoriaPorId(@PathVariable Long id){
        categoriaUseCase.removerCategoriaPorId(id);
    }
}
