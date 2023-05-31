package com.kinesoft.zero.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.NumerText;
import com.vaadin.flow.component.grid.Grid;

public class ItemVenta {
//datos de control
	public int 						id;
	public Producto 				producto;
	public int 						cantidad;
	public BigDecimal 				total;
	public BigDecimal 				precio;

	public GridView<ItemVenta> 		grid;
	public BigDecimal 				totalDinero=BigDecimal.ZERO;
	public Boolean bandera;

	// datos visualesT

	public NumerText 			numerText = new NumerText();

	public ItemVenta(int id, Producto producto, int cantidad, BigDecimal total, BigDecimal precio,Boolean bandera) {
		super();
		this.bandera=bandera;

		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.numerText.setValue(Double.valueOf(cantidad));
		this.total = total;
		this.precio = precio;
		numerText.addValueChangeListener(e -> {
			this.cantidad 		= (int) Math.round(numerText.getValue());
			this.total 			= precio.multiply(new BigDecimal(this.cantidad));
			System.out.println(this.totalDinero+" + "+this.total);
			this.totalDinero=this.total;
			System.out.println("El dinero al cambiar es "+this.totalDinero);
		
			this.grid.getDataProvider().refreshItem(this);
			if (this.total!=null) {}

		});

	}

	public ItemVenta() {
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
		ItemVenta other = (ItemVenta) obj;
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

	public String getstrNomProducto() {
		return this.producto.getNombre();

	}

	public BigDecimal getBigDecimalPrecio() {
		if(bandera) {
			return this.getPrecio();
		}
		return this.producto.getPrecio();
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public NumerText getNumerText() {
		return numerText;
	}

	public BigDecimal getTotalDinero() {
		return totalDinero;
	}

	public void setTotalDinero(BigDecimal totalDinero) {
		this.totalDinero = totalDinero;
	}

}
