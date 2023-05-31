package com.kinesoft.zero.views.documentoPago;

import com.kinesoft.zero.model.DocumentoPago;
import com.kinesoft.zero.model.PedidoDetalle;
import com.kinesoft.zero.servicesImpl.DocumentoPagoServiceImpl;
import com.kinesoft.zero.servicesImpl.PedidoDetalleServiceImpl;
import com.kinesoft.zero.views.pedido.PedidoView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentosPagoView extends DocumentosPagoUI {

	List<DocumentoPago> listadeDocumentosPago = new ArrayList<>();
	DocumentoPago documento;
	public DocumentosPagoView() {
		initDataView();

	}

	public void initDataView() {
		onRefrescar();

	}

	

	@Override
	public void onadd() {

		DocumentoPagoView vistaDocumentoDePago 	= new DocumentoPagoView(null);
		vistaDocumentoDePago.setMinWidth("500px");
		vistaDocumentoDePago.showDialog(vistaDocumentoDePago);

	}

	@Override
	public void onRefrescar() {

		try {
			listadeDocumentosPago = DocumentoPagoServiceImpl.listarDocumentosPago();
			grid.setItems(listadeDocumentosPago);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@Override
	public void onEditar() {

		/*
		
		documento =grid.getValue();
		
		if(documento !=null) {
		PedidoView vistaPedido = new PedidoView(documento);
		vistaPedido.setSizeFull();
		vistaPedido.showDialog(vistaPedido);
		}


		 */
	}

	@Override
	public void onEliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onImprimirPedido() {

		/*
		documento =grid.getValue();
		
		if(documento !=null) {
			
			try {
				List<PedidoDetalle>  pedidoDetalle = PedidoDetalleServiceImpl.listarDetallesDePedido(documento);
				for(PedidoDetalle pedDetalle : pedidoDetalle) {
					
					System.out.println(pedDetalle.producto.getNombre());
					System.out.println(pedDetalle.getPrecio().toString());

				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		*/
	}

}
