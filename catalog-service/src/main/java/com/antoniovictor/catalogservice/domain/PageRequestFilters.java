package com.antoniovictor.catalogservice.domain;

public record PageRequestFilters(
        String nome,
        String marca,
        String categoria,
        String precoMin,
        String precoMax
) {
}
