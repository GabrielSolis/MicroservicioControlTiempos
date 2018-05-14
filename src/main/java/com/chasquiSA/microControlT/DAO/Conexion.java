package com.chasquiSA.microControlT.DAO;

//import java.net.URI;
//import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Conexion {
	private static Connection conexion=null;	
	/*public static Connection getConexion() throws Exception{
		String urlDataBase = "jdbc:postgresql://localhost:5432/chasquiSA";
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(urlDataBase,"postgres","postgres");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}*/
	public static Connection getConexion() throws Exception{
		//String urlDataBase = "jdbc:postgresql://localhost:5432/ChasquiSA";
		//String urlDataBase = "jdbc:postgresql://localhost/ChasquiSA";
		String urlDataBase = "jdbc:postgresql://ec2-23-23-130-158.compute-1.amazonaws.com:5432/dc2309nncb82j0";
		try {
			Class.forName("org.postgresql.Driver");
			//conexion = DriverManager.getConnection(urlDataBase,"postgres","1234");
			conexion = DriverManager.getConnection(urlDataBase,"kfynnkzoefhnpo","288b39e08ea37d8f4f187bdee680600b684de8902940580781fe1ab2fe8cbafe");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
}
