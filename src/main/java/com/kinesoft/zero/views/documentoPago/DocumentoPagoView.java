package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.model.*;
import com.kinesoft.zero.servicesImpl.*;
import com.kinesoft.zero.views.cliente.ClientesView;
import com.kinesoft.zero.views.producto.ProductosView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DocumentoPagoView extends DocumentoPagoUI {


	List<ItemVenta> listaDeItemsVenta = new ArrayList<>();


	ItemVenta itemVenta = new ItemVenta();
	Cliente cliente;

	Producto producto;

	Mesa mesa;
	List<Serie> listaDeSeries = new ArrayList<>();
	Integer correlativoSerie;
	List<Cliente>listaDeClientes = new ArrayList<>();

	public Integer id_Detalle=1;
	public boolean save;

	public DocumentoPagoView() {
	}

	public DocumentoPagoView(DocumentoPago documentoPago) {
		this.save = documentoPago == null;
		initData(documentoPago);
	}


	public void initData(DocumentoPago documentoPago) {

		try {
			listaDeSeries=SerieServiceImpl.listarSeries(null);
			chboxSerie.setItems(listaDeSeries);
			chboxSerie.setValue(listaDeSeries.get(0));
			onCambiarSerie();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}


	@Override
	public String onCerrar() {
		this.closeDialog();
		return null;
	}

	@Override
	public String onSave() {
		return null;
	}


	@Override
	public void onSeleccionCliente() {
		ClientesView view= new ClientesView();

		//	vistaSeleccion.setMinWidth("500px");
		view.setSizeFull();

		view.showDialog(view);


		view.btnSeleccionar.addClickListener(e->{
			if(view.grid.getValue()!=null) {
				txtCliente.setText(view.grid.getValue().getNombre());
				cliente= view.grid.getValue();
				view.closeDialog();
			}


		});

	}

	@Override
	public void onSeleccionaProducto() {
	//	dataDelGrid.refreshAll();

		ProductosView view= new ProductosView();

		//	vistaSeleccion.setMinWidth("500px");
		view.setSizeFull();

		view.showDialog(view);


		view.btnSeleccionar.addClickListener(e->{
			if(view.grid.getValue()!=null) {
				Producto productoSeleccionado = view.grid.getValue();
				System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());

				DocumentoPagoDetalle nuevoDetalle = new DocumentoPagoDetalle(id_Detalle,
						productoSeleccionado,1, productoSeleccionado.getPrecio(), productoSeleccionado.getPrecio());




				System.out.println("Producto en el detalle documento : " + nuevoDetalle.getProducto().getNombre());



				listadeDocumentoPagoDetalle.add(nuevoDetalle);


				System.out.println("Nombres de los productos en la lista:");
				for (DocumentoPagoDetalle detalle : listadeDocumentoPagoDetalle) {
					System.out.println(detalle.getProducto().getNombre());
				}
				//dataDelGrid.refreshAll();

				System.out.println("Antes de refreshAll(): " + listadeDocumentoPagoDetalle.size());
				dataDelGrid.refreshAll();
				System.out.println("Después de refreshAll(): " + listadeDocumentoPagoDetalle.size());









				//producto= view.grid.getValue();

				view.closeDialog();
				id_Detalle++;
			}


		});


	}

	@Override
	public void onCambiarSerie() {
		try {
			correlativoSerie=DocumentoPagoServiceImpl.devolverCorrelativo(chboxSerie.getValue())+1;
			System.out.println("El correlativo que toca es "+correlativoSerie);
			txfCorrelativo.setValue(correlativoSerie.toString());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public String onTotal() {

		BigDecimal totalDinero=BigDecimal.ZERO;
		for(DocumentoPagoDetalle item : listadeDocumentoPagoDetalle){


			if (item.total!=null) {
				totalDinero=totalDinero.add(item.total);
				System.out.println("El total n° "+totalDinero);

			}



			//	this.grid.getDataProvider().refreshItem(item);

		}






		System.out.println("El valor total de el dinero es "+totalDinero);
		//this.grid.getDataProvider().refreshAll();
		columnaTotales.setFooter(""+totalDinero);
		dataDelGrid.refreshAll();

		return ""+totalDinero;

		//return "hola";



	}






}