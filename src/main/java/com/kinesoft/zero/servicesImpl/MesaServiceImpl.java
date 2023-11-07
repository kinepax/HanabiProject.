package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.server.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class MesaServiceImpl extends WindowsView {

	public MesaServiceImpl() {

	}

	public static List<Mesa> listarMesas(String condicion) throws SQLException {

		Connection conexion 		= Server.conectar();
		Statement state 		= conexion.createStatement();
		String where 		= " where id is not null";
		if (condicion != null) {

			where += " and id= " + condicion;

		}
		
		String query="Select * from mesa " + where;
		
		System.out.println(query);

		ResultSet resultados = state.executeQuery(query);
		List<Mesa> listaDeMesas = new ArrayList<Mesa>();

		while (resultados.next()) 
		{
				listaDeMesas.add(
						new Mesa(
								Integer.parseInt(resultados.getString("id")),
								resultados.getString("nombre")
								) 
						
								);

		}	

		conexion.close();
		return listaDeMesas;

	}

	public void guardar(Mesa mesa) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarMesa =
					conexion.prepareStatement
					("Insert into mesa (nombre)"
					+ " values (?)");
			
			procedimientoInsertarMesa.setString(1, mesa.getNombre());
			procedimientoInsertarMesa.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());
		

		}

	}

	public void editar(Mesa mesa) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarMesa = conexion
					.prepareStatement("update   mesa " + " set nombre=?" + " where id=?");
			procedimientoInsertarMesa.setString(1, mesa.getNombre());
			procedimientoInsertarMesa.setInt(2, mesa.getId());

			procedimientoInsertarMesa.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

	}

	public static void eliminar(Mesa mesa) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarMesa = conexion.prepareStatement("delete from   mesa " + " where id=?");

			procedimientoEliminarMesa.setInt(1, mesa.getId());

			procedimientoEliminarMesa.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}

	}



}
