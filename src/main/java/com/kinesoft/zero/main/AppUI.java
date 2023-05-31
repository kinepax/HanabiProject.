package com.kinesoft.zero.main;

import com.kinesoft.zero.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;



@Route("")

public class AppUI extends VerticalLayout{
	
	
	public AppLayout layoutApp = new AppLayout();
	public Main main = new Main();
    public VerticalLayout content = new VerticalLayout();
    
    HorizontalLayout logoutLayout = new HorizontalLayout();
    

    

	
	public AppUI() {
		UI.getCurrent().getSession().setAttribute("app", this);

		DrawerToggle toggle = new DrawerToggle();
		H1 titulo = new H1("Project Zero");
		
		
		
		
		
		titulo.getStyle().set("font-size", "var(--lumo-font-size-l)")
        .set("margin", "0");
		
		layoutApp.addToDrawer(main);
		
		
		logoutLayout.setWidthFull();
        logoutLayout.setAlignItems(Alignment.CENTER); 
	    logoutLayout.add(toggle,logoutButton);

	    // Agregar el HorizontalLayout en el navbar
		
		
		
		//layoutApp.addToNavbar(toggle);
	    layoutApp.getElement().getStyle().set("width", "100%");

	    layoutApp.addToNavbar(logoutLayout);
		layoutApp.setContent(content);

	    
	    
        content.setSizeFull();
        content.setPadding(false);
        content.setMargin(false);
        content.setAlignItems(Alignment.CENTER);

	    
	    
	    
	    
     //   this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setSizeFull();


		this.add(layoutApp);
		
     //   content.setAlignItems(Alignment.CENTER);
		
		VaadinSession sesion = UI.getCurrent().getSession();
		
		if(sesion.getAttribute("usuario")==null) {
			
		      LoginView loginView = new LoginView(this);     
		       loginView.setSizeFull();
		       loginView.showDialog();
		       loginView.disableCloseEsc(false);
			
			
		}
	 
	      
		
	}
	
    public static AppUI get() {
        AppUI app = (AppUI) UI.getCurrent().getSession().getAttribute("app");
        return app;
    }
    
    
    public static AppUI getUsuario() {
        AppUI app = (AppUI) UI.getCurrent().getSession().getAttribute("usuario");
        return app;
    }
    

    public static void closeSession() throws Exception {
        VaadinSession session = UI.getCurrent().getSession();
        session.close();
    }
    
    // Crear botón de cerrar sesión
    Button logoutButton = new Button("Cerrar sesión", event -> {
        try {
            // Cerrar sesión
            closeSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirigir a la página de inicio de sesión
        UI.getCurrent().getPage().reload();
    });
	

}
