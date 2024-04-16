package com.idat.CluckinBells.model.services;

import com.idat.CluckinBells.model.entity.Plato;

import java.util.List;

public interface PlatoService {

    Plato guardarPlato(Plato plato);
    List<Plato> obtenerTodosLosPlatos();
    Plato obtenerPlatoPorId(Long id);
    Plato actualizarPlato(Plato plato);
    void eliminarPlato(Long id);
    void cambioEstado(Long id, boolean estado);
    List<Plato> buscarClienteNombre(String nombre);
    List<Plato> obtenerPlatosPorCategoria(Long categoriaId);
}
