package com.antoniovictor.catalogservice.domain.entities.categoria;

import com.antoniovictor.catalogservice.domain.entities.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nome;
    private List<Produto> produtos = new ArrayList<>();


    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", produtos=" + produtos +
                '}';
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
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
        this.nome = builder.nome;
    }

    public static class CategoriaBuilder {
        private String nome;
        private List<Produto> produtos;

        public CategoriaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Categoria build() {
            return new Categoria(this);
        }
    }
}
