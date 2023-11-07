package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.server.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  abstract class  ClienteServiceImpl extends WindowsView {

	public ClienteServiceImpl() {

	}

	public static List<Cliente> listarClientes(String condicion) throws SQLException {

		Connection conexion 		= Server.conectar();
		Statement state 		= conexion.createStatement();
		String where 		= " where id is not null";
		if (condicion != null) {

			where += " and id= " + condicion;

		}

		ResultSet resultados = state.executeQuery("Select * from cliente " + where);
		List<Cliente> listaDeClientes = new ArrayList<Cliente>();

		while (resultados.next()) {
				listaDeClientes.add(
					new Cliente(
								Integer.parseInt(resultados.getString("id")),
								resultados.getString("nombre"), 
								resultados.getString("dni")));

		}	

		conexion.close();
		return listaDeClientes;

	}

	public static void guardar(Cliente cliente) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarCliente = conexion.prepareStatement("Insert into cliente (nombre,dni)" + " values (?,?)");
			procedimientoInsertarCliente.setString(1, cliente.getNombre());
			procedimientoInsertarCliente.setString(2, cliente.getDni());

			procedimientoInsertarCliente.executeUpdate();

		} catch (SQLException e) {
			
			System.out.println(e.getMessage());

		}

	}

	public static void editar(Cliente client) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoActualizarCliente = conexion
					.prepareStatement("update   cliente " + " set nombre=?,dni=?" + " where id=?");
			procedimientoActualizarCliente.setString(1, client.getNombre());
			procedimientoActualizarCliente.setString(2, client.getDni());
			procedimientoActualizarCliente.setInt(3, client.getId());

			procedimientoActualizarCliente.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}

	}

	public static void eliminar(Cliente cliente) {
		Connection con = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarCliente = con.prepareStatement("delete from   cliente " + " where id=?");

			procedimientoEliminarCliente.setInt(1, cliente.getId());

			procedimientoEliminarCliente.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}

	}


}
