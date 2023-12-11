package com.kinesoft.zero.views.pedido;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.dto.PedidoDTO;
import com.kinesoft.zero.model.Mesa;
import com.kinesoft.zero.model.Pedido;
import com.kinesoft.zero.servicesImpl.MesaServiceImpl;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class PedidosUI extends WindowsView  {
	public GridView<PedidoDTO> grid = new GridView<>(PedidoDTO.class);
	Button btnAgregar = new Button("Agregar");
	Button btnEditar = new Button("Editar");
	Button btnEliminar = new Button("Eliminar");
	 Button btnRefrescar = new Button("Refrescar");
	Button btnImprimirPedido = new Button("Imprimir Pedido");
	public Button btnOnSeleccionar = new Button("Seleccionar");
	HorizontalLayout pnlObciones = new HorizontalLayout();

	HorizontalLayout pnlFiltros = new HorizontalLayout();
	ComboBox<Mesa> chxboxMesa = new ComboBox<>("Mesa");
	TextField txtCliente = new TextField("Cliente");

	ComboBox<String>chboxEstado= new ComboBox<>("Estados");

	DatePicker fechaInicial = new DatePicker("Fecha Inicial");
	DatePicker fechaFinal = new DatePicker("Fecha Final");

    Mesa mesa = new Mesa(0,"TODAS");




	public PedidosUI() {
		initStyles();
		initData();
		initListener();
	}


	public void initStyles(){

		pnlFiltros.setAlignItems(Alignment.BASELINE);
	}
	public void initData() {
		btnEliminar.setEnabled(false);
		List<Mesa> listaMesas= new ArrayList<>();
		fechaInicial.setValue(LocalDate.now());
		fechaFinal.setValue(LocalDate.now());

		chxboxMesa.setItems(new Mesa(0,"TODAS"));


		chboxEstado.setItems("PENDIENTE","ATENDIDO","FACTURADO","TODOS");



		try {
			listaMesas=MesaServiceImpl.listarMesas(null);
			listaMesas.add(mesa);

			chxboxMesa.setItems(listaMesas);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		chboxEstado.setValue("TODOS");
		chxboxMesa.setValue(mesa);

		pnlObciones.add(btnAgregar, btnEditar, btnEliminar,btnImprimirPedido,btnOnSeleccionar);
		pnlFiltros.add(fechaInicial,fechaFinal, chxboxMesa,txtCliente,chboxEstado,btnRefrescar);

	/*	grid.addComponentColumn(pedido -> {
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

	 */
		//grid.setColumns("id", "cliente", "total", "estado","fecha_hora");

		grid.setSelectionMode(Grid.SelectionMode.MULTI);

		grid.addCol(PedidoDTO::getId,"id");
		grid.addCol(PedidoDTO::getMesa,"Mesa");
		grid.addCol(PedidoDTO::getCliente,"Cliente");
		grid.addCol(PedidoDTO::getEstado,"Estado");
		grid.addCol(PedidoDTO::getTotal,"Total");
		grid.addCol(PedidoDTO::getFecha_hora,"Fecha y Hora");

		grid.addComponentColumn(pedidoDTO -> {
		return onOperacion(pedidoDTO);

		});

		add(pnlObciones,pnlFiltros, grid);

	}

	public void initListener() {

		btnAgregar.addClickListener(e -> onadd());
		btnRefrescar.addClickListener(e -> onRefrescar());
		btnEditar.addClickListener(e -> onEditar());
		btnEliminar.addClickListener(e -> onEliminar());
		btnImprimirPedido.addClickListener(e -> onImprimirPedido());
		btnOnSeleccionar.addClickListener(e-> onSeleccionPedido());

		fechaInicial.addValueChangeListener(e -> onRefrescar());
		fechaFinal.addValueChangeListener(e -> onRefrescar());

		chxboxMesa.addValueChangeListener(e -> onRefrescar());
		chboxEstado.addValueChangeListener(e -> onRefrescar());
		txtCliente.addKeyUpListener(Key.ENTER, e -> onRefrescar());

	}

	public abstract void onCheckSeleccion();

	public abstract Button onOperacion(PedidoDTO pedidoDTO);


	public abstract void onadd();

	public abstract void onRefrescar();

	public abstract void onEditar();

	public abstract void onEliminar();

	public abstract void onImprimirPedido();

	public abstract Pedido onSeleccionPedido();
}
