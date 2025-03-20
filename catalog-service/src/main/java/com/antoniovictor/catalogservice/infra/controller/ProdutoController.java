package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageRequestFilters;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.produto.Produto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto produtoRequestDto, UriComponentsBuilder uriBuilder) {
        var produto = produtoUseCase.cadastrarProduto(produtoRequestDto);
        var uri = uriBuilder.path("produto/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<PageResponse<Produto>> listarProdutos(Pageable pageable, @ModelAttribute PageRequestFilters filters) {
        return ResponseEntity.ok(produtoUseCase.listarProdutos(new PageRequestDto(pageable.getPageNumber(), pageable.getPageSize()), filters));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(produtoUseCase.listarProdutosPorCategoria(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoUseCase.buscarProdutoPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDto produtoRequestDto) {
        return ResponseEntity.ok(produtoUseCase.atualizarProduto(id, produtoRequestDto));
    }

    @DeleteMapping("/{id}")
    public void removerProdutoPorId(@PathVariable Long id){
        produtoUseCase.removerProdutoPorId(id);
    }
}
