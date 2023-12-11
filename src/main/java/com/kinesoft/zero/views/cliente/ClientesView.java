package com.kinesoft.zero.views.cliente;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.servicesImpl.ClienteServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesView extends ClientesUI implements WindowsView.ConfirmarListener {

	List<Cliente> listaDeClientes = new ArrayList<>();
	Cliente cliente = new Cliente();

	public ClientesView() {
		iniData();

	}

	public void iniData() {
		onRefrescar();
	}

	@Override
	public void onadd() {
	//	Broadcaster.broadcast("¡Nuevo pedido!");
		ClienteView vistaCliente = new ClienteView();


		vistaCliente.setSizeFull();
		vistaCliente.showDialog(vistaCliente);
	}

	@Override
	public void onRefrescar() {


		try {
			String dni = txtDni.getValue();
			String nombre = txtNombre.getValue();


			listaDeClientes = ClienteServiceImpl.listarClientes(dni, nombre);
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

	@Override
	public void onConfirmacionSeleccionada(boolean respuesta) {
		System.out.println("El valor recibio fue MIRAMEEEEEEEE "+respuesta);
		onRefrescar();
	}
}

