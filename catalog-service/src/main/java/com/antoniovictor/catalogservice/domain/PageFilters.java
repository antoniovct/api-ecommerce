package com.antoniovictor.catalogservice.domain;

import com.antoniovictor.catalogservice.domain.entities.produto.Produto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PageFilters {
    private String nome;
    private String marca;
    private String categoria;
    private Double precoMin;
    private Double precoMax;

    public PageFilters() {
    }

    public PageFilters(PageRequestFilters filters) {
        this.nome = filters.nome();
        this.marca = filters.marca();
        this.categoria = filters.categoria();
        this.precoMin = filters.precoMin() != null ? Double.valueOf(filters.precoMin()) : null;
        this.precoMax = filters.precoMax() != null ? Double.valueOf(filters.precoMax()) : null;
    }

    public List<Produto> aplicarFiltros(List<Produto> lista) {
        List<Produto> listaFiltrada = lista;
        if (this.nome != null) {
            listaFiltrada = lista.stream().filter(p -> p.getNome().contains(this.nome)).collect(Collectors.toList());
        } else if (this.marca != null) {
            listaFiltrada = lista.stream().filter(p -> p.getMarca().contains(this.marca)).collect(Collectors.toList());
        } else if (this.categoria != null) {
            listaFiltrada = lista.stream().filter(p -> p.getCategoria().getNome().contains(this.categoria)).collect(Collectors.toList());
        } else if (this.precoMin != null) {
            listaFiltrada = lista.stream().filter(p -> p.getPreco() >= this.precoMin).collect(Collectors.toList());
        } else if (this.precoMax != null) {
            listaFiltrada = lista.stream().filter(p -> p.getPreco() <= this.precoMax).collect(Collectors.toList());
        }
        return listaFiltrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(Double precoMin) {
        this.precoMin = precoMin;
    }

    public Double getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(Double precoMax) {
        this.precoMax = precoMax;
    }
}
