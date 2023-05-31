package com.kinesoft.zero.views.mesa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.servicesImpl.MesaServiceImpl;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

//@Route(value = "mesas", layout = MainLayout.class)
public class MesasView extends MesasUI {
	
	List<Mesa> listaDeMesas 			= new ArrayList<>();
	Mesa mesa							= new Mesa() ;	

	public MesasView() {
		iniData();

	}

	public void iniData() {
		onRefrescar();
	}
	
	

	@Override
	public void onadd() {
		MesaView vistaMesa = new MesaView();
		vistaMesa.setSizeFull();
		vistaMesa.showDialog(vistaMesa);

	}

	@Override
	public void onRefrescar() {
		try {
			listaDeMesas = MesaServiceImpl.listarMesas(null);
			grid.setItems(listaDeMesas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onEditar() {
		mesa = grid.getValue();
		MesaView vistaMesa = new MesaView(mesa);
		vistaMesa.setSizeFull();
		vistaMesa.showDialog(vistaMesa);

		System.out.println(mesa.getNombre());
	}

	@Override
	public void onEliminar() {
		mesa = grid.getValue();
		try {
			MesaServiceImpl.eliminar(mesa);

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
