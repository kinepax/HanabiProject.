package com.kinesoft.zero.views.pedido;

import java.math.BigDecimal;

import com.vaadin.flow.component.checkbox.Checkbox;
import org.springframework.web.servlet.View;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.LabelView;
import com.kinesoft.zero.components.NumerText;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.ItemVenta;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.views.cliente.ClienteView;
import com.kinesoft.zero.views.cliente.ClientesView;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public abstract class PedidoUI extends WindowsView {

	public ComboBox<Cliente> comboCliente 	= new ComboBox<>();
	GridView<ItemVenta> gridItemVenta 		= new GridView<>(ItemVenta.class);
	Column<ItemVenta> columnaTotales;
	TextField txtCliente 					= new TextField("Cliente");
	Button btnCliente						= new Button("Cliente");
	Button btnGrabar 						= new Button("Grabar");
	Button btnCerrar 						= new Button("Cerrar");
	ComboBox<Mesa> comboMesas				= new ComboBox<>("Mesas");
	HorizontalLayout pnlObciones 			= new HorizontalLayout();
	private VerticalLayout body 			= new VerticalLayout();
	BigDecimal precio 						= new BigDecimal(0.0);




	public PedidoUI() {
		initDataUI();
		initStyles();
		intiListener();
	}

	public void initDataUI() {

		pnlObciones.add(
						btnCliente,
						txtCliente,
						comboMesas,
						btnGrabar,
						btnCerrar
						);


		gridItemVenta.addCol				(ItemVenta::getstrNomProducto, "Producto");
		gridItemVenta.addCol				(ItemVenta::getBigDecimalPrecio, "Precio");			
		gridItemVenta.addComponentColumn	(ItemVenta->
		
											{onTotal();
											
											
											return ItemVenta.getNumerText();
											
											}
											).setHeader("Cantidad");
		
		
		columnaTotales=
		gridItemVenta.addComponentColumn
		(ItemVenta->{
			LabelView lbl	= new LabelView();
			lbl.setText(ItemVenta.getTotal().toString());													
			return lbl;	
					}
		
				
		).setHeader("Total").setFooter("0");




		body.add(gridItemVenta);
		add(pnlObciones, body);
	

	}

	public void intiListener() {
		btnCerrar.addClickListener(e->{
			this.closeDialog();
			
			
		});
		btnGrabar.addClickListener(e -> onSave());
		btnCliente.addClickListener(e->{onSeleccionCliente();});
		comboMesas.addValueChangeListener(e->onCambiarMesa());
	
	};


	public void initStyles() {
		this.setWidthFull();

		body.setWidthFull();

		comboCliente.setWidthFull();
		pnlObciones.setWidthFull();
		gridItemVenta.setWidthFull();

	};


	public abstract String onCerrar();

	public abstract String onSave();

	
	public abstract String onTotal() ;
	
	public abstract String onRecorrer();

	
	public abstract void onSeleccionCliente();
	
	public abstract void onCambiarMesa();
	
	
	public abstract void onCargarPedido(Pedido pedido);

	



}