package com.idat.CluckinBells.controllers;

import com.idat.CluckinBells.config.ApiResponse;
import com.idat.CluckinBells.model.entity.Usuario;
import com.idat.CluckinBells.model.services.UsuarioService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.usuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/existe-correo")
    public ResponseEntity<Boolean> existeCorreoElectronico(@RequestParam String correoElectronico) {
        boolean existe = usuarioService.existeCorreoElectronico(correoElectronico);
        return ResponseEntity.ok(existe);
    }

    @PostMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @PathVariable boolean estado) {
        try {
            usuarioService.cambioEstado(id, estado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Usuario>> login(@RequestParam String username, @RequestParam String password) {
        Usuario usuario = usuarioService.buscarUsername(username);

        if (usuario != null && usuario.isEstado() && usuario.getContrasena().equals(password)) {
            // Acceso correcto
            return ResponseEntity.ok(new ApiResponse<>(true, "Inicio de sesión exitoso", usuario));
        } else if (usuario != null && !usuario.isEstado()) {
            // Usuario bloqueado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ApiResponse<>(false, "Usuario bloqueado. Por favor, contacta al administrador.", null));
        } else {
            // Credenciales inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ApiResponse<>(false, "Credenciales inválidas", null));
        }
    }

}
