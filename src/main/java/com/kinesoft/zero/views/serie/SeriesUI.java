package com.kinesoft.zero.views.serie;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Producto;
import com.kinesoft.zero.model.Serie;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class SeriesUI extends WindowsView {

    GridView<Serie> grid = new GridView<>(Serie.class,true);

    Button btnAgregar = new Button("Agregar");
    Button btnEditar = new Button("Editar");
    Button btnEliminar = new Button("Eliminar");
    Button btnRefrescar = new Button("Refrescar");

    HorizontalLayout pnlObciones = new HorizontalLayout();


    public SeriesUI() {

        initData();
        initListener();
    }


    public void initData() {
        pnlObciones.add(btnAgregar, btnEditar, btnEliminar, btnRefrescar);

        grid.setColumns("id", "serie", "usuario","fecha_hora");

        add(pnlObciones, grid);

    }
    public void initListener() {

        btnAgregar.addClickListener(e -> onadd());
        btnRefrescar.addClickListener(e -> onRefrescar());
        btnEditar.addClickListener(e -> onEditar());
        btnEliminar.addClickListener(e -> onEliminar());

    }

    public abstract String onadd();

    public abstract String onRefrescar();

    public abstract void onEditar();

    public abstract String onEliminar();

}
