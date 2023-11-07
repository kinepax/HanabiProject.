package com.kinesoft.zero.servicesImpl;

import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.server.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public  abstract class UsuarioServiceImpl  {

	public UsuarioServiceImpl() {

	}
	
	
	public static Usuario validarUsuario(Usuario user)throws SQLException {
		Connection conexion 		= Server.conectar();
		Statement state 			= conexion.createStatement();
		String where 				= "";
		Usuario usuario = new Usuario();
		if (user!=null)
		{
			where += " where id is not null and usuario= '" + user.getUsuario()+
					 "' and pass= '" + user.getPass()+"' limit 1" ;

		}
		
		ResultSet resultados = state.executeQuery("Select * from usuario " + where);		
		while (resultados.next()) {
			
			 usuario = new Usuario(
					Integer.parseInt(resultados.getString("id")),
					resultados.getString("nombre"), 
					resultados.getString("usuario"),
					resultados.getString("pass")
					
					 				);
					

		}	

		
		System.out.println("el usuario ingresado fue "+usuario.getNombre());
		
	
		conexion.close();
		return usuario;
	
		
		
	}
	

	public static List<Usuario> listarUsuarios(String id) throws SQLException {

		Connection conexion 		= Server.conectar();
		Statement state 			= conexion.createStatement();
		String where 				= " where id is not null";
		if (id != null) {

			where += " and id= " + id;

						}

		ResultSet resultados			= state.executeQuery("Select * from usuario " + where);
		List<Usuario> listaDeUsuarios   = new ArrayList<Usuario>();

		while (resultados.next()) {
				listaDeUsuarios.add(
					new Usuario(
								Integer.parseInt(resultados.getString("id")),
								resultados.getString("nombre"), 
								resultados.getString("usuario"),
								resultados.getString("pass")
							)
							);

						}	

		conexion.close();
		return listaDeUsuarios;

	}

	public static void guardar(Usuario usuario) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoInsertarUsuario 
			= conexion.prepareStatement("Insert into usuario (nombre,usuario,pass)"
			+ " values (?,?,?)");
			procedimientoInsertarUsuario.setString(1, usuario.getNombre());
			procedimientoInsertarUsuario.setString(2, usuario.getUsuario());
			procedimientoInsertarUsuario.setString(3, usuario.getPass());

			procedimientoInsertarUsuario.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

	}

	public static void editar(Usuario usuario) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoActualizarUsuario = conexion
					.prepareStatement("update   usuario " + " set nombre=?,usuario=?,pass=?" + " where id=?");
			procedimientoActualizarUsuario.setString(1, usuario.getNombre());
			procedimientoActualizarUsuario.setString(2, usuario.getUsuario());
			procedimientoActualizarUsuario.setString(3, usuario.getPass());
			procedimientoActualizarUsuario.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}

	}

	public static void eliminar(Usuario usuario) {
		Connection conexion = Server.conectar();
		try {
			PreparedStatement procedimientoEliminarUsuario = conexion.prepareStatement("delete from   usuario " + " where id=?");
			procedimientoEliminarUsuario.setInt(1, usuario.getId());
			procedimientoEliminarUsuario.executeUpdate();

		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			System.out.println(exepcion.getMessage());

		}

	}

}
