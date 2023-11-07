package com.kinesoft.zero.views.pedido;

import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.model.PedidoDetalle;
import com.kinesoft.zero.servicesImpl.PedidoDetalleServiceImpl;
import com.kinesoft.zero.servicesImpl.PedidoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosView extends PedidosUI {

	List<Pedido> listaDePedidos = new ArrayList<>();
	Pedido pedido = new Pedido();

	List<Pedido>listaDePedidoSeleccionado ;

	public PedidosView() {
		initDataView();

	}


	public void initDataView() {
		onRefrescar();


	}


	@Override
	public void onadd() {
		PedidoView vistaPedido = new PedidoView(null);
		vistaPedido.setMinWidth("500px");
		vistaPedido.showDialog(vistaPedido);

	}

	@Override
	public void onRefrescar() {

		try {
			listaDePedidos = PedidoServiceImpl.listarPedidos();
			grid.setItems(listaDePedidos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@Override
	public void onEditar() {

		pedido=grid.getValue();

		if(pedido!=null) {
			PedidoView vistaPedido = new PedidoView(pedido);
			vistaPedido.setSizeFull();
			vistaPedido.showDialog(vistaPedido);
		}
	}

	@Override
	public void onEliminar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onImprimirPedido() {
		pedido=grid.getValue();

		if(pedido!=null) {

			try {
				List<PedidoDetalle>  pedidoDetalle = PedidoDetalleServiceImpl.listarDetallesDePedido(pedido);
				for(PedidoDetalle pedDetalle : pedidoDetalle) {

					System.out.println(pedDetalle.producto.getNombre());
					System.out.println(pedDetalle.getPrecio().toString());

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void onCheckSeleccion() {


		System.out.println("hola soy el check me cliquearon");
		System.out.println(grid.getValue().getCliente().toString());
		//listaDePedidoSeleccionado.add(grid.getValue());


	}

}
