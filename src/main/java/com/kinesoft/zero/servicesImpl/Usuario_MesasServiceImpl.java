package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.model.Usuario_Mesas;
import com.kinesoft.zero.server.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Usuario_MesasServiceImpl extends WindowsView {

	public Usuario_MesasServiceImpl() {

	}

	public static List<Usuario_Mesas> listarUsuariosMesas(String id) throws SQLException {

		Connection conexion 		= Server.conectar();
		Statement state 		= conexion.createStatement();
		String where 		= " where id is not null";
		Usuario usuario 				= new Usuario();
		Mesa mesa 				= new Mesa();

		if (id != null) {

			where += " and id= " + id;

		}

		ResultSet resultados = state.executeQuery("Select * from usuario_mesa " + where);
		List<Usuario_Mesas> listaDeUsuarioMesas = new ArrayList<Usuario_Mesas>();

		while (resultados.next()) {
				listaDeUsuarioMesas.add(
					new Usuario_Mesas(
						Integer.parseInt(resultados.getString("id")),
						usuario = UsuarioServiceImpl.listarUsuarios(resultados.getString("usuario")).get(0),
						mesa = MesaServiceImpl.listarMesas(resultados.getString("mesa")).get(0)
						
							)
					);

		}	

		conexion.close();
		return listaDeUsuarioMesas;

	}

	public void guardar(Usuario_Mesas usuarioMesas) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarUsuarioEnMesa = conexion.prepareStatement("Insert into usuario_mesa (usuario,mesa)"
		+ " values (?,?)");
			procedimientoInsertarUsuarioEnMesa.setInt(1, usuarioMesas.getUser().getId());
			procedimientoInsertarUsuarioEnMesa.setInt(2, usuarioMesas.getMesa().getId());

			procedimientoInsertarUsuarioEnMesa.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.onError(e.getMessage());

		}

	}

	public void editar(Usuario_Mesas usuarioMesas) throws SQLException {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoActualizarUsuarioMesa = conexion
					.prepareStatement("update   usuario_mesa " + " set usuario=?,mesa=?" + " where id=?");
			procedimientoActualizarUsuarioMesa.setInt(1, usuarioMesas.getUser().getId());
			procedimientoActualizarUsuarioMesa.setInt(2, usuarioMesas.getMesa().getId());
			procedimientoActualizarUsuarioMesa.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());
			conexion.close();

		}
		
		

	}

	public void eliminar(Usuario_Mesas usuarioMesas) throws SQLException {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarUsuarioMesas = conexion.prepareStatement("delete from   usuario_mesa " + " where id=?");

			procedimientoEliminarUsuarioMesas.setInt(1, usuarioMesas.getId());

			procedimientoEliminarUsuarioMesas.executeUpdate();
			conexion.close();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			this.onError(exepcion.getMessage());
			conexion.close();


		}
		
		
		

	}

}
