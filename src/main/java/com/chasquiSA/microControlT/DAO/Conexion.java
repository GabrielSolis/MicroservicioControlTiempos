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
		String urlDataBase = "jdbc:postgresql://ec2-174-129-28-38.compute-1.amazonaws.com:5432/d7ite1rtejj9da";
		try {
			Class.forName("org.postgresql.Driver");
			//conexion = DriverManager.getConnection(urlDataBase,"postgres","1234");
			conexion = DriverManager.getConnection(urlDataBase,"epnvssgbxirdfv","09588d1b6b0a2b62a99276551999d9a5e9bebe9f3c2a04b0e926a11066b51289");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
}
