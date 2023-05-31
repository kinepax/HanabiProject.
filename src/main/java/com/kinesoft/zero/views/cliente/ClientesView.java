package com.kinesoft.zero.views.cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.servicesImpl.ClienteServiceImpl;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

public class ClientesView extends ClientesUI {

	List<Cliente> listaDeClientes 				= new ArrayList<>();
	Cliente cliente 							= new Cliente();
	public ClientesView() {
		iniData();

	}

	public void iniData() {
		onRefrescar();
	}

	@Override
	public  void onadd() {
		ClienteView vistaCliente = new ClienteView();
		
		
		vistaCliente.setSizeFull();
		vistaCliente.showDialog(vistaCliente);
	}

	@Override
	public void  onRefrescar() {
		try {
			listaDeClientes = ClienteServiceImpl.listarClientes(null);
			grid.setItems(listaDeClientes);
		} catch (SQLException exepcion) {
			// TODO Auto-generated catch block
			exepcion.printStackTrace();
		}
	}

	@Override
	public void onEditar() {
		cliente = grid.getValue();
		ClienteView vistaCliente = new ClienteView(cliente);
		vistaCliente.setSizeFull();
		vistaCliente.showDialog(vistaCliente);
	}

	@Override
	public void onEliminar() {
		cliente = grid.getValue();
		try {
			ClienteServiceImpl.eliminar(cliente);

		} catch (Exception e) {
			// TODO: handle exception
		}
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