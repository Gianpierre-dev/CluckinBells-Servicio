package com.idat.CluckinBells.model.dto;

import com.idat.CluckinBells.model.entity.Cliente;
import com.idat.CluckinBells.model.entity.DetallePedido;
import com.idat.CluckinBells.model.entity.Pedido;

import java.util.ArrayList;

public class GenerarPedidoDTO {

    private Pedido pedido;
    private ArrayList<DetallePedido> detallePedidos;
    private Cliente cliente;

    public GenerarPedidoDTO(Pedido pedido, ArrayList<DetallePedido> detallePedidos, Cliente cliente) {
        this.pedido = pedido;
        this.detallePedidos = detallePedidos;
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ArrayList<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(ArrayList<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}