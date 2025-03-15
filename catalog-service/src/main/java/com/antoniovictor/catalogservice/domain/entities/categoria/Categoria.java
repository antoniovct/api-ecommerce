package com.antoniovictor.catalogservice.domain.entities.categoria;

import com.antoniovictor.catalogservice.domain.entities.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private Long id;
    private String nome;
    private List<Produto> produtos = new ArrayList<>();


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", produtos=" + produtos +
                '}';
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    public Categoria() {}
    public Categoria(CategoriaBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.produtos = builder.produtos;
    }

    public static class CategoriaBuilder {
        private Long id;
        private String nome;
        private List<Produto> produtos;

        public CategoriaBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public CategoriaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public CategoriaBuilder produtos(List<Produto> produtos) {
            this.produtos = produtos;
            return this;
        }

        public Categoria build() {
            return new Categoria(this);
        }
    }
}
