package com.kinesoft.zero.views.cliente;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Producto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public abstract class ClienteUI extends WindowsView {

	Button btnGrabar 				= new Button("Grabar");
	Button btnCerrar 				= new Button("Cerrar");
	TextField txtNombre 			= new TextField("Nombre");
	TextField txtDni 				= new TextField("DNI");
	Text txtId 						= new Text("");	
	HorizontalLayout pnlObciones 	= new HorizontalLayout();

	public ClienteUI() {
		initData();
	}

	public void initData() {
		txtDni.setPattern("\\d*");
		//txtDni.setPreventInvalidInput(true);
		

		pnlObciones.add(btnGrabar, btnCerrar);
		add(txtNombre, txtDni, pnlObciones);
		intiListener();
		initStyles();
	}

	public void initStyles() {
		this.setSizeFull();
	}

	public void intiListener() {
		btnCerrar.addClickListener			(e -> onCerrar());
		btnGrabar.addClickListener			(e -> onSave());

	}
	
	public abstract void onCerrar();
	public abstract void onSave();

}
