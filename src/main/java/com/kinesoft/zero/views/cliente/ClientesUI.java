package com.kinesoft.zero.views.cliente;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.Producto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class ClientesUI extends WindowsView {

	public GridView<Cliente> grid 	= new GridView<>(Cliente.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	public Button btnSeleccionar	= new Button("Seleccionar");
	public Button btnClose 			= new Button("Cerrar");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();

	public ClientesUI() {
		initDataUI();
		initListener();
	}

	public void initDataUI() {
		pnlObciones.add(btnAgregar,
						btnEditar,
						btnEliminar,
						btnSeleccionar,
						btnRefrescar
						,btnClose);
		
		
		grid.setColumns("id", "dni", "nombre");

		add(pnlObciones, grid);
		this.setSizeFull();

	}

	public void initListener() {
		btnClose		.addClickListener			(e->{	onCerrar();});
		btnAgregar		.addClickListener			(e -> onadd());
		btnRefrescar	.addClickListener			(e -> onRefrescar());
		btnEditar		.addClickListener			(e -> onEditar());
		btnEliminar		.addClickListener			(e -> onEliminar());

	}

	public abstract void 	 onadd();
	public abstract void 	 onRefrescar();
	public abstract void	 onEditar();
	public abstract void	 onEliminar();	
	public abstract void 	 onCerrar();	
	public abstract void 	 onSeleccionar();


}