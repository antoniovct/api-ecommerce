package com.antoniovictor.catalogservice.infra.gateway;

import com.antoniovictor.catalogservice.application.gateway.CategoriaGateway;
import com.antoniovictor.catalogservice.domain.PageRequestDto;
import com.antoniovictor.catalogservice.domain.PageResponse;
import com.antoniovictor.catalogservice.domain.entities.categoria.Categoria;
import com.antoniovictor.catalogservice.domain.exception.CategoriaNaoEncontradaException;
import com.antoniovictor.catalogservice.infra.mapper.CategoriaMapper;
import com.antoniovictor.catalogservice.infra.persistence.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public PageResponse<Categoria> listarTodos(PageRequestDto pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.pageNumber(), pageRequest.pageSize());
        var categorias = categoriaRepository.findAll(pageable).map(CategoriaMapper::categoriaEntityToCategoria);
        return new PageResponse<Categoria>(categorias.getNumber(),categorias.getSize(),categorias.getTotalPages(),
                categorias.getTotalElements(),categorias.hasNext(),categorias.hasPrevious(),categorias.getContent());
    }

    @Override
    public Categoria buscarPorId(Long id) throws CategoriaNaoEncontradaException {
        var categoriaEntity = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada!"));
        return CategoriaMapper.categoriaEntityToCategoria(categoriaEntity);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        categoriaRepository.deleteById(id);
    }
}
