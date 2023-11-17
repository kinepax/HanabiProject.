package com.kinesoft.zero.views.serie;

import com.kinesoft.zero.components.WindowsView;
import com.kinesoft.zero.model.Serie;
import com.kinesoft.zero.servicesImpl.SerieServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeriesView extends SeriesUI{


    List<Serie> series= new ArrayList<>();
    Serie entity= new Serie();
    SerieServiceImpl serieServiceImpl = new SerieServiceImpl();

    public SeriesView(){
        iniData();

    }

    public void iniData() {
        onRefrescar();
    }



    @Override
    public String onadd() {
        SerieView view = new SerieView();
        view.setSizeFull();
        view.showDialog(view);

        return null;

    }

    @Override
    public String onRefrescar() {
        String serie=  txtSerie.getValue();
        try {
            series=serieServiceImpl.listarSeries(serie);
            System.out.println(series.size());
            grid.setItems(series);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onEditar() {
        entity=grid.getValue();
        if(entity==null) {
            System.out.print("Entre aqui en el null");
            WindowsView view= new WindowsView();
            view.onError("Debes seleccionar un item para editar");
            view.showDialog(view);
            view.setSizeFull();

            return ;
        }



        SerieView view = new SerieView(entity);
        view.setSizeFull();
        view.showDialog(view);






    }

    @Override
    public String onEliminar() {
        return null;
    }
}
