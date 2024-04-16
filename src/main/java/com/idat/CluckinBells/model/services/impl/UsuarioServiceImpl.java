package com.idat.CluckinBells.model.services.impl;


import com.idat.CluckinBells.model.entity.Usuario;
import com.idat.CluckinBells.model.repository.UsuarioRepository;
import com.idat.CluckinBells.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> usuarios() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Usuario buscarUsuarioId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarUsername(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void cambioEstado(Long id, boolean estado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEstado(estado);
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean existeCorreoElectronico(String correoElectronico) {
        return usuarioRepository.existsByCorreoElectronico(correoElectronico);
    }



}
