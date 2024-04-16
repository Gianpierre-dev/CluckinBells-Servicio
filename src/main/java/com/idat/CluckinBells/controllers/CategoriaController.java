package com.idat.CluckinBells.controllers;


import com.idat.CluckinBells.config.ApiResponse;
import com.idat.CluckinBells.model.entity.Categoria;
import com.idat.CluckinBells.model.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Categoria> guardarCategoria(
            @RequestPart("categoria") Categoria categoria,
            @RequestPart(name = "imagen") MultipartFile imagen) throws IOException {

        if (imagen != null && !imagen.isEmpty()) {
            byte[] imagenBytes = imagen.getBytes();
            categoria.setImagenCategoria(imagenBytes);
        }

        Categoria categoriaGuardada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGuardada);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Categoria>>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        ApiResponse<List<Categoria>> response = new ApiResponse<>(true, "Categorías obtenidas exitosamente", categorias);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoria);
    }


}
