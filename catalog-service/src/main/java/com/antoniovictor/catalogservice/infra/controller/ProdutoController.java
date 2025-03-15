package com.antoniovictor.catalogservice.infra.controller;

import com.antoniovictor.catalogservice.application.usecase.ProdutoUseCase;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoRequestDto;
import com.antoniovictor.catalogservice.domain.entities.produto.ProdutoResponseDto;
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
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody ProdutoRequestDto produtoRequestDto, UriComponentsBuilder uriBuilder) {
        var produto = produtoUseCase.cadastrarProduto(produtoRequestDto);
        var uri = uriBuilder.path("produto/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        return ResponseEntity.ok(produtoUseCase.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoUseCase.buscarProdutoPorId(id));
    }
}
