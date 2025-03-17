package com.antoniovictor.catalogservice.infra.entities;

import com.antoniovictor.catalogservice.infra.config.MapConvert;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
    private String marca;
    @Convert(converter = MapConvert.class)
    private Map<String, String> informacoes = new HashMap<>();
    private Integer quantidadeEstoque;

    public ProdutoEntity() {
    }
    public ProdutoEntity(ProdutoEntityBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
        this.categoria = builder.categoria;
        this.marca = builder.marca;
        this.informacoes = builder.informacoes;
        this.quantidadeEstoque = builder.quantidadeEstoque;
    }

    public static class ProdutoEntityBuilder {
        private Long id;
        private String nome;
        private String descricao;
        private Double preco;
        private CategoriaEntity categoria;
        private String marca;
        private Map<String, String> informacoes;
        private Integer quantidadeEstoque;

        public ProdutoEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public ProdutoEntityBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public ProdutoEntityBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }
        public ProdutoEntityBuilder preco(Double preco) {
            this.preco = preco;
            return this;
        }
        public ProdutoEntityBuilder categoria(CategoriaEntity categoria) {
            this.categoria = categoria;
            return this;
        }
        public ProdutoEntityBuilder marca(String marca) {
            this.marca = marca;
            return this;
        }
        public ProdutoEntityBuilder informacoes(Map<String, String> informacoes) {
            this.informacoes = informacoes;
            return this;
        }
        public ProdutoEntityBuilder quantidadeEstoque(Integer quantidadeEstoque) {
            this.quantidadeEstoque = quantidadeEstoque;
            return this;
        }

        public ProdutoEntity build() {
            return new ProdutoEntity(this);
        }
    }

    public Long getId() {
        return id;
    }


    public Map<String, String> getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(Map<String, String> informacoes) {
        this.informacoes = informacoes;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProdutoEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                ", marca='" + marca + '\'' +
                ", informacoes=" + informacoes +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}
