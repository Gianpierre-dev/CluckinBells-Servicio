package com.idat.CluckinBells.model.services.impl;

import com.idat.CluckinBells.model.entity.Plato;
import com.idat.CluckinBells.model.repository.PlatosRepository;
import com.idat.CluckinBells.model.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoServiceImpl implements PlatoService {

	@Autowired
    private PlatosRepository platoRepository;

    @Override
    public Plato guardarPlato(Plato producto) {
        return platoRepository.save(producto);
    }

	@Override
	public List<Plato> obtenerTodosLosPlatos() {
		return platoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Plato obtenerPlatoPorId(Long id) {
        return platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado con ID: " + id));
	}

	@Override
	public Plato actualizarPlato(Plato plato) {
		Optional<Plato> platoExistenteOptional = platoRepository.findById(plato.getId());

		if (platoExistenteOptional.isPresent()) {
			Plato platoExistente = platoExistenteOptional.get();
			platoExistente.setImagenProducto(plato.getImagenProducto());
			platoExistente.setPrecio(platoExistenteOptional.get().getPrecio());
			platoExistente.setCategoria(platoExistenteOptional.get().getCategoria());
			platoExistente.setId(platoExistenteOptional.get().getId());
			platoExistente.setEstado(platoExistenteOptional.get().isEstado());
			platoExistente.setStock(platoExistenteOptional.get().getStock());
			platoExistente.setNombre(platoExistenteOptional.get().getNombre());
			platoExistente.setDescripcion(platoExistenteOptional.get().getDescripcion());
			return platoRepository.save(platoExistente);
		} else {
			throw new RuntimeException("No se puede actualizar la imagen. Plato no encontrado con ID: " + plato.getId());
		}
	}


	@Override
	public void eliminarPlato(Long id) {
		platoRepository.deleteById(id);
		
	}

	@Override
	public void cambioEstado(Long id, boolean estado) {
		Plato producto = platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        producto.setEstado(estado);
        platoRepository.save(producto);
		
	}

	@Override
	public List<Plato> buscarClienteNombre(String nombre) {
		return platoRepository.findByNombre(nombre);
	}

	@Override
	public List<Plato> obtenerPlatosPorCategoria(Long categoriaId) {
		return platoRepository.findByCategoriaId(categoriaId);
	}
}
