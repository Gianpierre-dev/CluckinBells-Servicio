package com.idat.CluckinBells.model.services;

import com.idat.CluckinBells.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> usuarios();
    Usuario buscarUsuarioId(Long id);
    Usuario guardarUsuario(Usuario usuario);

    Usuario buscarUsername(String username);
    void cambioEstado(Long id, boolean estado);
    boolean existeCorreoElectronico(String correoElectronico);
}
