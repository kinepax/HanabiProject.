package com.kinesoft.zero.views.serie;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Serie;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class SeriesUI extends WindowsView {

    GridView<Serie> grid = new GridView<>(Serie.class,true);

    Button btnAgregar = new Button("Agregar");
    Button btnEditar = new Button("Editar");
    Button btnEliminar = new Button("Eliminar");
    Button btnRefrescar = new Button("Refrescar");

    HorizontalLayout pnlObciones = new HorizontalLayout();
    HorizontalLayout pnlFiltros 	= new HorizontalLayout();

    TextField txtSerie= new TextField("Serie");


    public SeriesUI() {
        initStyles();
        initData();
        initListener();
    }

    public void initStyles(){

        pnlFiltros.setAlignItems(Alignment.BASELINE);

    }
    public void initData() {
        pnlObciones.add(btnAgregar, btnEditar, btnEliminar);
        pnlFiltros.add(txtSerie,btnRefrescar);

        grid.setColumns("id", "serie", "usuario","fecha_hora");

        add(pnlObciones,pnlFiltros, grid);

    }
    public void initListener() {

        btnAgregar      .addClickListener(e -> onadd());
        btnRefrescar     .addClickListener(e -> onRefrescar());
        btnEditar       .addClickListener(e -> onEditar());
        btnEliminar     .addClickListener(e -> onEliminar());
        txtSerie		.addKeyUpListener(Key.ENTER, e->onRefrescar());

    }

    public abstract String onadd();

    public abstract String onRefrescar();

    public abstract void onEditar();

    public abstract String onEliminar();

}
