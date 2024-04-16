package com.idat.CluckinBells.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "Despacho")
public class Despacho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;


	@ManyToOne
	@JoinColumn(name = "despachador_id", referencedColumnName = "id")
	private Usuario usuario_despacho;

	@ManyToOne
	@JoinColumn(name = "motorizado_id", referencedColumnName = "id")
	private Usuario usuario_motorizado;


	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fecha;

	public Despacho() {

	}

	public Despacho(Long id, Pedido pedido, Usuario usuario_despacho, Usuario usuario_motorizado, Timestamp fecha) {
		this.id = id;
		this.pedido = pedido;
		this.usuario_despacho = usuario_despacho;
		this.usuario_motorizado = usuario_motorizado;
		this.fecha = fecha;
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

	public Usuario getUsuario_despacho() {
		return usuario_despacho;
	}

	public void setUsuario_despacho(Usuario usuario_despacho) {
		this.usuario_despacho = usuario_despacho;
	}

	public Usuario getUsuario_motorizado() {
		return usuario_motorizado;
	}

	public void setUsuario_motorizado(Usuario usuario_motorizado) {
		this.usuario_motorizado = usuario_motorizado;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
}