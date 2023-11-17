package com.kinesoft.zero.views.usuario;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Usuario;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class UsuariosUI extends WindowsView {

	public GridView<Usuario> grid 			= new GridView<>(Usuario.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	public Button btnSeleccionar			= new Button("Seleccionar");
	public Button btnClose 				= new Button("Cerrar");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();
	HorizontalLayout pnlFiltros 	= new HorizontalLayout();

	TextField txtNombre = new TextField("Nombre");
	TextField txtUsuario = new TextField("Usuario");

	public UsuariosUI() {
		initStyles();
		initDataUI();
		initListener();
	}
	public void initStyles(){

		pnlFiltros.setAlignItems(Alignment.BASELINE);

	}

	public void initDataUI() {
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar,btnSeleccionar,btnClose);
		pnlFiltros.add(txtNombre,txtUsuario,btnRefrescar);
		// grid.setColumns("id","Dni","Nombre");
		grid.setColumns("nombre", "usuario");

		add(pnlObciones,pnlFiltros, grid);

	}

	public void initListener() {
		btnClose.addClickListener(e->{
			onCerrar();
			
			
		});
		btnAgregar.addClickListener		(e -> onadd());
		btnRefrescar.addClickListener	(e -> onRefrescar());
		btnEditar.addClickListener		(e -> onEditar());
		btnEliminar.addClickListener	(e -> onEliminar());
		txtNombre.addKeyUpListener		(Key.ENTER,e->onRefrescar());
		txtUsuario.addKeyUpListener		(Key.ENTER,e->onRefrescar());

	}

	public abstract String onadd();

	public abstract String onRefrescar();

	public abstract String onEditar();

	public abstract String onEliminar();
	
	public abstract void onCerrar();
	
	public abstract void onSeleccionar();
	
}
