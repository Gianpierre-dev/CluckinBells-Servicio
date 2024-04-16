package com.idat.CluckinBells.model.services.impl;

import com.idat.CluckinBells.model.entity.Categoria;
import com.idat.CluckinBells.model.repository.CategoriaRepository;
import com.idat.CluckinBells.model.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + id));
    }
}
