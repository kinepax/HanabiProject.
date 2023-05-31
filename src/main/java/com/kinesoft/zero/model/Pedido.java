package com.kinesoft.zero.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


public class Pedido {

	public Integer id;
	public Cliente cliente;
	public Mesa mesa;
	public BigDecimal total;
	public String estado;
	public LocalDateTime fecha_hora;

	public Pedido(Integer id, Cliente cliente,Mesa mesa, BigDecimal total, String estado,LocalDateTime fecha_hora) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.mesa = mesa;
		this.total = total;
		this.estado = estado;
		this.fecha_hora=fecha_hora;
	}
	
	public Pedido( Cliente cliente,Mesa mesa, BigDecimal total, String estado) {
		super();
		this.cliente = cliente;
		this.mesa= mesa;
		this.total = total;
		this.estado = estado;
	}

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void getMesa() {
		this.mesa= mesa;		
	}
	
	public int getIdMesa() {
		return this.mesa.getId();
		
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa=mesa;
		
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(LocalDateTime fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

}