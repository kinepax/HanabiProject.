package com.kinesoft.zero.server;

import java.sql.Connection;
import java.sql.DriverManager;

import com.kinesoft.zero.components.WindowsView;



public final  class Server {

	public static final  String  url ="jdbc:mysql://127.0.0.1/hanabi";

	public static final  String  usuario ="root";
	public static final  String  contraseña ="";



	

	//Direccion de base datos en la nube
/*
	public static final  String  url ="jdbc:mysql://35.223.236.33/hanabi";

	public static final  String  usuario ="root";
	public static final  String  contraseña ="s1stema5";

*/

	public static final  String  driver ="com.mysql.cj.jdbc.Driver";


	public Server() {
		
		
	}
	
	public static Connection conectar() {

		Connection con= null;
		
		try {
			
			Class.forName(driver).getDeclaredConstructor().newInstance();
			//con= DriverManager.getConnection(url,usuario,contraseña);
			
			//Inplementacion de base de datos en nube
			con= DriverManager.getConnection(url,usuario,contraseña);

			if(con!=null) {
				
				System.out.println("Conectado correctamente ala base de datos");
				return con;
			}
			
		} catch (Exception e) {
			
			WindowsView alerta = new WindowsView();
			alerta.setHeight("10px");
			alerta.setWidth("10px");
			alerta.onError("Fallo la conxion con labase de datos");
			System.out.println(e.getMessage());
		}
		System.out.println("Pase aqui");

		return con;
		
	}
	
	
}
