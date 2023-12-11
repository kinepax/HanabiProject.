package com.kinesoft.zero.views.pedido;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.listener.PedidoEvents;
import com.kinesoft.zero.model.*;
import com.kinesoft.zero.servicesImpl.*;
import com.kinesoft.zero.views.cliente.ClientesView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PedidoView extends PedidoUI {

	PedidosView pedidosView;
	List<ItemVenta> listaDeItemsVenta 						= new ArrayList<>();
	ItemVenta itemVenta 							= new ItemVenta();
	Cliente cliente;
	Mesa mesa;
	List<Mesa> listaDeMesas 						= new ArrayList<>();
	List<Cliente>listaDeClientes					= new ArrayList<>();


	private PedidoEvents pedidoEvents = new PedidoEvents();

	public boolean save;

	public PedidoView() {		
	}

	public PedidoView(Pedido pedido) 
	{
		this.save = pedido == null;
		initData(pedido);
	}

	public void setPedidosView(PedidosView pedidosView){
		this.pedidosView=pedidosView;
		setPedidoViewListener(this.pedidosView);

	}
	public void  setPedidoViewListener(PedidosView pedidosView){
		pedidoEvents.setEventsListener(pedidosView);
	}

	 

	public void initData(Pedido pedido) {
		
		comboMesas.setPlaceholder("Seleccione una mesa");


		
		//Pedido nuevo aqui
		
		if (save) {
			try {
				cliente = ClienteServiceImpl.listarClientesId(1).get(0);
				txtCliente.setValue(cliente.getNombre());

				listaDeItemsVenta = ItemVentaServiceImpl.listaDeItemsEnVenta();
				listaDeMesas= MesaServiceImpl.listarMesas(null);
				

				for (ItemVenta item : listaDeItemsVenta) {
					item.grid = this.gridItemVenta;
					System.out.println("elvalor del item es "+item.getstrNomProducto());
					System.out.println("estas poniendo todo el grid en un item");
				}
				gridItemVenta.setlist(listaDeItemsVenta);


				
				comboMesas.setItems(listaDeMesas);

				return ;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}
		else {

			//Ver pedido ya hecho

			onCargarPedido(pedido);

		}

	}

	
	
	@Override
	public void onCargarPedido(Pedido pedido) {
		try {
			int idMesa=pedido.getIdMesa();
			int idCliente= pedido.getCliente().getId();
			System.out.println("el  id del pedido es "+idCliente);

			listaDeMesas= MesaServiceImpl.listaMesaId(idMesa);
			cliente=ClienteServiceImpl.listarClientesId(idCliente).get(0);
			
			txtCliente.setValue(cliente.getNombre());
			txtCliente.setEnabled(false);
			
			comboMesas.setItems(listaDeMesas);
			comboMesas.setValue(listaDeMesas.get(0));
			comboMesas.setEnabled(false);
			
			
			btnCliente.setEnabled(false);
			btnGrabar.setEnabled(false);

	
			listaDeItemsVenta = ItemVentaServiceImpl.listarItemsPedido(pedido);
			gridItemVenta.setlist(listaDeItemsVenta);

		//	grid.setlist(listaDeItemsVenta);
			for (ItemVenta item : listaDeItemsVenta) {
				item.getNumerText().setEnabled(false);
				item.grid = this.gridItemVenta;
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return ;
		
	}
	
	
	
	@Override
	public String onCerrar() {
		this.closeDialog();
		return null;
	}

	@Override
	public String onSave() {

		
		
		if(!cliente.equals(null) && comboMesas.getValue()!=null && Double.valueOf(onTotal())>0.0) {

			mesa=comboMesas.getValue();

			WindowsView.ConfirmarListener view= respuesta -> {

				   if (respuesta) {

						Pedido pedidoCab= new Pedido(cliente,mesa,new BigDecimal(onTotal()) , "PENDIENTE");
						int id_pedido_cab=PedidoServiceImpl.save(pedidoCab);
						pedidoCab.setId(id_pedido_cab);
						List <PedidoDetalle> pedidosDetalle= new ArrayList<PedidoDetalle>();

						System.out.println("---------------------");
						System.out.println(pedidoCab.getId());
						System.out.println(pedidoCab.getCliente().getNombre());
						System.out.println(pedidoCab.getTotal());
						System.out.println(pedidoCab.getEstado());
						System.out.println("---------------------");



						for(ItemVenta item : gridItemVenta.getList()){


							if (item.cantidad>0) {

								System.out.println("---------------------");

								System.out.println(item.getProducto().getNombre());
								System.out.println(item.getCantidad());
								System.out.println(item.getPrecio());
								System.out.println(item.getTotal());
								System.out.println("---------------------");
								PedidoDetalle pedidoDetalle = new PedidoDetalle(pedidoCab,item.getProducto(),item.getCantidad(),
										item.getPrecio(),item.getTotal()
										);

								pedidosDetalle.add(pedidoDetalle);
								PedidoDetalleServiceImpl.guardar(pedidoDetalle);
								System.out.println("Se grabo correctamente");





								WindowsView alerta = new WindowsView();
								alerta.setHeight("10px");
								alerta.setWidth("10px");
								alerta.onError("Se grabo correctamente");

								pedidoEvents.notifySavePedido();


								closeDialog();


							}
						//	this.grid.getDataProvider().refreshItem(item);

						}				        }




				   else {
						// El usuario seleccionó "No"
						// No hacer nada
					}

			};

	WindowsView.onPreguntarGrabar("¿Está seguro de que desea continuar?", view);
					

			//	vistaSeleccion.setMinWidth("500px");
			
		

			
			
		
	
		
		
	
		}
		
		else {
				
			WindowsView alerta = new WindowsView();
			alerta.setHeight("10px");
			alerta.setWidth("10px");
			alerta.onError("Tienes que ingresar una mesa , un cliente y productos");
			
		}
		
		return null;
		
		
		

	}


	
	
	
	


	@Override
	public String onTotal() {
		BigDecimal totalDinero=BigDecimal.ZERO;
		for(ItemVenta item : gridItemVenta.getList()){
			

			if (item.total!=null) {
				totalDinero=totalDinero.add(item.total);
				System.out.println("El total n° "+totalDinero);

			}
		//	this.grid.getDataProvider().refreshItem(item);		
			
		}
		
	
	
		
		

		//System.out.println("El valor total de el dinero es "+totalDinero);
		//this.grid.getDataProvider().refreshAll();
		columnaTotales.setFooter("S/"+totalDinero);

		return ""+totalDinero;

		
		//return "hola";
		
		
	
	}

	@Override
	public String onRecorrer() {
		// TODO Auto-generated method stub
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
			txtCliente.setValue(view.grid.getValue().getNombre());
			cliente= view.grid.getValue();
			view.closeDialog();
			}
			
			
		});
		
	}

	@Override
	public void onCambiarMesa() {
		
		System.out.println(comboMesas.getValue().getNombre());
		
	}










	


	/*
	@Override
	public ItemVenta onChange(ItemVenta item) {
		grid.select(item);
		if (grid.getValue().equals(item)) {
			grid.getValue().total = item.getPrecio().multiply(new BigDecimal(item.cantidad));
			grid.getDataProvider().refreshItem(item);
		}
		// grid.getValue().setCantidad(Integer.parseInt(String.valueOf(cantidad)));
		return null;
	}
*/
}