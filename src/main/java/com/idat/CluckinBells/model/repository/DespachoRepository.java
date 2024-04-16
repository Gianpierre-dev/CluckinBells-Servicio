package com.idat.CluckinBells.model.repository;

import com.idat.CluckinBells.model.entity.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
