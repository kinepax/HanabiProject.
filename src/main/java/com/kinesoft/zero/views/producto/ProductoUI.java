package com.kinesoft.zero.views.producto;

import com.kinesoft.zero.components.WindowsView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class ProductoUI extends WindowsView {
	
	Button btnGrabar= new Button("Grabar");
	Button btnCerrar= new Button("Cerrar");
	TextField txtNombre= new TextField("Nombre");
	TextField txtDescripcion = new TextField("Descripcion del plato");
	TextField txtPrecio=new TextField("Precio");
	TextField txtStock = new TextField("Stock");
	Text txtId=new Text("");
	

	HorizontalLayout pnlObciones= new HorizontalLayout();
	
	public ProductoUI() {
		
		initData();
	}
	
	public void initData() {
		pnlObciones.add(btnGrabar,btnCerrar);
		add(txtNombre,txtDescripcion,txtPrecio,txtStock,pnlObciones);
		
		intiListener();
		initStyles();
	}
	
	public void initStyles() {
		
		this.setSizeFull();
		
	}
	public void intiListener(){
		btnCerrar.addClickListener(e->onCerrar());
		btnGrabar.addClickListener(e->onSave());

		}
	
	public    abstract    String onCerrar();
	public    abstract    String onSave();


}
	

