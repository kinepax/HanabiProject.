package com.kinesoft.zero.views.mesa;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Mesa;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class MesasUI extends WindowsView {

	public GridView<Mesa> grid 			= new GridView<>(Mesa.class);
	Button btnAgregar 					= new Button("Agregar");
	Button btnEditar 					= new Button("Editar");
	Button btnEliminar 					= new Button("Eliminar");
	Button btnRefrescar 				= new Button("Refrescar");
	public Button btnSeleccionar		= new Button("Seleccionar");
	public Button btnClose 				= new Button("Cerrar");
	HorizontalLayout pnlObciones 		= new HorizontalLayout();
	public MesasUI() {
		initDataUI();
		initListener();
	}

	public void initDataUI() {
		System.out.println("");
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar,btnSeleccionar, btnRefrescar,btnClose);
		grid.setColumns("id", "nombre");
		add(pnlObciones, grid);

	}

	public void initListener() {
		btnClose.addClickListener 			(e->{onCerrar();});
		btnAgregar.addClickListener			(e-> onadd());
		btnRefrescar.addClickListener		(e -> onRefrescar());
		btnEditar.addClickListener			(e -> onEditar());
		btnEliminar.addClickListener		(e -> onEliminar());

	}

	public abstract void onadd();
	public abstract void onRefrescar();
	public abstract void onEditar();
	public abstract void onEliminar();
	public abstract void onCerrar();	
	public abstract void onSeleccionar();
	
}
