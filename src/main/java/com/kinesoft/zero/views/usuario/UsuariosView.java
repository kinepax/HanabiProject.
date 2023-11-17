package com.kinesoft.zero.views.usuario;

import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.servicesImpl.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosView extends UsuariosUI {
	
	List<Usuario> usuarios = new ArrayList<>();
	Usuario entity = new Usuario();

	public UsuariosView() {
		iniData();

	}

	public void iniData() {
		onRefrescar();
	}
	
	

	@Override
	public String onadd() {
		UsuarioView view = new UsuarioView();
		view.setSizeFull();
		view.showDialog(view);

		return null;
	}

	@Override
	public String onRefrescar() {
		String nombre=txtNombre.getValue();
		String usuario=txtUsuario.getValue();
		try {
			usuarios = UsuarioServiceImpl.listarUsuarios(nombre,usuario);
			grid.setItems(usuarios);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String onEditar() {
		entity = grid.getValue();
		if(entity!=null) {
		UsuarioView view = new UsuarioView(entity);
		view.setSizeFull();
		view.showDialog(view);

		System.out.println(entity.getNombre());
		return null;
		
		}
		return null;
	}

	@Override
	public String onEliminar() {
		entity = grid.getValue();
		try {
			UsuarioServiceImpl.eliminar(entity);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void onCerrar() {
		// TODO Auto-generated method stub
		this.closeDialog();		
	}

	@Override
	public void onSeleccionar() {
		// TODO Auto-generated method stub
		
	}

}
