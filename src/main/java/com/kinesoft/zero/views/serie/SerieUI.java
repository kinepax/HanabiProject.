package com.kinesoft.zero.views.serie;

import com.kinesoft.zero.components.WindowsView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class SerieUI extends WindowsView {

    Button btnGrabar= new Button("Grabar");
    Button btnCerrar= new Button("Cerrar");
    TextField txtSerie= new TextField("Serie");

    Text txtId=new Text("");


    HorizontalLayout pnlObciones= new HorizontalLayout();

    public SerieUI() {

        initData();
    }

    public void initData() {
        pnlObciones.add(btnGrabar,btnCerrar);
        add(txtSerie,pnlObciones);

        intiListener();
        initStyles();
    }

    public void initStyles() {

        this.setSizeFull();

    }
    public void intiListener(){
        btnCerrar.addClickListener(e->onCerrar());
        btnGrabar.addClickListener(e->onSave());

    }

    public    abstract    String onCerrar();
    public    abstract    String onSave();
}
