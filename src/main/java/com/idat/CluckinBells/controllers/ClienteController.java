package com.idat.CluckinBells.controllers;

import com.idat.CluckinBells.config.ApiResponse;
import com.idat.CluckinBells.model.entity.Cliente;
import com.idat.CluckinBells.model.entity.Usuario;
import com.idat.CluckinBells.model.services.ClienteService;
import com.idat.CluckinBells.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = { "*" })
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("guardar")
    public ResponseEntity<ApiResponse<Cliente>> guardarCliente(@RequestBody Cliente cliente) {

        Cliente clienteExistente = clienteService.existeCorreoElectronico(cliente.getCorreo());
        if (clienteExistente != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Ya existe un cliente con este correo electrónico", clienteExistente));
        } else {
            Cliente clienteGuardado = clienteService.guardarCliente(cliente);
            if (clienteGuardado != null) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Cliente guardado correctamente", clienteGuardado));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse<>(false, "No se pudo guardar el cliente", null));
            }
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> getUsuarioById(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClienteId(id);
        if (cliente != null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se encontró cliente", cliente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(false, "Cliente no existe.",
                            null));
        }
    }

    @GetMapping("/existe-correo")
    public ResponseEntity<ApiResponse<Cliente>> existeCorreoElectronico(@RequestParam String correoElectronico) {
        Cliente cliente = clienteService.existeCorreoElectronico(correoElectronico);
        if(cliente != null){
            return ResponseEntity.ok(new ApiResponse<>(true, "Se encontró cliente", cliente ));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(false, "Cliente no existe.",
                            null));
        }

    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Cliente>> login(@RequestParam String username, @RequestParam String password) {

        Cliente cliente = clienteService.buscarUsername(username);

        if (cliente != null  && cliente.getContrasena().equals(password)) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Inicio de sesión exitoso", cliente));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ApiResponse<>(false, "Credenciales inválidas", null));
        }
    }
}
