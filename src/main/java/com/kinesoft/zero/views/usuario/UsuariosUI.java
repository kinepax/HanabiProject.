package com.kinesoft.zero.views.usuario;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.Usuario;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class UsuariosUI extends WindowsView {

	public GridView<Usuario> grid 			= new GridView<>(Usuario.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	public Button btnSeleccionar			= new Button("Seleccionar");
	public Button btnClose 				= new Button("Cerrar");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();
	public UsuariosUI() {
		initDataUI();
		initListener();
	}

	public void initDataUI() {
		System.out.println("");
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar,btnSeleccionar, btnRefrescar,btnClose);
		// grid.setColumns("id","Dni","Nombre");
		grid.setColumns("id", "nombre", "usuario","pass");

		add(pnlObciones, grid);

	}

	public void initListener() {
		btnClose.addClickListener(e->{
			onCerrar();
			
			
		});
		btnAgregar.addClickListener(e -> onadd());
		btnRefrescar.addClickListener(e -> onRefrescar());
		btnEditar.addClickListener(e -> onEditar());
		btnEliminar.addClickListener(e -> onEliminar());

	}

	public abstract String onadd();

	public abstract String onRefrescar();

	public abstract String onEditar();

	public abstract String onEliminar();
	
	public abstract void onCerrar();
	
	public abstract void onSeleccionar();
	
}
