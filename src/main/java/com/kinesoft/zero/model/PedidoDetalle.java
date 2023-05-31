package com.kinesoft.zero.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PedidoDetalle {

	public int 				id;
	public Pedido 			pedidoCabecera;
	public Producto 		producto;
	public int 				cantidad;
	public BigDecimal 		precio;
	public BigDecimal 		total;


	
	public PedidoDetalle(Pedido pedidoCabecera, Producto producto, int cantidad, BigDecimal precio, BigDecimal total) {
		super();
		this.pedidoCabecera = pedidoCabecera;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}

	public PedidoDetalle() {
		
		
	};

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
		PedidoDetalle other = (PedidoDetalle) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Pedido getPedidoCabecera() {
		return pedidoCabecera;
	}

	public void setPedidoCabecera(Pedido pedidoCabecera) {
		this.pedidoCabecera = pedidoCabecera;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}