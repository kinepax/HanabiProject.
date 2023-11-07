package com.kinesoft.zero.main;

import com.kinesoft.zero.views.cliente.ClientesView;
import com.kinesoft.zero.views.documentoPago.DocumentosPagoView;
import com.kinesoft.zero.views.mesa.MesasView;
import com.kinesoft.zero.views.pedido.PedidosView;
import com.kinesoft.zero.views.producto.ProductosView;
import com.kinesoft.zero.views.serie.SeriesView;
import com.kinesoft.zero.views.usuario.UsuariosView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.DomEvent;

public class Main extends VerticalLayout {


	
	public Tab tabClientesView = new Tab (VaadinIcon.USER_STAR.create(),new Label("Clientes"));
	public Tab tabMesasView = new Tab (VaadinIcon.AIRPLANE.create(),new Label("Mesas"));
	public Tab tabPedidosView = new Tab (VaadinIcon.CART.create(),new Label("Pedidos"));
	public Tab tabProductosView = new Tab (VaadinIcon.PACKAGE.create(),new Label("Productos"));
	public Tab tabUsuariosView = new Tab (VaadinIcon.WORKPLACE.create(),new Label("Usuarios"));

	public Tab tabSeriesView = new Tab (VaadinIcon.DESKTOP.create(),new Label("Series"));

	public Tab tabDocumentosPagoView = new Tab (VaadinIcon.CASH.create(),new Label("Documentos de Pago"));


	final Tabs tabs = new Tabs(tabClientesView,tabMesasView,tabPedidosView,tabProductosView,tabUsuariosView,tabSeriesView,tabDocumentosPagoView);
    public HorizontalLayout layOpciones = new HorizontalLayout();



    
    private ClientesView clientesView;
    private MesasView mesasView;
    private PedidosView pedidosView;
    private ProductosView productosView;
    private UsuariosView usuariosView;

	private SeriesView seriesView;
	private DocumentosPagoView documentosPagoView;





	public Main() {
		
		
		
		layOpciones.getStyle().set("overflow","hidden");
	    this.getStyle().set("overflow", "auto");
	    
//	    tabResultado.getStyle().set("color", "green");
	    tabs.setWidthFull();
	    layOpciones.setWidthFull();
	    layOpciones.add(tabs);
		
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		
		add(tabs);
		

		
		//if(AppUI.getUsuario()=null) {
			
			initListener();

			
			
		//}
	
			
	

	     initStyles();
	     
	
	     
	     
	     
		
	}
	
    private void initStyles() {
        this.setSizeFull();
        this.setSpacing(false);
        this.setMargin(false);
        this.setPadding(false);
        this.setAlignItems(Alignment.CENTER);

        
    }
    
    
    private void initListener() {
    	

	     
	     
	     tabClientesView.getElement().addEventListener("click", this::onClientesView);
	     tabMesasView.getElement().addEventListener("click", this::onMesasView);
	     tabPedidosView.getElement().addEventListener("click", this::onPedidosView);
	     tabProductosView.getElement().addEventListener("click", this::onProductosView);
	     tabUsuariosView.getElement().addEventListener("click", this::onUsuariosView);
		tabSeriesView.getElement().addEventListener("click", this::onSeriesView);
		tabDocumentosPagoView.getElement().addEventListener("click", this::onDocumentosPagoView);



    }

    
    public void onClientesView(DomEvent evento) {
    	
    	if(clientesView==null) {
    		
    		clientesView= new ClientesView();
    		
    	}
    	
    	
    	AppUI.get().content.removeAll();
    	AppUI.get().content.add(clientesView);

    


    }

    public void onMesasView(DomEvent evento) {
    	
    	if(mesasView==null) {
    		
    		mesasView= new MesasView();
    		
    	}
    	
    	
    	AppUI.get().content.removeAll();
    	AppUI.get().content.add(mesasView);

    


    }
    public void onPedidosView(DomEvent evento) {
    	
    	if(pedidosView==null) {
    		
    		pedidosView= new PedidosView();
    		
    	}
    	
    	
    	AppUI.get().content.removeAll();
    	AppUI.get().content.add(pedidosView);

    


    }

    public void onProductosView(DomEvent evento) {
    	
    	if(productosView==null) {
    		
    		productosView= new ProductosView();
    		
    	}
    	
    	
    	AppUI.get().content.removeAll();
    	AppUI.get().content.add(productosView);

    


    }

    public void onUsuariosView(DomEvent evento) {
    	
    	if(usuariosView==null) {
    		
    		usuariosView= new UsuariosView();
    		
    	}
    	
    	
    	AppUI.get().content.removeAll();
    	AppUI.get().content.add(usuariosView);

    


    }


	public void onSeriesView(DomEvent evento) {

		if(seriesView==null) {

			seriesView= new SeriesView();

		}


		AppUI.get().content.removeAll();
		AppUI.get().content.add(seriesView);




	}



	public void onDocumentosPagoView(DomEvent evento) {

		if(documentosPagoView==null) {

			documentosPagoView= new DocumentosPagoView();

		}


		AppUI.get().content.removeAll();
		AppUI.get().content.add(documentosPagoView);




	}
}