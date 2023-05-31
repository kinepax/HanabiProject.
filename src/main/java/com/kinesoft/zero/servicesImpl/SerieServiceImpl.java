package com.kinesoft.zero.servicesImpl;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.*;
import com.kinesoft.zero.server.Server;

public class SerieServiceImpl  extends WindowsView  {


	public SerieServiceImpl() {

	}

	public static List<Serie> listarSeries(String condicion) throws SQLException{
		Connection conexion = Server.conectar();
		Statement state = conexion.createStatement();

		String where = " where id is not null";
		if (condicion != null) {

			where += " and id= " + condicion;

		}
		String query="Select * from serie "+ where;
		System.out.println(query);
		ResultSet resultados = state.executeQuery(query);
		List<Serie> listaDeSeries = new ArrayList<Serie>();

		while(resultados.next()) {

			listaDeSeries.add(new Serie(

					Integer.parseInt(resultados.getString("id")),
					resultados.getString("serie"),
					UsuarioServiceImpl.listarUsuarios(resultados.getString("usuario")).get(0),
					resultados.getTimestamp("fecha_hora").toLocalDateTime(),
					resultados.getString("serie_anterior"),
					resultados.getString("serie_actual"),
					resultados.getString("operacion")


			));


		}
		conexion.close();


		return listaDeSeries;


	}

	public static int save(Serie serie) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("Insert into serie (serie,usuario,fecha_hora,serie_anterior,serie_actual,operacion)"
					+ " values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			psIsertar.setString(1,serie.getSerie());
			psIsertar.setInt(2,serie.getUsuario().getId() );
			psIsertar.setTimestamp(3,Timestamp.from(Instant.now()));
			psIsertar.setString(4,serie.getSerie());
			psIsertar.setString(5,serie.getSerie());
			psIsertar.setString(6,psIsertar.toString());
			System.out.println("la consuta fue la siguiente "+psIsertar.toString());
			psIsertar.executeUpdate();

			ResultSet rs=psIsertar.getGeneratedKeys();
			int llaveGenerada=0;
			if(rs.next()) {
				llaveGenerada=rs.getInt(1);


			}
			return llaveGenerada;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
		return 0;

	}


	public void edit(Serie serie) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con
					.prepareStatement("update   serie " + " set serie=?,usuario=?," +

							"  fecha_hora=?,serie_anterior=?," +
							"  serie_actual=?,operacion=?" +
							" where id=?");
			psIsertar.setString(1, serie.getSerie());
			psIsertar.setInt(2, serie.getUsuario().getId());
			psIsertar.setTimestamp(3, Timestamp.from(Instant.now()));

			psIsertar.setString(4,serie.getSerie_anterior());
			psIsertar.setString(5,serie.getSerie_nueva());
			psIsertar.setString(6,psIsertar.toString());
			//	psIsertar.setString(6,"");

			psIsertar.setInt(7,serie.getId());

			System.out.println(psIsertar.toString());
			psIsertar.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}


	public void delete(Serie serie) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("delete from   serie " + " where id=?");

			psIsertar.setInt(1, serie.getId());

			psIsertar.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}


}
