package com.kinesoft.zero.views.producto;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Producto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class ProductosUI extends WindowsView {

	public GridView<Producto> grid = new GridView<>(Producto.class,true);
	Button btnAgregar = new Button("Agregar");
	Button btnEditar = new Button("Editar");
	Button btnEliminar = new Button("Eliminar");
	Button btnRefrescar = new Button("Refrescar");

	HorizontalLayout pnlObciones = new HorizontalLayout();
	public Button btnSeleccionar	= new Button("Seleccionar");

	public ProductosUI() {

		initData();
		initListener();
	}

	public void initData() {
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar, btnRefrescar,btnSeleccionar);
		
		grid.setColumns("id", "nombre", "descripcion","precio","stock");

		add(pnlObciones, grid);

	}

	public void initListener() {

		btnAgregar.addClickListener(e -> onadd());
		btnRefrescar.addClickListener(e -> onRefrescar());
		btnEditar.addClickListener(e -> onEditar());
		btnEliminar.addClickListener(e -> onEliminar());

	}

	public abstract String onadd();

	public abstract String onRefrescar();

	public abstract void onEditar();

	public abstract String onEliminar();

}
