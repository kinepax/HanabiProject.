package com.kinesoft.zero.views.producto;

import java.math.BigDecimal;

import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.servicesImpl.ProductoServiceImpl;

public class ProductoView extends ProductoUI {

	Producto product = new Producto();
	ProductoServiceImpl servicio= new ProductoServiceImpl();
	public boolean save;
	
	public ProductoView(Producto entity) {
		this.save=entity==null;
		System.out.println("El valor de save es "+save);
		initData(entity);
		
		
	}

	public ProductoView() {
		this(null);
	}

	/*
	public void ProductosView(){
				
	}
	
	*/

	public void initData(Producto producto) {
		if(save) {
			return;
			
		}
		txtId.setText(""+producto.getId());
		txtNombre.setValue(producto.getNombre());
		txtDescripcion.setValue(producto.getDescripcion());
		txtPrecio.setValue(""+producto.getPrecio());
		txtStock.setValue(""+producto.getStock());
		
	}
	
	@Override
	public String onCerrar() {
		this.closeDialog();
		return null;
		
	}

	@Override
	public String onSave() {
		product.setNombre(txtNombre.getValue());
		product.setDescripcion(txtDescripcion.getValue());
		product.setPrecio(new BigDecimal(txtPrecio.getValue()));
		product.setStock(Integer.parseInt(txtStock.getValue()));
		if(!save) {
			product.setId(Integer.parseInt( txtId.getText()));

			servicio.editar(product);
			this.closeDialog();
		
		}
		else {
			servicio.guardar(product);
			this.closeDialog();

			
		}
		

		return null;
	
	}
	
	
	


}
