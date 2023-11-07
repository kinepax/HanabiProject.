package com.kinesoft.zero.views.mesa;

import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.servicesImpl.MesaServiceImpl;

public class MesaView extends MesaUI {

	Mesa mesa 				= new Mesa();
	MesaServiceImpl servicio = new MesaServiceImpl();
	public boolean save;

	public MesaView(Mesa entity) {
		this.save = entity == null;
		System.out.println("El valor de save es " + save);
		initData(entity);

	}

	public MesaView() {
		this(null);
	}



	public void initData(Mesa mesa) {
		if (save) {
			return;

		}
		txtId.setText("" + mesa.getId());
		txtNombre.setValue(mesa.getNombre());
	

	}

	@Override
	public String onCerrar() {
		this.closeDialog();
		return null;

	}

	@Override
	public String onSave() {
		mesa.setNombre(txtNombre.getValue());
	


		if (!save) {
			mesa.setId(Integer.parseInt(txtId.getText()));

			servicio.editar(mesa);
			this.closeDialog();

		} else {
			servicio.guardar(mesa);
			this.closeDialog();

		}

		return null;

	}

}
