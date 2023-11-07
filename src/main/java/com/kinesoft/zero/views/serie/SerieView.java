package com.kinesoft.zero.views.serie;

import com.kinesoft.zero.model.Serie;
import com.kinesoft.zero.model.Usuario;
import com.kinesoft.zero.servicesImpl.SerieServiceImpl;

public class SerieView extends  SerieUI{

    Serie serie = new Serie();
    SerieServiceImpl serie_service= new SerieServiceImpl();

    public boolean save;


    public SerieView(Serie entity) {
        this.save=entity==null;
        System.out.println("El valor de save es "+save);
        initData(entity);


    }

    public SerieView() {
        this(null);
    }



    public void initData(Serie serie) {
        if(save) {
            return;

        }
        txtId.setText(""+serie.getId());
        txtSerie.setValue(serie.getSerie());
        this.serie=serie;



    }

    @Override
    public String onCerrar() {
        this.closeDialog();
        return null;
    }

    @Override
    public String onSave() {

        serie.setSerie(txtSerie.getValue());
        serie.setUsuario(new Usuario(1,"Paulo","kinepax","123456"));




        if(!save) {
         //   serie.setId(Integer.parseInt( txtId.getText()));
            serie.setSerie_anterior(serie.getSerie_nueva());
            serie.setSerie_nueva(txtSerie.getValue());
            serie.setSerie(txtSerie.getValue());


            System.out.println(serie.getSerie_anterior()+"el valor anterior era ");
            System.out.println(serie.getSerie_nueva()+"el valor nuevo era ");

            serie_service.edit(serie);
            this.closeDialog();

        }
        else {

            serie.setSerie_anterior(txtSerie.getValue());
            serie.setSerie_nueva(txtSerie.getValue());
            serie_service.save(serie);
            this.closeDialog();


        }



        return null;
    }
}
