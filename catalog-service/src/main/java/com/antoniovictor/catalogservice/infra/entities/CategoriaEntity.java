package com.antoniovictor.catalogservice.infra.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutoEntity> produtos = new ArrayList<>();

    public CategoriaEntity() {
    }

    public CategoriaEntity(CategoriaEntityBuilder builder) {
        this.nome = builder.nome;
        this.produtos = builder.produtos;
    }

    public static class CategoriaEntityBuilder {
        private String nome;
        private List<ProdutoEntity> produtos;

        public CategoriaEntityBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public CategoriaEntityBuilder produtos(List<ProdutoEntity> produtos) {
            this.produtos = produtos;
            return this;
        }

        public CategoriaEntity build() {
            return new CategoriaEntity(this);
        }
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void addProduto(ProdutoEntity produto) {
        this.produtos.add(produto);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoriaEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoriaEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
