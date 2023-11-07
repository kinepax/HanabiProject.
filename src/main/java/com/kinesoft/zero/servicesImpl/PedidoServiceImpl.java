package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.server.Server;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class PedidoServiceImpl extends WindowsView {

	public PedidoServiceImpl() {

	}

	public static List<Pedido> listarPedidos() throws SQLException {

		Connection conexion 				= Server.conectar();
		Statement state 					= conexion.createStatement();
		ResultSet resultados 				= state.executeQuery("Select * from pedido order by id desc");
		List<Pedido> listaDePedidos 		= new ArrayList<Pedido>();

		while (resultados.next()) {
			
			listaDePedidos.add(
					new Pedido(
							
					Integer.parseInt(resultados.getString("id")),
					//Optiene el nombre del cliente 
					ClienteServiceImpl.listarClientes(resultados.getString("cliente")).get(0),
					 MesaServiceImpl.listarMesas(resultados.getString("mesa")).get(0),
					new BigDecimal(resultados.getString("total")),
					
					resultados.getString("estado"),
					resultados.getTimestamp("fecha_hora").toLocalDateTime()
					
							)

								
					);

		}

		conexion.close();
		return listaDePedidos;

	}

	public static int save(Pedido pedido) {
		Connection con = Server.conectar();
		try {
			PreparedStatement psIsertar = con.prepareStatement("Insert into pedido (cliente,total,estado)"
					+ " values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			psIsertar.setInt			(1,pedido.getCliente().getId());
			psIsertar.setBigDecimal		(2,pedido.getTotal() );
			psIsertar.setString			(3,pedido.getEstado());

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
	


}
