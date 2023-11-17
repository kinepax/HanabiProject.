package com.kinesoft.zero.views.producto;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Producto;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class ProductosUI extends WindowsView {

	public GridView<Producto> grid = new GridView<>(Producto.class,true);
	Button btnAgregar = new Button("Agregar");
	Button btnEditar = new Button("Editar");
	Button btnEliminar = new Button("Eliminar");
	Button btnRefrescar = new Button("Refrescar");

	HorizontalLayout pnlObciones = new HorizontalLayout();
	HorizontalLayout pnlFiltros 	= new HorizontalLayout();

	TextField txtNombre= new TextField("Nombre");

	public Button btnSeleccionar	= new Button("Seleccionar");

	public ProductosUI() {
		initStyles();

		initData();
		initListener();
	}
	public void initStyles(){

		pnlFiltros.setAlignItems(Alignment.BASELINE);

	}
	public void initData() {
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar, btnRefrescar,btnSeleccionar);
		pnlFiltros.add(txtNombre,btnRefrescar);


		grid.setColumns("nombre", "descripcion","precio","stock");

		add(pnlObciones,pnlFiltros, grid);

	}

	public void initListener() {

		btnAgregar		.addClickListener(e -> onadd());
		btnRefrescar	.addClickListener(e -> onRefrescar());
		btnEditar		.addClickListener(e -> onEditar());
		btnEliminar		.addClickListener(e -> onEliminar());
		txtNombre		.addKeyUpListener(Key.ENTER, e->onRefrescar());

	}

	public abstract String onadd();

	public abstract String onRefrescar();

	public abstract void onEditar();

	public abstract String onEliminar();

}
