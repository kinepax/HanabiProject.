package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.LabelView;
import com.kinesoft.zero.components.NumerText;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.DocumentoPagoDetalle;
import com.kinesoft.zero.model.Serie;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class DocumentoPagoUI extends WindowsView {


	HorizontalLayout pnlFinal = new HorizontalLayout();

	VerticalLayout pnlColumna1 = new VerticalLayout();
	VerticalLayout pnlColumna2 = new VerticalLayout();


	HorizontalLayout pnlObciones = new HorizontalLayout();
	HorizontalLayout pnlInteraccion = new HorizontalLayout();
	HorizontalLayout pnlSeleccionProductos = new HorizontalLayout();

	LabelView txtCliente = new LabelView();
	Button btnCliente = new Button("Cliente");
	ComboBox <Serie> chboxSerie = new ComboBox<Serie>();
	TextField txfCorrelativo= new TextField();
	List<DocumentoPagoDetalle> listadeDocumentoPagoDetalle= new ArrayList<>();


	DatePicker dateFecha = new DatePicker("Fecha");

	Checkbox chxPagado = new Checkbox("Pagado");


	Button btnProducto = new Button("Productos");


	Button btnImportarPedido = new Button("Importar pedido");


	private HorizontalLayout body = new HorizontalLayout();

	private VerticalLayout pnlFacturado = new VerticalLayout();


	TextField txtValorVenta = new TextField("Valor de venta");
	TextField txtIgv = new TextField("Igv");
	TextField txtMontoIgv = new TextField("Monto Igv");
	TextField txtNetoPagar = new TextField("Neto Pagar");


	Button btnGrabar = new Button("Grabar");
	Button btnCerrar = new Button("Cerrar");


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
				txtCliente,
				chboxSerie,
				txfCorrelativo

		);

		pnlInteraccion.add(
				dateFecha,
				chxPagado

		);

		pnlSeleccionProductos.add(
				btnProducto,
				btnImportarPedido
		);

		pnlFacturado.add(
				txtValorVenta,
				txtIgv,
				txtMontoIgv,
				txtNetoPagar,
				btnGrabar
		);


		body.add(
				documentoPagoDetalleGridView
		);

		pnlColumna1.add(
				pnlObciones,
				pnlInteraccion,
				pnlSeleccionProductos,
				body

		);

		pnlColumna2.add(
				btnCerrar,
				pnlFacturado

		);


		pnlFinal.add(
				pnlColumna1,
				pnlColumna2

				);

		add(pnlFinal);


		documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getProductotoSring,"Producto");
		documentoPagoDetalleGridView.addComponentColumn(detalle -> {
			NumerText numerText = detalle.getNumerText();
			numerText.addValueChangeListener(e->{
				if(numerText.getValue()==0){
					listadeDocumentoPagoDetalle.remove(detalle);
					dataDelGrid.refreshAll();
				}
				onTotal();
			});
			return numerText;
		}).setHeader("Cantidad");

		documentoPagoDetalleGridView.addCol(DocumentoPagoDetalle::getPrecio,"Precio");
		columnaTotales=documentoPagoDetalleGridView.addComponentColumn(DocumentoPagoDetalle->{
			LabelView lbl= new LabelView();
			lbl.setText(DocumentoPagoDetalle.getTotal().toString());
			return lbl;
		}).setHeader("Total").setFooter("0");


		dateFecha.setValue(LocalDate.now());
		txtIgv.setValue("18%");
		txtValorVenta.setReadOnly(true);
		txtIgv.setReadOnly(true);
		txtMontoIgv.setReadOnly(true);
		txtNetoPagar.setReadOnly(true);
		chxPagado.setValue(true);
	}

	public void intiListener() {
		btnCerrar.addClickListener(e->{
			this.closeDialog();
		});
		btnGrabar.addClickListener(e -> onSave());
		btnCliente.addClickListener(e->{onSeleccionCliente();});
		btnProducto.addClickListener(e->{onSeleccionaProducto();});
		chboxSerie.addValueChangeListener(e->{onCambiarSerie();});

	};


	public void initStyles() {
		this.setWidthFull();
		txfCorrelativo.setEnabled(false);

		body.setWidthFull();
		dateFecha.setPlaceholder("Fecha de Emisión");
		txtCliente.setText("-");
		chboxSerie.setWidth("90px");
		txfCorrelativo.setWidth("70px");
		pnlObciones.setWidthFull();
		documentoPagoDetalleGridView.setMinWidth("350px");

		documentoPagoDetalleGridView.setMaxWidth("360px");

		documentoPagoDetalleGridView.setMinHeight("200px");
		documentoPagoDetalleGridView.setMaxHeight("250px");

		pnlInteraccion.setAlignItems(Alignment.BASELINE);
		pnlObciones.setAlignItems(Alignment.BASELINE);
		pnlFacturado.setAlignItems(Alignment.END);

		pnlColumna2.setAlignItems(Alignment.END);


	};


	public abstract String onCerrar();

	public abstract String onSave();


	public abstract void onSeleccionCliente();

	public abstract void onSeleccionaProducto();

	public abstract void onCambiarSerie();

	public abstract String onTotal() ;





}