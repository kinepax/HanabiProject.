package com.kinesoft.zero.servicesImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.model.PedidoDetalle;
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.server.Server;

public final class PedidoDetalleServiceImpl extends WindowsView {

	public PedidoDetalleServiceImpl() {

	}

	public static List<PedidoDetalle> listarDetallesDePedido(Pedido pedido) throws SQLException {

		String id					= String.valueOf(pedido.getId());
		Connection conexion 		= Server.conectar();
		Statement state 			= conexion.createStatement();
		ResultSet resultados 		= state.executeQuery("Select * from pedido_detalle where pedido="+id);
		List<PedidoDetalle>
		listaDeDetallesDePedido 	= new ArrayList<PedidoDetalle>();
		Producto producto 			= new Producto();

		//Recorre la lista de resultados
		while (resultados.next()) {
			
			listaDeDetallesDePedido.add(
			new PedidoDetalle(pedido,
							  producto= ProductoServiceImpl.listarProductosConId(resultados.getString("producto")).get(0)
							  ,Integer.parseInt( resultados.getString("cantidad")),
							  new BigDecimal(resultados.getString("precio")),
							  new BigDecimal(resultados.getString("total"))
							  )										
					
					);
					
		}

		conexion.close();
		return listaDeDetallesDePedido;

	}

	public static void guardar(PedidoDetalle pedidoDetalle) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarDetallePedido = 
					conexion.prepareStatement("Insert into pedido_detalle (pedido,producto,cantidad,precio,total)"
					+ " values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			procedimientoInsertarDetallePedido.setInt			(1,pedidoDetalle.getPedidoCabecera().getId());
			procedimientoInsertarDetallePedido.setInt			(2,pedidoDetalle.getProducto().getId());
			procedimientoInsertarDetallePedido.setInt			(3,pedidoDetalle.getCantidad());
			procedimientoInsertarDetallePedido.setBigDecimal	(4,pedidoDetalle.getPrecio() );
			procedimientoInsertarDetallePedido.setBigDecimal	(5,pedidoDetalle.getTotal());

			procedimientoInsertarDetallePedido.executeUpdate();
			
		

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}
	

	}
/*

	public void editar(Cliente cliente) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoActualizarCliente = conexion
			.prepareStatement("update   cliente " + " set nombre=?,dni=?" + " where id=?");
			procedimientoActualizarCliente.setString(1, cliente.getNombre());
			procedimientoActualizarCliente.setString(2, cliente.getDni());
			procedimientoActualizarCliente.setInt   (3, cliente.getId());

			procedimientoActualizarCliente.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());

		}

	}

	public void eliminar(Cliente client) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarCliente 
			= conexion.prepareStatement("delete from   producto " + " where id=?");
			  procedimientoEliminarCliente.setInt(1, client.getId());
			  procedimientoEliminarCliente.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());

		}

	}
	*/


}
