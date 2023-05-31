package com.kinesoft.zero.views.usuario;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.ItemVenta;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.model.Usuario_Mesas;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public abstract class UsuarioUI extends WindowsView {

	Button btnGrabar 			= new Button("Grabar");
	Button btnCerrar 			= new Button("Cerrar");
	TextField txtNombre 		= new TextField("Nombre");
	TextField txtUsuario 		= new TextField("Usuario");
	TextField txtPass 			= new TextField("Contraseña");

	HorizontalLayout primera_fase = new HorizontalLayout();

	Text txtId = new Text("");
	
	HorizontalLayout division = new HorizontalLayout();

	Button btnagregarMesa= new Button("+");
	Button btnquitarMesa= new Button("-");

	VerticalLayout obcionesMesa=new VerticalLayout(); 
	
	
	GridView<Usuario_Mesas>usuariosxmesas_grid= new GridView<>(Usuario_Mesas.class);
	GridView<Mesa>mesas_grid= new GridView<>(Mesa.class);
	
	

	HorizontalLayout pnlObciones = new HorizontalLayout();
	


	public UsuarioUI() {

		initData();
	}

	public void initData() {
		System.out.println("");
		obcionesMesa.add(btnagregarMesa,btnquitarMesa);
		primera_fase.add(txtNombre, txtUsuario,txtPass);
		division.add(usuariosxmesas_grid,obcionesMesa,mesas_grid);
		usuariosxmesas_grid.setSizeFull();
		mesas_grid.setSizeFull();
		division.setSizeFull();
		pnlObciones.add(btnGrabar,btnCerrar);
		add(primera_fase,division, pnlObciones);
		this.setSizeFull();
		mesas_grid.addCol(Mesa::getNombre, "Nombre de Mesa");
		usuariosxmesas_grid.addCol(Usuario_Mesas::getStrNomMesa,"Mesa Asociada");
		
		intiListener();
		initStyles();
	}

	public void initStyles() {

		this.setSizeFull();

	}

	public void intiListener() {
		btnCerrar.addClickListener(e -> onCerrar());
		btnGrabar.addClickListener(e -> onSave());
		btnquitarMesa.addClickListener(e->onQuitar());
		btnagregarMesa.addClickListener(e->onAgregar());

	}

	public abstract String onCerrar();

	public abstract String onSave();
	
	public abstract void onCargar();
	
	public abstract void onAgregar();
	
	public abstract void onQuitar();

}
