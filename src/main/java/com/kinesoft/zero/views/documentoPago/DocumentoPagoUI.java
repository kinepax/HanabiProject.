package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.LabelView;
import com.kinesoft.zero.components.NumerText;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.data.SelectListDataView;
import com.vaadin.flow.component.textfield.TextField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class DocumentoPagoUI extends WindowsView {

	HorizontalLayout pnlObciones 			= new HorizontalLayout();
	HorizontalLayout pnlInteraccion 		= new HorizontalLayout();

	LabelView txtCliente 					= new LabelView();
	Button btnCliente						= new Button("Cliente");

	ComboBox <Serie> chboxSerie = new ComboBox<Serie>();
	TextField txfCorrelativo= new TextField();


	List<DocumentoPagoDetalle> listadeDocumentoPagoDetalle= new ArrayList<>();



	DatePicker dateFecha					= new DatePicker();

	Checkbox chxPagado						= new Checkbox("Pagado");


	Button btnProducto 						= new Button("Productos");


	Button btnImportarPedido				= new Button("Importar pedido");


	private HorizontalLayout body 			= new HorizontalLayout();

	private VerticalLayout pnlFacturado		= new VerticalLayout();

	TextField txtValorVenta = new TextField("Valor de venta");
	TextField txtIgv = new TextField("Igv");
	TextField txtMontoIgv = new TextField("Monto Igv");
	TextField txtNetoPagar = new TextField("Neto Pagar");








	Button btnGrabar 						= new Button("Grabar");
	Button btnCerrar 						= new Button("Cerrar");



	GridView<DocumentoPagoDetalle>documentoPagoDetalleGridView = new GridView<>(DocumentoPagoDetalle.class);
	GridListDataView<DocumentoPagoDetalle> dataDelGrid = documentoPagoDetalleGridView.setItems(listadeDocumentoPagoDetalle);

	Column<DocumentoPagoDetalle> columnaTotales;


	public DocumentoPagoUI() {
		initDataUI();
		initStyles();
		intiListener();
	}

	public void initDataUI() {




		pnlObciones.add(
						btnCliente,
						txtCliente,chboxSerie,txfCorrelativo,
						dateFecha,
						chxPagado,btnCerrar

						);


		pnlInteraccion.add(btnProducto,btnImportarPedido);
		pnlFacturado.add(txtValorVenta,txtIgv,txtMontoIgv,txtNetoPagar);



		documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getProductotoSring,"Producto");
     //   documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getCantidad,"Cantidad");

/*
		documentoPagoDetalleGridView.addComponentColumn(DocumentoPagoDetalle->{
			onTotal();
			return DocumentoPagoDetalle.getNumerText();



		}).setHeader("Cantidad");


*/
        documentoPagoDetalleGridView.addComponentColumn(detalle -> {
            NumerText numerText = detalle.getNumerText();

            numerText.addValueChangeListener(event -> {
                int cantidad = (int) Math.round(numerText.getValue());
                detalle.setCantidad(cantidad);
                detalle.setTotal(detalle.getPrecio().multiply(new BigDecimal(cantidad)));

                onTotal(); // Actualizar el total al cambiar la cantidad

            });

            return numerText;
        }).setHeader("Cantidad");








		documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getPrecio,"Precio");
		//documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getTotal,"Total");

		columnaTotales=documentoPagoDetalleGridView.addComponentColumn(DocumentoPagoDetalle->{
			LabelView lbl= new LabelView();
			lbl.setText(DocumentoPagoDetalle.getTotal().toString());
			return lbl;
		})	.setHeader("Total").setFooter("0");




		dateFecha.setValue(LocalDate.now());



		body.add(documentoPagoDetalleGridView,pnlFacturado);

		add(pnlObciones,pnlInteraccion, body/*,select,addNewItem,newItemField,itemCountSpan*/ );


	}

	public void intiListener() {
		btnCerrar.addClickListener(e->{
			this.closeDialog();
			
			
		});
		btnGrabar.addClickListener(e -> onSave());
		btnCliente.addClickListener(e->{onSeleccionCliente();});
		btnProducto.addClickListener(e->{onSeleccionaProducto();});
		chboxSerie.addValueChangeListener(e->{onCambiarSerie();});


		dataDelGrid.addItemCountChangeListener(e ->
				Notification.show(" " + e.getItemCount() + " items available"));

	};


	public void initStyles() {
		this.setWidthFull();
		txfCorrelativo.setEnabled(false);
		body.setWidthFull();
		dateFecha.setPlaceholder("Fecha de Emisión");
		txtCliente.setText("-");
		pnlObciones.setWidthFull();
		documentoPagoDetalleGridView.setWidthFull();

	};


	public abstract String onCerrar();

	public abstract String onSave();




	
	public abstract void onSeleccionCliente();

	public abstract void onSeleccionaProducto();

	public abstract void onCambiarSerie();

	public abstract String onTotal() ;



}