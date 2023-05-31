package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.DocumentoPago;
import com.kinesoft.zero.model.Pedido;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class DocumentosPagoUI extends WindowsView {
	GridView<DocumentoPago> grid 			= new GridView<>(DocumentoPago.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	Button btnImprimirPedido 		= new Button("Imprimir Pedido");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();


	public DocumentosPagoUI() {
		initData();
		initListener();
	}

	public void initData() {
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar, btnRefrescar,btnImprimirPedido);





		grid.setColumns("id", "serie", "numero", "fecha","condicion_pago","cliente","neto_pagar","monto_pagado","usuario","firma_electronica");
		add(pnlObciones, grid);

	}



	public void initListener() {

		btnAgregar.addClickListener				(e -> 	onadd());
		btnRefrescar.addClickListener			(e -> 	onRefrescar());
		btnEditar.addClickListener				(e -> 	onEditar());
		btnEliminar.addClickListener			(e -> 	onEliminar());
		btnImprimirPedido.addClickListener		(e -> 	onImprimirPedido());

	}

	public abstract void onadd();
	public abstract void onRefrescar();
	public abstract void onEditar();
	public abstract void onEliminar();
	public abstract void onImprimirPedido();

}
