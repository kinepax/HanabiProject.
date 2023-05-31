package com.kinesoft.zero.views.producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Producto;

import com.kinesoft.zero.servicesImpl.ProductoServiceImpl;


public class ProductosView extends ProductosUI{
	
	List<Producto>productos= new ArrayList<>();
	Producto entity= new Producto();
	ProductoServiceImpl productoServiceImpl = new ProductoServiceImpl();

	public ProductosView(){
		iniData();
		
		
	}
	
	public void iniData() {
		onRefrescar();
	}

	@Override
	public String onadd() {
		ProductoView view = new ProductoView();
		view.setSizeFull();
		view.showDialog(view);
		
		return null;
	}

	@Override
	public String onRefrescar() {
		try {
			productos=ProductoServiceImpl.listarProductos();
			System.out.println(productos.size());
			grid.setItems(productos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void onEditar() {
		entity=grid.getValue();
		if(entity==null) {
			System.out.print("Entre aqui en el null");
			WindowsView view= new WindowsView();
			view.onError("Debes seleccionar un item para editar");
			view.showDialog(view);
			view.setSizeFull();

			return ;
		}
	

			
			ProductoView view = new ProductoView(entity);
			view.setSizeFull();
			view.showDialog(view);
			
			
			System.out.println(entity.getNombre());
			
	
		
		return ;
		
	}

	@Override
	public String onEliminar() {
		entity=grid.getValue();
		try {
			productoServiceImpl.eliminar(entity);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
