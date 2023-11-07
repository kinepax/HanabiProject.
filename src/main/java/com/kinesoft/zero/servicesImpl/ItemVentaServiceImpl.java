package com.kinesoft.zero.servicesImpl;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.ItemVenta;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.model.PedidoDetalle;
import com.kinesoft.zero.model.Producto;

public final class ItemVentaServiceImpl extends WindowsView {
	//Actualizacion por Kine

	public ItemVentaServiceImpl() {

	}

	public static List<ItemVenta> listaDeItemsEnVenta() throws SQLException {

		List<ItemVenta> listaDeItemsDeVenta 	= new ArrayList<ItemVenta>();
		List<Producto> 	listaDeProductos;

		listaDeProductos						= ProductoServiceImpl.listarProductos();
		int idParaItemVenta = 1;
		for (Producto producto : listaDeProductos) {

			listaDeItemsDeVenta.add(
					new ItemVenta(idParaItemVenta,
								producto,
								0,
								new BigDecimal(0),
								producto.getPrecio(),
								false));
			
			idParaItemVenta++;
		}

		return listaDeItemsDeVenta;

	}
	
	
	public static List<ItemVenta> listarItemsPedido(Pedido pedido) throws SQLException {

		List<ItemVenta>		listaDeItemsEnVenta 			= new ArrayList<ItemVenta>();
		List<Producto>		listaDeProductos 				= new ArrayList<Producto>();		
		List<PedidoDetalle>	listaDeDetalleDePedidos			= new ArrayList<PedidoDetalle>();
		int idParaItemVenta = 1;

		listaDeDetalleDePedidos	=	PedidoDetalleServiceImpl.listarDetallesDePedido(pedido);
	
		for (PedidoDetalle pedDetalle : listaDeDetalleDePedidos) {
			
			listaDeItemsEnVenta.add(
					new ItemVenta(
					idParaItemVenta,
					pedDetalle.getProducto(),
					pedDetalle.getCantidad(),
					pedDetalle.getTotal(),
					pedDetalle.getPrecio(),false )
					);
			
			idParaItemVenta++;

	
																}

		return listaDeItemsEnVenta;

	}

}
