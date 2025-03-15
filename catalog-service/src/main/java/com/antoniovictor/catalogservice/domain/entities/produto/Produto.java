package com.antoniovictor.catalogservice.domain.entities.produto;

import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;

import java.util.HashMap;
import java.util.Map;

public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Categoria categoria;
    private String marca;
    private Map<String, String> informacoes = new HashMap<>();


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", marca='" + marca + '\'' +
                ", informacoes=" + informacoes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Map<String, String> getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(Map<String, String> informacoes) {
        this.informacoes = informacoes;
    }


    public Produto() {}
    public Produto(ProdutoBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
        this.categoria = builder.categoria;
        this.marca = builder.marca;
        this.informacoes = builder.informacoes;
    }

    public static class ProdutoBuilder {
        private Long id;
        private String nome;
        private String descricao;
        private Double preco;
        private Categoria categoria;
        private String marca;
        private Map<String, String> informacoes;

        public ProdutoBuilder() {}

        public ProdutoBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public ProdutoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public ProdutoBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }
        public ProdutoBuilder preco(double preco) {
            this.preco = preco;
            return this;
        }
        public ProdutoBuilder categoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }
        public ProdutoBuilder marca(String marca) {
            this.marca = marca;
            return this;
        }
        public ProdutoBuilder informacoes(Map<String, String> informacoes) {
            this.informacoes = informacoes;
            return this;
        }
        public Produto build() {
            return new Produto(this);
        }

    }
}
