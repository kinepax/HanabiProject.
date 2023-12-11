package com.kinesoft.zero.views.pedido;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.dto.PedidoDTO;
import com.kinesoft.zero.listener.EventsPedidoListener;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.servicesImpl.PedidoServiceImpl;
import com.kinesoft.zero.views.documentoPago.DocumentoPagoView;
import com.vaadin.flow.component.button.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosView extends PedidosUI implements EventsPedidoListener {

	List<PedidoDTO> listaDePedidos = new ArrayList<>();
	List<Mesa> listaDeMesas = new ArrayList<>();

	Pedido pedido;


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
		vistaPedido.setPedidosView(this);
		vistaPedido.setMinWidth("500px");
		vistaPedido.showDialog(vistaPedido);

	}

	@Override
	public void onRefrescar() {

		try {
			String fechaInicialStr= fechaInicial.getValue().toString();
			String fechaFinalStr= fechaFinal.getValue().toString();
			Integer idMesa= chxboxMesa.getValue().getId();
			String cliente = txtCliente.getValue().toString();
			String estado= chboxEstado.getValue();



			//listaDePedidos = PedidoServiceImpl.listarPedidos();
			listaDePedidos = PedidoServiceImpl.listarPedidos(
					fechaInicialStr,fechaFinalStr,
					idMesa,cliente,estado
					);

			grid.setItems(listaDePedidos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@Override
	public void onEditar() {
		int idPedido= grid.getValue().getId();
		int idCliente= grid.getValue().getIdCliente();
		int idMesa= grid.getValue().getIdMesa();

		pedido=new Pedido(idPedido,idCliente,idMesa);

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
		/*
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

		 */

	}

	@Override
	public Pedido onSeleccionPedido() {
		/*
		if(grid.getValue()!=null){
			return grid.getValue();
		}
		return null;

		 */
		return null;

	}

	@Override
	public void onCheckSeleccion() {


		System.out.println("hola soy el check me cliquearon");
		System.out.println(grid.getValue().getCliente().toString());
		//listaDePedidoSeleccionado.add(grid.getValue());


	}

	@Override
	public Button onOperacion(PedidoDTO pedidoDTO) {
		String estadoButton="";
		Button btnEstado ;

		switch (pedidoDTO.getEstado()){
			case "PENDIENTE":
				estadoButton="ATENDER";
				btnEstado=new Button(estadoButton);

				btnEstado.addClickListener(e->{
					System.out.println("AHORA VAMOS A ATENDER EL PEDIDO "+pedidoDTO.getId()+" el nombre del cliente "+pedidoDTO.getCliente().toString());
					WindowsView.ConfirmarListener view= respuesta -> {

						if (respuesta) {

							PedidoServiceImpl.actualizarEstado("ATENDIDO",pedidoDTO.getId());
							onRefrescar();

						}

						else {
							// El usuario seleccionó "No"
							// No hacer nada
						}

					};
					WindowsView.onPreguntarGrabar("¿Está seguro de que desea continuar?", view);

				});
				break;

			case "ATENDIDO":
				estadoButton="FACTURAR";
				btnEstado=new Button(estadoButton);
				btnEstado.addClickListener(e->{
					System.out.println("AHORA VAMOS A FACTURAR EL PEDIDO "+pedidoDTO.getId()+" el nombre del cliente "+pedidoDTO.getCliente().toString());
					DocumentoPagoView documentoPagoView = new DocumentoPagoView(pedidoDTO,true,this);
					documentoPagoView.showDialog(documentoPagoView);




					documentoPagoView.addDetachListener(detachEvent -> {

						//	PedidoServiceImpl.actualizarEstado("FACTURADO",pedidoDTO.getId());

						onRefrescar();
					});


				});
				break;


			case "FACTURADO":
				estadoButton="FACTURADO";
				btnEstado=new Button(estadoButton);
				btnEstado.setEnabled(false);
				break;

			default:
				estadoButton="VALOR INVALIDO";
				btnEstado=new Button(estadoButton);
				btnEstado.setEnabled(false);
				break;


		}






		return btnEstado;

	}


	@Override
	public void notifySaveDocumentoPago(int idDocumentoPagoCab) {
		System.out.println("LLLEGASTE AL SAVE DEL PEDIDOS VIEW el id "+idDocumentoPagoCab);

		PedidoServiceImpl.actualizarEstado("FACTURADO",idDocumentoPagoCab);

	}

	@Override
	public void notifySavePedido() {
		onRefrescar();
	}


}
