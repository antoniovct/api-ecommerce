package com.antoniovictor.catalogservice.infra.gateway;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.infra.entities.CategoriaEntity;
import com.antoniovictor.catalogservice.infra.entities.ProdutoEntity;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;
import com.antoniovictor.catalogservice.infra.persistence.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoriaJpaRepositoryGateway implements CategoriaGateway {
    private final CategoriaRepository categoriaRepository;

    public CategoriaJpaRepositoryGateway(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @Override
    public Categoria salvar(Categoria categoria) {
        var categoriaEntity = CategoriaMapper.categoriaToCategoriaEntity(categoria);
        return CategoriaMapper.categoriaEntityToCategoria(categoriaRepository.save(categoriaEntity));

    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll().stream().map(CategoriaMapper::categoriaEntityToCategoria).toList();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        var categoriaEntity = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        return CategoriaMapper.categoriaEntityToCategoria(categoriaEntity);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        categoriaRepository.deleteById(id);
    }
}
