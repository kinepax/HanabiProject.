package com.kinesoft.zero.views.cliente;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;

public abstract class ClientesUI extends WindowsView {

	public GridView<Cliente> grid 	= new GridView<>(Cliente.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	public Button btnSeleccionar	= new Button("Seleccionar");
	public Button btnClose 			= new Button("Cerrar");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();

	HorizontalLayout pnlFiltros 	= new HorizontalLayout();

	TextField txtDni= new TextField("Dni");

	TextField txtNombre= new TextField("Nombre");


	public ClientesUI() {
		initStyles();
		initDataUI();
		initListener();
	}
	public void initStyles(){

		pnlFiltros.setAlignItems(Alignment.BASELINE);

	}
	public void initDataUI() {
		pnlObciones.add(btnAgregar,
						btnEditar,
						btnEliminar,
						btnSeleccionar

						,btnClose);

		pnlFiltros.add(txtDni,txtNombre,btnRefrescar);
		grid.setColumns("dni", "nombre");

		add(pnlObciones,pnlFiltros, grid);
		this.setSizeFull();

	}



	public void initListener() {
		btnClose		.addClickListener			(e->{	onCerrar();});
		btnAgregar		.addClickListener			(e -> onadd());
		btnRefrescar	.addClickListener			(e -> onRefrescar());
		btnEditar		.addClickListener			(e -> onEditar());
		btnEliminar		.addClickListener			(e -> onEliminar());
		txtDni			.addKeyUpListener			(Key.ENTER,e->onRefrescar());
		txtNombre		.addKeyUpListener			(Key.ENTER,e->onRefrescar());

	}

	public abstract void 	 onadd();
	public abstract void 	 onRefrescar();
	public abstract void	 onEditar();
	public abstract void	 onEliminar();	
	public abstract void 	 onCerrar();	
	public abstract void 	 onSeleccionar();


}