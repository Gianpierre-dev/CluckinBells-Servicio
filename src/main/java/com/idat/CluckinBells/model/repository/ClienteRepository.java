package com.idat.CluckinBells.model.repository;

import com.idat.CluckinBells.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idat.CluckinBells.model.entity.Cliente;

import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByUsername(String username);
    @Query(value = "SELECT c FROM Cliente c WHERE c.correo = ?1")
    Optional<Cliente> findByCorreo(String correoElectronico);
}
