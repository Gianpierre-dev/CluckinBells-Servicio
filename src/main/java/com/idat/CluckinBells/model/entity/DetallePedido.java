package com.idat.CluckinBells.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalleventa")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int cantidad;
	private BigDecimal subtotal;

	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "platos_id", referencedColumnName = "id")
	private Plato plato;

	public DetallePedido() {
	}

	public DetallePedido(Long id, Pedido pedido, Plato plato, int cantidad, BigDecimal subtotal) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.plato = plato;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Plato getProducto() {
		return plato;
	}

	public void setProducto(Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}


}
	

