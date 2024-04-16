package com.idat.CluckinBells.model.services.impl;

import com.idat.CluckinBells.model.dto.GenerarPedidoDTO;
import com.idat.CluckinBells.model.dto.PedidoConDetallesDTO;
import com.idat.CluckinBells.model.entity.DetallePedido;
import com.idat.CluckinBells.model.entity.Pedido;
import com.idat.CluckinBells.model.repository.DetallePedidoRepository;
import com.idat.CluckinBells.model.repository.PedidoRepository;
import com.idat.CluckinBells.model.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<PedidoConDetallesDTO> listaPedidosCliente(Long idcliente) {

        List<PedidoConDetallesDTO> pedidosdto = new ArrayList<>();

        List<Pedido> clientePedidos = pedidoRepository.findByClienteId(idcliente);

        for (Pedido p : clientePedidos) {
            List<DetallePedido> detallesPedido = detallePedidoRepository.findByPedidoId(p.getId());
            PedidoConDetallesDTO pedidoConDetallesDTO = new PedidoConDetallesDTO(p, detallesPedido);
            pedidosdto.add(pedidoConDetallesDTO);
        }

        return pedidosdto;
    }

    @Override
    public GenerarPedidoDTO guardarPedido(GenerarPedidoDTO dto) {

        DetallePedido detallePedidoRetorno = new DetallePedido();
        Pedido pedidoRetorno = new Pedido();

        System.out.println("TOTAL "+ dto.getPedido().getTotal());

        dto.getPedido().setFecha(new java.sql.Date(new Date().getTime()));
        dto.getPedido().setTotal(dto.getPedido().getTotal());
        dto.getPedido().setCliente(dto.getCliente());
        dto.getPedido().setMetodoPago("Tarjeta credito");
        dto.getPedido().setEstado(true);

        pedidoRetorno = this.pedidoRepository.save(dto.getPedido());

        for (DetallePedido dp: dto.getDetallePedidos()){
            dp.setPedido(pedidoRetorno);
            dp.setCantidad(dp.getCantidad());
            dp.setPlato(dp.getPlato());
            dp.setSubtotal(dp.getSubtotal());
            detallePedidoRetorno = this.detallePedidoRepository.save(dp);
        }

        return new GenerarPedidoDTO(pedidoRetorno, dto.getDetallePedidos(),dto.getCliente());
    }

}
