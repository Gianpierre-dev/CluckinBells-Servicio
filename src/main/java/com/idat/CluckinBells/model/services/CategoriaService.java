package com.idat.CluckinBells.model.services;

import com.idat.CluckinBells.model.entity.Categoria;

import java.util.List;

public interface CategoriaService {
	
    Categoria guardarCategoria(Categoria categoria);
    List<Categoria> obtenerTodasLasCategorias();
    Categoria obtenerCategoriaPorId(Long id);
   
}
