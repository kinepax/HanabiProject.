package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.dto.PedidoDTO;
import com.kinesoft.zero.listener.PedidoEvents;
import com.kinesoft.zero.model.*;
import com.kinesoft.zero.servicesImpl.DocumentoPagoDetalleServiceImpl;
import com.kinesoft.zero.servicesImpl.DocumentoPagoServiceImpl;
import com.kinesoft.zero.servicesImpl.PedidoDetalleServiceImpl;
import com.kinesoft.zero.servicesImpl.SerieServiceImpl;
import com.kinesoft.zero.views.cliente.ClientesView;
import com.kinesoft.zero.views.pedido.PedidosView;
import com.kinesoft.zero.views.producto.ProductosView;
import com.vaadin.flow.component.page.Push;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DocumentoPagoView extends DocumentoPagoUI {

    private PedidoEvents pedidoEvents = new PedidoEvents();

    List<ItemVenta> listaDeItemsVenta = new ArrayList<>();


    ItemVenta itemVenta = new ItemVenta();
    Cliente cliente;

    Producto producto;

    Mesa mesa;
    List<Serie> listaDeSeries = new ArrayList<>();
    Integer correlativoSerie;
    List<Cliente> listaDeClientes = new ArrayList<>();

    Pedido pedido ;
    public Integer id_Detalle = 1;
    public boolean save;

    public DocumentoPagoView() {
    }

    public DocumentoPagoView(DocumentoPago documentoPago) {
        this.save = documentoPago == null;
        initData(documentoPago);
    }


    public DocumentoPagoView(PedidoDTO pedidoDTO,Boolean estado,PedidosView pedidosView) {

        pedidoEvents.setEventsListener(pedidosView);

        pedido = new Pedido(pedidoDTO.getId());
        cliente = new Cliente(pedidoDTO.getIdCliente(), pedidoDTO.getCliente());
        txtCliente.setText(cliente.getNombre());

        initData(null);
        btnProducto.setEnabled(false);
        btnCliente.setEnabled(false);
        btnImportarPedido.setEnabled(false);

        agregarDetallePedido(pedido);





    }


    public void initData(DocumentoPago documentoPago) {


        try {
            listaDeSeries = SerieServiceImpl.listarSeries("");
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

        if(cliente != null && !listadeDocumentoPagoDetalle.isEmpty() ){

            System.out.println("Los datos estan correctos");
            String condicionPago= chxPagado.getValue() ? "EFECTIVO" : "CREDITO";
            Boolean cancelado=  chxPagado.getValue() ? true : false;
            BigDecimal montoPagado =  chxPagado.getValue() ? new BigDecimal(txtNetoPagar.getValue().toString()) : new BigDecimal(0.0) ;



            WindowsView.ConfirmarListener view= respuesta->{

                if(respuesta){

                    System.out.println("Se supone que le diste que si");
                    DocumentoPago documentoPagoCab = new DocumentoPago(
                            chboxSerie.getValue(),
                            Integer.parseInt(txfCorrelativo.getValue()),
                            dateFecha.getValue(),
                            condicionPago,
                            cliente,
                            new BigDecimal(txtValorVenta.getValue().toString()),
                            new BigDecimal(18.00),
                            new BigDecimal(txtMontoIgv.getValue().toString()),
                            new BigDecimal(txtNetoPagar.getValue().toString()),
                            "B",
                            cancelado,
                            new Usuario(1,"Paulo","kinepax","123456"),
                            montoPagado,
                            ""


                    );

                    int idDocumentoPagoCab = DocumentoPagoServiceImpl.save(documentoPagoCab);
                    documentoPagoCab.setId(idDocumentoPagoCab);


                    for(DocumentoPagoDetalle item : listadeDocumentoPagoDetalle){

                        DocumentoPagoDetalle docuDetalle = new DocumentoPagoDetalle(
                                documentoPagoCab,
                                item.getProducto(),
                                item.getCantidad(),
                                item.getPrecio(),
                                item.getTotal()
                                                                                   );
                        DocumentoPagoDetalleServiceImpl.guardar(docuDetalle);





                    }


                    WindowsView alerta = new WindowsView();
                    alerta.setHeight("10px");
                    alerta.setWidth("10px");
                    alerta.onError("Se grabo correctamente");
                    pedidoEvents.notifySaveDocumentoPago(pedido.getId());

                    closeDialog();




                }
                else{
                    System.out.println("Se supone que le diste que no");


                }



            };
            WindowsView.onPreguntarGrabar("¿Está seguro de que desea continuar?", view);

        }

        else{



            System.out.println("Algo te falta");
        }





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

                if(!listadeDocumentoPagoDetalle.stream().anyMatch(item->item.getProducto().equals(productoSeleccionado))){
                    agregarDetalleProducto(productoSeleccionado);
                    id_Detalle++;

                }

                view.closeDialog();



            }


        });


    }

    @Override
    public void onSeleccionarPedido() {
        PedidosView pedidosView = new PedidosView();
        pedidosView.showDialog(pedidosView);
        pedidosView.btnOnSeleccionar.addClickListener(e -> {
            if (pedidosView.grid.getValue() != null) {
                PedidoDTO pedidoSeleccionado = pedidosView.grid.getValue();
                Pedido pedido = new Pedido(pedidoSeleccionado.getId());
                agregarDetallePedido(pedido);


/*
                if(!listadeDocumentoPagoDetalle.stream().anyMatch(item->item.getProducto().equals(pedidoSeleccionado))){

                   // agregarDetalleProducto(pedidoSeleccionado);
                 //   id_Detalle++;

                }

                */

                pedidosView.closeDialog();



            }


        });


    }
    public void agregarDetallePedido(Pedido pedido) {


        try {
            List<PedidoDetalle> pedidoDetalleList = PedidoDetalleServiceImpl.listarDetallesDePedido(pedido);
            for(PedidoDetalle pedidoDetalle:pedidoDetalleList){

                DocumentoPagoDetalle nuevoDetalle = new DocumentoPagoDetalle(
                        id_Detalle,
                        pedidoDetalle.getProducto(),
                        pedidoDetalle.getCantidad(),
                        pedidoDetalle.getProducto().getPrecio(),
                        pedidoDetalle.getTotal(),

                        documentoPagoDetalleGridView
                );
                nuevoDetalle.numerText.setEnabled(false);


                dataDelGrid.addItem(nuevoDetalle);

                id_Detalle++;  // Incrementa el id_Detalle después de agregar el detalle
                onTotal();

            }




        } catch (SQLException ex) {

            throw new RuntimeException(ex);
        }



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

        id_Detalle++;  // Incrementa el id_Detalle después de agregar el detalle
        onTotal();

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
        BigDecimal valorVenta = BigDecimal.ZERO;
        BigDecimal IGV = BigDecimal.ZERO;

        BigDecimal valorMontoIGV = BigDecimal.ZERO;

        BigDecimal netoPagar = BigDecimal.ZERO;


        for (DocumentoPagoDetalle item : listadeDocumentoPagoDetalle) {


            if (item.total != null) {
                netoPagar = netoPagar.add(item.total);

                System.out.println("El total n° " + netoPagar);

            }





            //this.grid.getDataProvider().refreshItem(item);

        }


        System.out.println("El valor total de el dinero es " + netoPagar);
        //this.grid.getDataProvider().refreshAll();
        columnaTotales.setFooter("" + netoPagar);
        dataDelGrid.refreshAll();

      //  documentoPagoDetalleGridView.getDataProvider().refreshAll();





        txtNetoPagar.setValue(""+netoPagar);

        valorVenta=netoPagar.divide(new BigDecimal(1.18),2);
        txtValorVenta.setValue(""+valorVenta);

        valorMontoIGV=netoPagar.subtract(valorVenta);
        txtMontoIgv.setValue(""+valorMontoIGV);





        return "" + netoPagar;

        //return "hola";


    }


}