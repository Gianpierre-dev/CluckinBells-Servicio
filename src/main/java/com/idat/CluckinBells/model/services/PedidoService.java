package com.idat.CluckinBells.model.services;

import com.idat.CluckinBells.model.dto.GenerarPedidoDTO;
import com.idat.CluckinBells.model.dto.PedidoConDetallesDTO;


import java.util.List;

public interface PedidoService {

    List<PedidoConDetallesDTO> listaPedidosCliente(Long idcliente);

    GenerarPedidoDTO guardarPedido(GenerarPedidoDTO dto);
}
