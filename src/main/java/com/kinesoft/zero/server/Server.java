package com.kinesoft.zero.server;

import java.sql.Connection;
import java.sql.DriverManager;

import com.kinesoft.zero.components.WindowsView;



public final  class Server {
	public static final  String  url ="jdbc:mysql://127.0.0.1/hanabi";

	public static final  String  usuario ="root";
	public static final  String  contraseña ="";

	public static final  String  driver ="com.mysql.cj.jdbc.Driver";
	
	
	

	//Direccion de base datos en la nube
	//public static final  String  urlExterna ="jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10436906";

//	public static final  String  usuarioExterno ="sql10436906";
	//public static final  String  contraseñaExterna ="SkNnQ24YSN";

	
	
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
