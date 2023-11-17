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
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.server.Server;

public final class ProductoServiceImpl extends WindowsView {

	public ProductoServiceImpl() {

	}

	public static List<Producto> listarProductos() throws SQLException {

		Connection conexion 				= Server.conectar();
		Statement state 				= conexion.createStatement();

		ResultSet resultados 				= state.executeQuery("Select * from producto");
		List<Producto> listadoDeProductos 	= new ArrayList<Producto>();

		//Recorre todos los productos
		while (resultados.next()) {
			
			listadoDeProductos.add(
				new Producto(Integer.parseInt(resultados.getString("id")),
				resultados.getString("nombre"),
				resultados.getString("descripcion"),
				BigDecimal.valueOf(Double.parseDouble(resultados.getString("precio"))),
				Integer.parseInt(resultados.getString("stock")))
					
								  );

		}

		conexion.close();
		return listadoDeProductos;

	}


	public static List<Producto> listarProductos(String condicion) throws SQLException {

		Connection conexion 				= Server.conectar();
		Statement state 				= conexion.createStatement();
		String where 		= " where id is not null";
			where += " and nombre like '%" + condicion+"%' ";


		ResultSet resultados 				= state.executeQuery("Select * from producto "+where);
		List<Producto> listadoDeProductos 	= new ArrayList<Producto>();

		//Recorre todos los productos
		while (resultados.next()) {

			listadoDeProductos.add(
					new Producto(Integer.parseInt(resultados.getString("id")),
							resultados.getString("nombre"),
							resultados.getString("descripcion"),
							BigDecimal.valueOf(Double.parseDouble(resultados.getString("precio"))),
							Integer.parseInt(resultados.getString("stock")))

			);

		}

		conexion.close();
		return listadoDeProductos;

	}

	public static List<Producto> listarProductosConId(String id) throws SQLException {

		Connection conexion 				= Server.conectar();
		Statement state 					= conexion.createStatement();
		ResultSet resultados 				= state.executeQuery("Select * from producto where id="+id);
		List<Producto> listadoDeProductos 	= new ArrayList<Producto>();

		while (resultados.next()){
			
			listadoDeProductos.add(new Producto(
					Integer.parseInt(resultados.getString("id")),
					resultados.getString("nombre"),
					resultados.getString("descripcion"),
					BigDecimal.valueOf(Double.parseDouble(resultados.getString("precio"))),
					Integer.parseInt(resultados.getString("stock"))
												)
					
									);

								 }

		conexion.close();
		return listadoDeProductos;

	}
	
	
	public void guardar(Producto producto) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarProducto = 
			conexion.prepareStatement("Insert into producto (nombre,descripcion,precio,stock)"
			+ "values (?,?,?,?)");
			procedimientoInsertarProducto.setString(1, producto.getNombre());
			procedimientoInsertarProducto.setString(2, producto.getDescripcion());
			procedimientoInsertarProducto.setBigDecimal(3, producto.getPrecio());
			procedimientoInsertarProducto.setInt(4, producto.getStock());
			procedimientoInsertarProducto.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());

		}

	}

	public void editar(Producto producto) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoEditarProducto = conexion.prepareStatement(
			"UPDATE   producto " + " set nombre=?,descripcion=?,precio=?,stock=?" + " where id=?");
			procedimientoEditarProducto.setString(1, producto.getNombre());
			procedimientoEditarProducto.setString(2, producto.getDescripcion());
			procedimientoEditarProducto.setBigDecimal(3, producto.getPrecio());
			procedimientoEditarProducto.setInt(4, producto.getStock());
			procedimientoEditarProducto.setInt(5, producto.getId());

			procedimientoEditarProducto.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}

	public void eliminar(Producto producto) {
		Connection con = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarProducto 
			= con.prepareStatement("delete from   producto " + " where id=?");

			procedimientoEliminarProducto.setInt(1, producto.getId());
			procedimientoEliminarProducto.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());

		}

	}

}
