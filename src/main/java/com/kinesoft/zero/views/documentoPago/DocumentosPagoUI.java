package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.DocumentoPago;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

public abstract class DocumentosPagoUI extends WindowsView {
	GridView<DocumentoPago> grid 			= new GridView<>(DocumentoPago.class);
	Button btnAgregar 				= new Button("Agregar");
	Button btnEditar 				= new Button("Editar");
	Button btnEliminar 				= new Button("Eliminar");
	Button btnRefrescar 			= new Button("Refrescar");
	Button btnImprimirPedido 		= new Button("Imprimir Pedido");
	HorizontalLayout pnlObciones 	= new HorizontalLayout();

	HorizontalLayout pnlFiltros = new HorizontalLayout();

	DatePicker fechaInicial = new DatePicker("Fecha Inicial");
	DatePicker fechaFinal = new DatePicker("Fecha Final");
	TextField txtCliente = new TextField("Cliente");
	ComboBox<String> chbxCondicion = new ComboBox<>("Condicion de Pago");




	public DocumentosPagoUI() {
		initStyles();
		initData();
		initListener();
	}
	public void initStyles(){

		pnlFiltros.setAlignItems(Alignment.BASELINE);
	}

	public void initData() {
		chbxCondicion.setItems("EFECTIVO","CREDITO","TODOS");
		chbxCondicion.setValue("TODOS");
		fechaInicial.setValue(LocalDate.now());
		fechaFinal.setValue(LocalDate.now());
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar,btnImprimirPedido);

		pnlFiltros.add(fechaInicial,fechaFinal,chbxCondicion,txtCliente,btnRefrescar);




		grid.setColumns("id", "serie", "numero", "fecha","condicion_pago","cliente","neto_pagar","monto_pagado","usuario","firma_electronica");
		add(pnlObciones,pnlFiltros, grid);

	}



	public void initListener() {

		btnAgregar.addClickListener				(e -> 	onadd());
		btnRefrescar.addClickListener			(e -> 	onRefrescar());
		btnEditar.addClickListener				(e -> 	onEditar());
		btnEliminar.addClickListener			(e -> 	onEliminar());
		btnImprimirPedido.addClickListener		(e -> 	onImprimirPedido());
		fechaInicial.addValueChangeListener(e -> onRefrescar());
		fechaFinal.addValueChangeListener(e -> onRefrescar());
		chbxCondicion.addValueChangeListener(e->onRefrescar());
		txtCliente.addKeyUpListener(Key.ENTER,e->onRefrescar());



	}

	public abstract void onadd();
	public abstract void onRefrescar();
	public abstract void onEditar();
	public abstract void onEliminar();
	public abstract void onImprimirPedido();

}
