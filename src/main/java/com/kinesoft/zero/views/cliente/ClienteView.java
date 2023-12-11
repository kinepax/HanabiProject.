package com.kinesoft.zero.views.cliente;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Cliente;

import com.kinesoft.zero.servicesImpl.ClienteServiceImpl;

public class ClienteView extends ClienteUI {

	Cliente cliente 				= new Cliente();
	public boolean save;

	public ClienteView(Cliente entity) {
		this.save = entity == null;
		System.out.println("El valor de save es " + save);
		initData(entity);

	}

	public ClienteView() {
		this(null);
	}



	public void initData(Cliente cliente) {
		if (save) {
			return;

		}
		txtId.setText("" + cliente.getId());
		txtNombre.setValue(cliente.getNombre());
		txtDni.setValue(cliente.getDni());
	

	}

	@Override
	public void onCerrar() {
		this.closeDialog();

	}

	@Override
	public void onSave() {

		WindowsView.ConfirmarListener view = respuesta -> {

			if(respuesta){
				cliente.setNombre(txtNombre.getValue());
				cliente.setDni(String.valueOf(txtDni.getValue()));

				if (!save) {
					cliente.setId(Integer.parseInt(txtId.getText()));

					ClienteServiceImpl.editar(cliente);
					this.closeDialog();

				} else {
					ClienteServiceImpl.guardar(cliente);
					this.closeDialog();

				}

			}

		};
		WindowsView.onPreguntarGrabar("¿Está seguro de que desea continuar?", view);



	}

}
