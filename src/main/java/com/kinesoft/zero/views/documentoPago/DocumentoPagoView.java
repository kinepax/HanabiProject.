package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.NumerText;
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
    List<Cliente> listaDeClientes = new ArrayList<>();

    public Integer id_Detalle = 1;
    public boolean save;

    public DocumentoPagoView() {
    }

    public DocumentoPagoView(DocumentoPago documentoPago) {
        this.save = documentoPago == null;
        initData(documentoPago);
    }


    public void initData(DocumentoPago documentoPago) {

        try {
            listaDeSeries = SerieServiceImpl.listarSeries(null);
            chboxSerie.setItems(listaDeSeries);
            chboxSerie.setValue(listaDeSeries.get(0));
            onCambiarSerie();
            documentoPagoDetalleGridView.setlist(listadeDocumentoPagoDetalle);

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
        ClientesView view = new ClientesView();

		//	vistaSeleccion.setMinWidth("500px");
        view.setSizeFull();

        view.showDialog(view);


        view.btnSeleccionar.addClickListener(e -> {
            if (view.grid.getValue() != null) {
                txtCliente.setText(view.grid.getValue().getNombre());
                cliente = view.grid.getValue();
                view.closeDialog();
            }


        });

    }

    @Override
    public void onSeleccionaProducto() {
		//	dataDelGrid.refreshAll();

        ProductosView view = new ProductosView();

		//	vistaSeleccion.setMinWidth("500px");
        view.setSizeFull();

        view.showDialog(view);


        view.btnSeleccionar.addClickListener(e -> {
            if (view.grid.getValue() != null) {
                Producto productoSeleccionado = view.grid.getValue();

                agregarDetalleProducto(productoSeleccionado);

                view.closeDialog();
                id_Detalle++;
            }


        });


    }


    public void agregarDetalleProducto(Producto productoSeleccionado) {
        System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());
		DocumentoPagoDetalle nuevoDetalle = new DocumentoPagoDetalle(
				id_Detalle,
				productoSeleccionado,
				1,
				productoSeleccionado.getPrecio(),
				productoSeleccionado.getPrecio(),
				documentoPagoDetalleGridView
		);


        dataDelGrid.addItem(nuevoDetalle);
    //    nuevoDetalle.setNumerText(new NumerText());
      //  nuevoDetalle.getNumerText().setValue(1.0);

	//	documentoPagoDetalleGridView.getDataProvider().refreshAll();
		id_Detalle++;  // Incrementa el id_Detalle después de agregar el detalle


    }

    @Override
    public void onCambiarSerie() {
        try {
            correlativoSerie = DocumentoPagoServiceImpl.devolverCorrelativo(chboxSerie.getValue()) + 1;
            System.out.println("El correlativo que toca es " + correlativoSerie);
            txfCorrelativo.setValue(correlativoSerie.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String onTotal() {

        BigDecimal totalDinero = BigDecimal.ZERO;
        for (DocumentoPagoDetalle item : listadeDocumentoPagoDetalle) {


            if (item.total != null) {
                totalDinero = totalDinero.add(item.total);

                System.out.println("El total n° " + totalDinero);

            }


            //this.grid.getDataProvider().refreshItem(item);

        }


        System.out.println("El valor total de el dinero es " + totalDinero);
        //this.grid.getDataProvider().refreshAll();
        columnaTotales.setFooter("" + totalDinero);
        //dataDelGrid.refreshAll();

        documentoPagoDetalleGridView.getDataProvider().refreshAll();


        return "" + totalDinero;

        //return "hola";


    }


}