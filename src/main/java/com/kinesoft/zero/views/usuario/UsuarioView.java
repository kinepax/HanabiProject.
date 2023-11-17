package com.kinesoft.zero.views.usuario;

import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.model.Usuario_Mesas;
import com.kinesoft.zero.servicesImpl.MesaServiceImpl;
import com.kinesoft.zero.servicesImpl.UsuarioServiceImpl;
import com.kinesoft.zero.servicesImpl.Usuario_MesasServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioView extends UsuarioUI {

	Usuario user 				= new Usuario();
	List<Mesa> list_mesas = new ArrayList<>();
	List<Usuario_Mesas> list_usuarios_mesas = new ArrayList<>();
	
	Usuario_Mesas usuario_x_mesa=new Usuario_Mesas();
	
	
	Usuario_MesasServiceImpl usuario_Mesa_ServiceImpl = new Usuario_MesasServiceImpl();


	public boolean save;

	public UsuarioView(Usuario entity) {
		this.save = entity == null;
		System.out.println("El valor de save es " + save);
		initData(entity);

	}

	public UsuarioView() {
		this(null);
	}



	public void initData(Usuario user) {

		if (save) {
			onDisable();
			return;
			
			

		}
		txtId.setText("" + user.getId());
		txtNombre.setValue(user.getNombre());
		txtUsuario.setValue(user.getUsuario());
		txtPass.setValue(user.getPass());
		this.user=user;
		onCargar();


	}
	public void onDisable() {
		
		btnagregarMesa.setVisible(false);
		btnquitarMesa.setVisible(false);
		usuariosxmesas_grid.setVisible(false);
		mesas_grid.setVisible(false);
	}
	
	
	@Override
	public String onCerrar() {
		this.closeDialog();
		return null;

	}

	@Override
	public String onSave() {
		user.setNombre(txtNombre.getValue());
		user.setUsuario(txtUsuario.getValue());
		user.setPass(txtPass.getValue());


		if (!save) {
			user.setId(Integer.parseInt(txtId.getText()));

			UsuarioServiceImpl.editar(user);
			this.closeDialog();

		} else {
			UsuarioServiceImpl.guardar(user);
			this.closeDialog();

		}

		return null;

	}

	@Override
	public void onCargar() {
		/*
		try {
	///		list_mesas=MesaServiceImpl.listarMesas(null);

		//	mesas_grid.setlist(list_mesas);


		//	list_usuarios_mesas=Usuario_MesasServiceImpl.listarUsuariosMesas(null);
			usuariosxmesas_grid.setlist(list_usuarios_mesas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

	@Override
	public void onAgregar() {
		/*
		usuario_x_mesa.setMesa(mesas_grid.getValue());
		usuario_x_mesa.setUser(user);
		
		usuario_Mesa_ServiceImpl.guardar(usuario_x_mesa);
		onCargar();
*/
	}

	@Override
	public void onQuitar() {
		/*
		usuario_x_mesa=usuariosxmesas_grid.getValue();
		try {
			usuario_Mesa_ServiceImpl.eliminar(usuario_x_mesa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		onCargar();


		 */
		
	}

}
