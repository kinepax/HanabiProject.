package com.kinesoft.zero.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.function.ValueProvider;

import java.util.ArrayList;
import java.util.List;


public class GridView<T>  extends Grid <T>{
    private List<T> list = new ArrayList<>();

//comentario
public GridView() {
	
	
	super();
}

	public T  getValue() {
		if(this.getSelectionModel().getFirstSelectedItem().isEmpty()) {
			return null;
			
		}
		return this.getSelectionModel().getFirstSelectedItem().get();
		
		
	}
	
	public List<T> getList() {
		
		return list;
	}
	
    public GridView(Class<T> beanType, boolean autoCreateColumns) {
        this();
        configureBeanType(beanType, autoCreateColumns);
    }
	  public GridView(Class<T> beanType) {
		  
	        this(beanType, false);
	       
	    }
	  

	    public Grid.Column<T> addCol(ValueProvider<T, ?> valueProvider, String header) {
	        Grid.Column<T> column = this.addColumn(valueProvider);
	        column.setHeader(header);
	        column.setAutoWidth(true);
	        column.setSortable(true);
	        column.setResizable(true);
	        return column;
	    }
	    

/*
	    public Grid.Column<T> addCol(ValueProvider<T, ?> valueProvider,Object object, String header) {
	        Grid.Column<T> column = this.addColumn(valueProvider);
	        column.setHeader(header);
	        column.setAutoWidth(true);
	        column.setSortable(true);
	        column.setResizable(true);
	        column.setFooter(object.toString());
	        return column;
	    }
	  
	*/

		public  Grid.Column<T> addCol (ValueProvider<T, ?> valueProvider, Object obj, String header) {
	        Grid.Column<T> column = this.addColumn(valueProvider);
	        
	        System.out.println("Prueba de entra a la creacion");
	        column.setHeader(header);
	        column.setAutoWidth(true);
	        column.setSortable(true);
	        column.setResizable(true);
	        System.out.println("La columna que inicia todo "+obj.toString());
	        
	       // column.setFooter(footer);
	        //System.out.println("El footer es "+footer);
	        return column;			
		}
		/*
		public  Grid.Column<T> addCol (ValueProvider<T, ?> valueProvider,ValueProvider<T, ?> valueProvider2,  String header) {
	        Grid.Column<T> column = this.addColumn(valueProvider);
	        Grid.Column<T> footer = this.addColumn(valueProvider2);
	        
	       String footer2 =valueProvider2.getClass().toString();
	        column.setHeader(header);
	        column.setAutoWidth(true);
	        column.setSortable(true);
	        column.setResizable(true);
	        column.setFooter(footer2);
	        System.out.println("AQUI SOY EL FOOTER "+footer2);
	        return column;			
		}
		
		*/

		public void setlist(List<T> list) {
			this.list.clear();
			this.list.addAll(list);
			this.setItems(list);
			
			
			// TODO Auto-generated method stub
			
		}

}
