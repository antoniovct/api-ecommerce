package com.antoniovictor.catalogservice.domain.entities.categoria;


public class Categoria {
    private Long id;
    private String nome;


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria() {}
    public Categoria(CategoriaBuilder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
    }

    public static class CategoriaBuilder {
        private Long id;
        private String nome;

        public CategoriaBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public CategoriaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Categoria build() {
            return new Categoria(this);
        }
    }
}
