package com.antoniovictor.catalogservice.domain.entities.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Map;

public record ProdutoRequestDto(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        Double preco,
        @NotNull @Positive
        Integer quantidade,
        @NotNull @Positive
        Long categoriaId,
        @NotBlank
        String marca,
        @NotBlank
        Map<String,String> informacoes
) {
}
