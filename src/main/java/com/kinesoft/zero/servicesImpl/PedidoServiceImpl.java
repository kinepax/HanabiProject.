package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.dto.PedidoDTO;
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

	public static List<PedidoDTO> listarPedidos() throws SQLException {


		Connection conexion 				= Server.conectar();
		Statement state 					= conexion.createStatement();
		String query="SELECT p.id,c.id as idCliente,c.nombre as cliente," +
				"m.id as idMesa, m.nombre as mesa ," +
				"p.total,p.estado, p.fecha_hora " +
				"FROM `pedido` as p " +
				"inner join mesa as m ON p.mesa=m.id " +
				"inner join cliente as c ON p.cliente=c.id; ";

		ResultSet resultados 				= state.executeQuery(query);
		List<PedidoDTO> listaDePedidos 		= new ArrayList<PedidoDTO>();

		while (resultados.next()) {
			
			listaDePedidos.add(
					new PedidoDTO(
					resultados.getInt("id"),
							resultados.getInt("idCliente"),
							resultados.getString("cliente"),
							resultados.getInt("idMesa"),
							resultados.getString("mesa"),
							resultados.getBigDecimal("total"),
							resultados.getString("estado"),
							resultados.getTimestamp("fecha_hora").toLocalDateTime()


							)

								
					);

		}

		conexion.close();
		return listaDePedidos;

	}


	public static List<PedidoDTO> listarPedidos(String fechaInicial,String fechaFinal,
												Integer idMesa,
												String cliente,
												String estado

												) throws SQLException {


		Connection conexion 				= Server.conectar();
		Statement state 					= conexion.createStatement();
		String where=" where p.id is not null ";
			where+="AND p.fecha_hora BETWEEN '"+fechaInicial+" 00:00:00 ' and '"+fechaFinal+" 23:59:59' ";

		where+="AND c.nombre like '%"+cliente+"%' ";
			if(idMesa!=0){
			where+=	"AND m.id = "+idMesa;

			}

		if(!estado.equals("TODOS")){
			where+=	" AND estado like '%"+estado+"%' ";

		}

		where+="  order by p.id desc";

		String query="SELECT p.id,c.id as idCliente,c.nombre as cliente," +
				"m.id as idMesa, m.nombre as mesa ," +
				"p.total,p.estado, p.fecha_hora " +
				"FROM `pedido` as p " +
				"inner join mesa as m ON p.mesa=m.id " +
				"inner join cliente as c ON p.cliente=c.id  "+where;

		System.out.println("la consulta es "+query);

		ResultSet resultados 				= state.executeQuery(query);
		List<PedidoDTO> listaDePedidos 		= new ArrayList<PedidoDTO>();

		while (resultados.next()) {

			listaDePedidos.add(
					new PedidoDTO(
							resultados.getInt("id"),
							resultados.getInt("idCliente"),
							resultados.getString("cliente"),
							resultados.getInt("idMesa"),
							resultados.getString("mesa"),
							resultados.getBigDecimal("total"),
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
