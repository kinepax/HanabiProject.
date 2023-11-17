package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.DocumentoPago;
import com.kinesoft.zero.model.Serie;
import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.server.Server;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DocumentoPagoServiceImpl extends WindowsView {

	public DocumentoPagoServiceImpl() {

	}
/*
	public static List<DocumentoPago> listarDocumentosPago() throws SQLException {

		Connection conexion = Server.conectar();
		Statement state = conexion.createStatement();
		ResultSet resultados = state.executeQuery("Select * from documento_pago order by id desc");
		List<DocumentoPago> listaDocumentosPago = new ArrayList<DocumentoPago>();

		while (resultados.next()) {

			listaDocumentosPago.add(
					new DocumentoPago(
							Integer.parseInt(resultados.getString("id")),
							//Optiene el nombre del cliente
							SerieServiceImpl.listarSeries(String.valueOf(resultados.getInt("serie"))).get(0),
							resultados.getInt("numero"),
							resultados.getDate("fecha").toLocalDate(),
							resultados.getString("condicion_pago"),
							ClienteServiceImpl.listarClientes(resultados.getString("cliente"),null).get(0),

							new BigDecimal(resultados.getString("valor_venta")),
							new BigDecimal(resultados.getString("igv")),
							new BigDecimal(resultados.getString("monto_igv")),
							new BigDecimal(resultados.getString("neto_pagar")),
							resultados.getString("estado"),
							resultados.getBoolean("cancelado"),
							UsuarioServiceImpl.listarUsuarios(resultados.getString("usuario")).get(0),
							new BigDecimal(resultados.getString("monto_pagado")),
							resultados.getString("firma_electronica")


					)


			);

		}

		conexion.close();
		return listaDocumentosPago;

	}
*/

	public static List<DocumentoPago> listarDocumentosPago() throws SQLException {

		Connection conexion = Server.conectar();
		Statement state = conexion.createStatement();


		String query  = "SELECT d.id as idDocumento,\n" +
				"s.id as idSerie,\n" +
				"s.serie ,\n" +
				"d.numero,\n" +
				"d.fecha,\n" +
				"d.condicion_pago,\n" +
				"c.id as idCliente,\n" +
				"c.nombre,\n" +
				"d.valor_venta,\n"+
				"d.neto_pagar,\n" +
				"d.igv,\n" +
				"d.monto_igv,\n" +
				"d.monto_pagado,\n" +
				"u.id as idUsuario,\n" +
				"u.usuario,\n" +
				"d.estado,\n"+
				"d.cancelado,\n"+
				"d.firma_electronica FROM `documento_pago` as d " +
				"inner join serie as s ON s.id=d.serie " +
				"inner join cliente as c on c.id=d.cliente " +
				"inner join usuario as u ON u.id=d.usuario ";


		ResultSet resultados = state.executeQuery(query);
		List<DocumentoPago> listaDocumentosPago = new ArrayList<DocumentoPago>();

		while (resultados.next()) {

			listaDocumentosPago.add(
					new DocumentoPago(
							Integer.parseInt(resultados.getString("idDocumento")),
							//Optiene el nombre del cliente
							new Serie(
									resultados.getInt("idSerie"),
									resultados.getString("serie")
							),
						//	SerieServiceImpl.listarSeries(String.valueOf(resultados.getInt("serie"))).get(0),
							resultados.getInt("numero"),
							resultados.getDate("fecha").toLocalDate(),
							resultados.getString("condicion_pago"),
							new Cliente(
									resultados.getInt("idCliente"),
									resultados.getString("nombre")
							),

						//	ClienteServiceImpl.listarClientes(resultados.getString("cliente"),null).get(0),

							new BigDecimal(resultados.getString("valor_venta")),
							new BigDecimal(resultados.getString("igv")),
							new BigDecimal(resultados.getString("monto_igv")),
							new BigDecimal(resultados.getString("neto_pagar")),
							resultados.getString("estado"),
							resultados.getBoolean("cancelado"),

						//	UsuarioServiceImpl.listarUsuarios(resultados.getString("usuario")).get(0),
							new Usuario(
									resultados.getInt("idUsuario"),
									resultados.getString("usuario")
							),

							new BigDecimal(resultados.getString("monto_pagado")),
							resultados.getString("firma_electronica")


					)


			);

		}

		conexion.close();
		return listaDocumentosPago;

	}

	public static List<DocumentoPago> listarDocumentosPago(
			String fechaInicial,
			String fechaFinal,
			String condicionPago,
			String cliente
	) throws SQLException {


		String where=" where d.id is not null ";
		where+="AND d.fecha BETWEEN '"+fechaInicial+" 00:00:00 ' and '"+fechaFinal+" 23:59:59' ";

		where+="AND c.nombre like '%"+cliente+"%' ";


		if(!condicionPago.equals("TODOS")){
			where+=	" AND condicion_pago like '%"+condicionPago+"%' ";

		}

		Connection conexion = Server.conectar();
		Statement state = conexion.createStatement();


		String query  = "SELECT d.id as idDocumento,\n" +
				"s.id as idSerie,\n" +
				"s.serie ,\n" +
				"d.numero,\n" +
				"d.fecha,\n" +
				"d.condicion_pago,\n" +
				"c.id as idCliente,\n" +
				"c.nombre,\n" +
				"d.valor_venta,\n"+
				"d.neto_pagar,\n" +
				"d.igv,\n" +
				"d.monto_igv,\n" +
				"d.monto_pagado,\n" +
				"u.id as idUsuario,\n" +
				"u.usuario,\n" +
				"d.estado,\n"+
				"d.cancelado,\n"+
				"d.firma_electronica FROM `documento_pago` as d " +
				"inner join serie as s ON s.id=d.serie " +
				"inner join cliente as c on c.id=d.cliente " +
				"inner join usuario as u ON u.id=d.usuario "+where;

		System.out.println("La consulta es "+query);


		ResultSet resultados = state.executeQuery(query);
		List<DocumentoPago> listaDocumentosPago = new ArrayList<DocumentoPago>();

		while (resultados.next()) {

			listaDocumentosPago.add(
					new DocumentoPago(
							Integer.parseInt(resultados.getString("idDocumento")),
							//Optiene el nombre del cliente
							new Serie(
									resultados.getInt("idSerie"),
									resultados.getString("serie")
							),
							//	SerieServiceImpl.listarSeries(String.valueOf(resultados.getInt("serie"))).get(0),
							resultados.getInt("numero"),
							resultados.getDate("fecha").toLocalDate(),
							resultados.getString("condicion_pago"),
							new Cliente(
									resultados.getInt("idCliente"),
									resultados.getString("nombre")
							),

							//	ClienteServiceImpl.listarClientes(resultados.getString("cliente"),null).get(0),

							new BigDecimal(resultados.getString("valor_venta")),
							new BigDecimal(resultados.getString("igv")),
							new BigDecimal(resultados.getString("monto_igv")),
							new BigDecimal(resultados.getString("neto_pagar")),
							resultados.getString("estado"),
							resultados.getBoolean("cancelado"),

							//	UsuarioServiceImpl.listarUsuarios(resultados.getString("usuario")).get(0),
							new Usuario(
									resultados.getInt("idUsuario"),
									resultados.getString("usuario")
							),

							new BigDecimal(resultados.getString("monto_pagado")),
							resultados.getString("firma_electronica")


					)


			);

		}

		conexion.close();
		return listaDocumentosPago;

	}



	public static int save(DocumentoPago documentoPago) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("INSERT INTO documento_pago" +
					" (serie, numero, fecha, condicion_pago, cliente," +
					" valor_venta, igv, monto_igv, neto_pagar, estado," +
					" cancelado, usuario, monto_pagado, firma_electronica)"

					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			Date fechaDateSql = Date.valueOf(documentoPago.getFecha());


			psIsertar.setInt(1,documentoPago.getSerie().getId());
			psIsertar.setInt(2,documentoPago.getNumero());
			psIsertar.setDate(3, fechaDateSql);
			psIsertar.setString(4,documentoPago.getCondicion_pago());
			psIsertar.setInt(5,documentoPago.getCliente().getId());
			psIsertar.setBigDecimal(6,documentoPago.getValor_venta());
			psIsertar.setBigDecimal(7,documentoPago.getIgv());
			psIsertar.setBigDecimal(8,documentoPago.getMonto_igv());
			psIsertar.setBigDecimal(9,documentoPago.getNeto_pagar());
			psIsertar.setString(10,documentoPago.getEstado());
			psIsertar.setBoolean(11,documentoPago.getCancelado());
			psIsertar.setInt(12,documentoPago.getUsuario().getId());
			psIsertar.setBigDecimal(13,documentoPago.getMonto_pagado());
			psIsertar.setString(14,documentoPago.getFirma_electronica());


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


	public static Integer devolverCorrelativo(Serie serie) throws SQLException {

		Connection conexion = Server.conectar();
		Statement state = conexion.createStatement();
		String consulta = "SELECT IFNULL(MAX(numero), 0) AS ultimo_correlativo FROM documento_pago WHERE serie = "+serie.getId();
		ResultSet resultados = state.executeQuery(consulta);


		Integer ultimoCorrelativo=0;

		while (resultados.next()) {

			ultimoCorrelativo= resultados.getInt("ultimo_correlativo");
		}

		System.out.println(consulta);
		conexion.close();
		return ultimoCorrelativo;


	}









/*
	public void edit(Cliente client) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con
					.prepareStatement("update   cliente " + " set nombre=?,dni=?" + " where id=?");
			psIsertar.setString(1, client.getNombre());
			psIsertar.setString(2, client.getDni());
			psIsertar.setInt(3, client.getId());

			psIsertar.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}

	public void delete(Cliente client) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("delete from   producto " + " where id=?");

			psIsertar.setInt(1, client.getId());

			psIsertar.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}
*/


}
