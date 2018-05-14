package com.chasquiSA.microControlT.DAO;

//import java.net.URI;
//import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Conexion {
	private static Connection conexion=null;	
	public static Connection getConexion() throws Exception{
		String urlDataBase = "jdbc:postgresql://localhost:5432/chasquiSA";
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(urlDataBase,"postgres","postgres");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
}
