package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.DocumentoPago;
import com.kinesoft.zero.model.DocumentoPagoDetalle;
import com.kinesoft.zero.server.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DocumentoPagoDetalleServiceImpl extends WindowsView {

	public DocumentoPagoDetalleServiceImpl() {

	}

	public static List<DocumentoPagoDetalle> listaDetallesDocumentoPago(DocumentoPago documentoPago) throws SQLException {

		String id					= String.valueOf(documentoPago.getId());
		Connection conexion 		= Server.conectar();
		Statement state 			= conexion.createStatement();
		ResultSet resultados 		= state.executeQuery("Select * from documento_pago_detalle where documento_pago="+id);
		List<DocumentoPagoDetalle>
		listaDocumentoPagoDetalle 	= new ArrayList<DocumentoPagoDetalle>();

		//Recorre la lista de resultados
		while (resultados.next()) {

			listaDocumentoPagoDetalle.add(
			new DocumentoPagoDetalle(

					resultados.getInt("id"),
					documentoPago,
					ProductoServiceImpl.listarProductosConId( String.valueOf(resultados.getInt("producto"))).get(0),
					resultados.getInt("cantidad"),
					resultados.getBigDecimal("precio"),
					resultados.getBigDecimal("total")

							  )
					
					);
					
		}

		conexion.close();
		return listaDocumentoPagoDetalle;

	}

	public static void guardar(DocumentoPagoDetalle documentoPagoDetalle) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarDetallePedido = 
					conexion.prepareStatement("Insert into documento_pago_detalle (documento_pago,producto_id,producto,cantidad,precio,total)"
					+ " values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			procedimientoInsertarDetallePedido.setInt			(1,documentoPagoDetalle.getDocumento_pago().getId());
			procedimientoInsertarDetallePedido.setInt			(2,documentoPagoDetalle.getProducto().getId());
			procedimientoInsertarDetallePedido.setString		(3,documentoPagoDetalle.getProducto().getNombre());
			procedimientoInsertarDetallePedido.setInt			(4,documentoPagoDetalle.getCantidad());
			procedimientoInsertarDetallePedido.setBigDecimal	(5,documentoPagoDetalle.getPrecio() );
			procedimientoInsertarDetallePedido.setBigDecimal	(6,documentoPagoDetalle.getTotal());

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
