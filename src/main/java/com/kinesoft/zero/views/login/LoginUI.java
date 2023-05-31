package com.kinesoft.zero.views.login;

import com.kinesoft.zero.components.WindowsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

public abstract class LoginUI extends WindowsView {
	TextField txtUsuario	 = new TextField("Usuario");
	TextField txtPass		 = new TextField("Contraseña");
	Button	 btnLogin		 = new Button("Login");

	
	

	public LoginUI() {
		initUI();
		initLister();
	}
	
	
	public void initUI() {
		
		this.add(txtUsuario,txtPass,btnLogin);
		this.disableCloseEsc(false);
        txtUsuario.focus();

		
	
	}
	
	public void initLister() {
		
		btnLogin.addClickListener(e -> onValidar());

	}
	
	public abstract void onValidar();
}
