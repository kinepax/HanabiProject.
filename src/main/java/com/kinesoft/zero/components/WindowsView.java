package com.kinesoft.zero.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public  class WindowsView extends VerticalLayout   {

	public Dialog dialog= new Dialog();
	 public Button btnAceptar = new Button("Aceptar");
	 public Button btnCancelar= new Button("Cancelar");
	 public Boolean respuesta=false;

	 public interface ConfirmarListener {
			 void onConfirmacionSeleccionada(boolean respuesta);

		}
	 
	public void initListener() {

	
		
	}
	
	public void onAceptar() {
		
		
	}
	
	
	
	
	public void showDialog(WindowsView view) {
		
		
		
		dialog.add(view);
		dialog.open();
	//	dialog.setSizeFull();
		
	}
	
    public  void showDialog() {
        dialog = new Dialog(this) {
        };
        
        dialog.open();
    }
    
    
    
    public static void onPreguntarGrabar(String mensaje, ConfirmarListener listener) {
   
    	 Dialog dialog = new Dialog();
 	    dialog.add(new Text(mensaje));
 	    Button btnSi = new Button("Sí");
 	    Button btnNo = new Button("No");
 	    dialog.add(btnSi, btnNo);
 	    btnSi.addClickListener(e -> {
 	        dialog.close();
 	        
 	       listener.onConfirmacionSeleccionada(true);
 	    });
 	    btnNo.addClickListener(e -> {
 	        dialog.close();
 	       listener.onConfirmacionSeleccionada(false);
 	       
 	    
 	    });
 	    dialog.open();

		

    	
    }
    

    

    
    public void onGrabadoCorrectamente() {
    	
    	
    }
	  
	
	public void closeDialog() {
		System.out.println("Llegue al cerrar");
		dialog.close();
		
	}
	
	
	public void onError(String text) {
		System.out.println("Llegue al error");

		dialog.add(new Text(text));

		dialog.open();

		
		
	}
	
	public void disableCloseEsc(boolean estado) {
		
		dialog.setCloseOnEsc(estado);
		dialog.setCloseOnOutsideClick(estado);	

		
	}





	
	
}
