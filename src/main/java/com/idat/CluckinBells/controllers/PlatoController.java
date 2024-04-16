package com.idat.CluckinBells.controllers;

import com.idat.CluckinBells.config.ApiResponse;
import com.idat.CluckinBells.model.entity.Plato;
import com.idat.CluckinBells.model.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*")
public class PlatoController {

    @Autowired
    private PlatoService platoService;


	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<Plato> guardarPlato(@RequestPart("plato") Plato plato,
                                              @RequestPart(name = "imagen") MultipartFile imagen) throws IOException, java.io.IOException {

		if (imagen != null && !imagen.isEmpty()) {
			byte[] imagenBytes = imagen.getBytes();
			plato.setImagenProducto(imagenBytes);
		}

		Plato platoGuardado = platoService.guardarPlato(plato);
		return ResponseEntity.status(HttpStatus.CREATED).body(platoGuardado);
	}

    @GetMapping
    public ResponseEntity<ApiResponse<List<Plato>>> obtenerTodosLosPlatos() {
        List<Plato> platos = platoService.obtenerTodosLosPlatos();
        ApiResponse<List<Plato>> apiResponse = new ApiResponse<>(true, "Platos obtenidos exitosamente", platos);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> obtenerPlatoPorId(@PathVariable Long id) {
        Plato plato = platoService.obtenerPlatoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(plato);
    }


   
	@PutMapping("/{id}")
	public ResponseEntity<Plato> actualizarPlato(@PathVariable Long id,
			@RequestPart("plato") Plato plato,
			@RequestPart(name = "imagen", required = false) MultipartFile imagen) throws java.io.IOException {

		plato.setId(id);

		if (imagen != null && !imagen.isEmpty()) {
			try {
				byte[] imagenBytes = imagen.getBytes();
				plato.setImagenProducto(imagenBytes);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			}
		}

		Plato pedidoActualizado = platoService.actualizarPlato(plato);
		return ResponseEntity.status(HttpStatus.OK).body(pedidoActualizado);
	}
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlato(@PathVariable Long id) {
        platoService.eliminarPlato(id);
        return ResponseEntity.noContent().build();
    }


 
    @PostMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @PathVariable boolean estado) {
        try {
        	platoService.cambioEstado(id, estado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Plato>> buscarPlatoPorNombre(@PathVariable String nombre) {
        List<Plato> platos = platoService.buscarClienteNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(platos);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<ApiResponse<List<Plato>>> obtenerPlatosPorCategoria(@PathVariable Long categoriaId) {
        List<Plato> platos = platoService.obtenerPlatosPorCategoria(categoriaId);
        if (!platos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Platos obtenidos exitosamente", platos));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontraron platos para la categoría proporcionada", null));
        }
    }
}
