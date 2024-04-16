package com.idat.CluckinBells.controllers;

import com.idat.CluckinBells.config.ApiResponse;
import com.idat.CluckinBells.model.dto.GenerarPedidoDTO;
import com.idat.CluckinBells.model.dto.PedidoConDetallesDTO;
import com.idat.CluckinBells.model.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = { "*" })
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/cliente/{idCliente}")
    public ApiResponse<List<PedidoConDetallesDTO>> listaPedidosCliente(@PathVariable Long idCliente) {
        try {
            List<PedidoConDetallesDTO> pedidos = pedidoService.listaPedidosCliente(idCliente);
            return new ApiResponse<>(true, "Lista de pedidos obtenida correctamente", pedidos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(false, "Error al obtener la lista de pedidos: " + e.getMessage(), null);
        }
    }

    @PostMapping
    public ApiResponse<GenerarPedidoDTO> guardarPedido(@RequestBody GenerarPedidoDTO dto) {

        GenerarPedidoDTO pedidoGuardado = pedidoService.guardarPedido(dto);
        if(pedidoGuardado.getPedido().getId() != null){
            return new ApiResponse<>(true, "Pedido guardado exitosamente", pedidoGuardado);
        }else{
            return new ApiResponse<>(false, "Error al guardar el pedido", null);
        }
    }

}
