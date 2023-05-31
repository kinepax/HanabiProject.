package com.kinesoft.zero.views.mesa;

import java.math.BigDecimal;

import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.servicesImpl.ClienteServiceImpl;
import com.kinesoft.zero.servicesImpl.MesaServiceImpl;
import com.kinesoft.zero.servicesImpl.ProductoServiceImpl;
import com.kinesoft.zero.servicesImpl.UsuarioServiceImpl;

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
