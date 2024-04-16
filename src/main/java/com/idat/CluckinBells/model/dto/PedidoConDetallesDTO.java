package com.idat.CluckinBells.model.dto;

import com.idat.CluckinBells.model.entity.DetallePedido;
import com.idat.CluckinBells.model.entity.Pedido;

import java.util.List;

public class PedidoConDetallesDTO {
    private Pedido pedido;
    private List<DetallePedido> detallePedido;

    public PedidoConDetallesDTO(Pedido pedido, List<DetallePedido> detallePedido) {
        this.pedido = pedido;
        this.detallePedido = detallePedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    @Override
    public String toString() {
        return "PedidoConDetallesDTO{" +
                "pedido=" + pedido +
                ", detallePedido=" + detallePedido +
                '}';
    }
}