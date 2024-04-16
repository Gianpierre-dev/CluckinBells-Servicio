package com.idat.CluckinBells.model.repository;

import com.idat.CluckinBells.model.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatosRepository extends JpaRepository<Plato, Long> {
	
	@Query(value = "SELECT * FROM plato p WHERE p.nombre LIKE %?1%", nativeQuery = true)
    List<Plato> findByNombre(String nombre);

    // Método para obtener los platos según el ID de la categoría utilizando una consulta nativa
    @Query(value = "SELECT * FROM plato WHERE categoria_id = ?1", nativeQuery = true)
    List<Plato> findByCategoriaId(Long categoriaId);
}
