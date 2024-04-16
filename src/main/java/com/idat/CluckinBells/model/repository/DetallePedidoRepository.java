package com.idat.CluckinBells.model.repository;

import com.idat.CluckinBells.model.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    @Query(value = "SELECT * FROM detalleventa WHERE pedido_id = :pedidoId", nativeQuery = true)
    List<DetallePedido> findByPedidoId(@Param("pedidoId") Long pedidoId);

}
