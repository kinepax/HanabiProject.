package com.kinesoft.zero.views.pedido;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Pedido;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class PedidosUI extends WindowsView {
	GridView<Pedido> grid = new GridView<>(Pedido.class);
	Button btnAgregar = new Button("Agregar");
	Button btnEditar = new Button("Editar");
	Button btnEliminar = new Button("Eliminar");
	Button btnRefrescar = new Button("Refrescar");
	Button btnImprimirPedido = new Button("Imprimir Pedido");
	HorizontalLayout pnlObciones = new HorizontalLayout();


	public PedidosUI() {
		initData();
		initListener();
	}

	public void initData() {
		pnlObciones.add(btnAgregar, btnEditar, btnEliminar, btnRefrescar,btnImprimirPedido);
		grid.addComponentColumn(pedido -> {
			Checkbox chxSeleccion=new Checkbox();
			chxSeleccion.addValueChangeListener(e->{

				Boolean estado= e.getValue();
				if(estado){
					grid.select(pedido);
					onCheckSeleccion();
				}
				else{
					System.out.println("no esta cliqueado");
				}


			});

			return chxSeleccion;
		});
		//grid.setColumns("id", "cliente", "total", "estado","fecha_hora");

		grid.addCol(Pedido::getId,"id");
		grid.addCol(Pedido::getCliente,"cliente");
		grid.addCol(Pedido::getEstado,"estado");
		grid.addCol(Pedido::getFecha_hora,"Fecha y Hora");

		add(pnlObciones, grid);

	}

	public void initListener() {

		btnAgregar.addClickListener(e -> onadd());
		btnRefrescar.addClickListener(e -> onRefrescar());
		btnEditar.addClickListener(e -> onEditar());
		btnEliminar.addClickListener(e -> onEliminar());
		btnImprimirPedido.addClickListener(e -> onImprimirPedido());

	}

	public abstract void onCheckSeleccion();


	public abstract void onadd();

	public abstract void onRefrescar();

	public abstract void onEditar();

	public abstract void onEliminar();

	public abstract void onImprimirPedido();

}
