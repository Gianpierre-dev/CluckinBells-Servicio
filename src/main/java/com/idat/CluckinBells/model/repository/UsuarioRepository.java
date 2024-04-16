package com.idat.CluckinBells.model.repository;

import com.idat.CluckinBells.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByCorreoElectronico(String correoElectronico);
}
