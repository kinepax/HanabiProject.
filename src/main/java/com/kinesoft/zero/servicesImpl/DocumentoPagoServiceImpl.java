package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.DocumentoPago;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.model.Serie;
import com.kinesoft.zero.server.Server;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DocumentoPagoServiceImpl extends WindowsView {

	public DocumentoPagoServiceImpl() {

	}

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
							resultados.getDate("fecha"),
							resultados.getString("condicion_pago"),
							ClienteServiceImpl.listarClientes(resultados.getString("cliente")).get(0),

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

	public static int save(DocumentoPago documentoPago) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("INSERT INTO documento_pago" +
					" (serie, numero, fecha, condicion_pago, cliente," +
					" valor_venta, igv, monto_igv, neto_pagar, estado," +
					" cancelado, usuario, monto_pagado, firma_electronica)"

					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			psIsertar.setInt(1,documentoPago.getSerie().getId());
			psIsertar.setInt(2,documentoPago.getNumero());
			psIsertar.setDate(3, (Date) documentoPago.getFecha());
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
		ResultSet resultados = state.executeQuery("SELECT IFNULL(MAX(serie), 0) AS ultimo_correlativo FROM documento_pago WHERE serie = "+serie.getId());
		Integer ultimoCorrelativo=0;

		while (resultados.next()) {

			ultimoCorrelativo= resultados.getInt("ultimo_correlativo");
		}

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
