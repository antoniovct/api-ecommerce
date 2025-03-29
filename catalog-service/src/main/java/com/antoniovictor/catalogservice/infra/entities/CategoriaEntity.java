package com.antoniovictor.catalogservice.infra.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public CategoriaEntity() {
    }

    public CategoriaEntity(CategoriaEntityBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
    }

    public static class CategoriaEntityBuilder {
        private Long id;
        private String nome;

        public CategoriaEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public CategoriaEntityBuilder nome(String nome) {
            this.nome = nome;
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
                '}';
    }
}
