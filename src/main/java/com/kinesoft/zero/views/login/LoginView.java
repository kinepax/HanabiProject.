package com.kinesoft.zero.views.login;

import java.sql.SQLException;

import com.kinesoft.zero.main.AppUI;
import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.servicesImpl.UsuarioServiceImpl;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;





public class LoginView extends LoginUI{
	 Usuario usuario  = new Usuario();
	 private final LoginForm login = new LoginForm(); 
	    public AppUI app;

	  

	    
	    public LoginView(AppUI app) {
	        this.app = app;
	        this.setSizeFull();
	    	/*
			setSizeFull(); 

			login.setAction("login"); 
			add(new H1("Vaadin CRM"), login);
			
			login.addLoginListener(e->{
				
				if("kinepax".equals(e.getUsername())
						
						&& "123".equals(e.getPassword())
						
						)
				{
					UI.getCurrent().navigate("pedidos");
					
				}
					
				
				
			});
*/
	     
	    
	    }



		@Override
		public void onValidar() {		
			
			usuario.setId(1);
			usuario.setUsuario(txtUsuario.getValue());
			usuario.setPass(txtPass.getValue());
			
			try {
				
				usuario=UsuarioServiceImpl.validarUsuario(usuario);
				
				if(usuario.getNombre()!=null) {					
					Notification.show("Hola " + usuario.getUsuario());
					VaadinSession.getCurrent().setAttribute("usuario", usuario.getUsuario());
					
					this.closeDialog();
				}
				else {					
					Notification.show("Usuario incorrecto");

				}
				
			
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}
