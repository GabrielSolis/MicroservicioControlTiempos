package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;


public class SancionDAO {
	public int registroSancion() throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iSancion()}");
			return 0;
		}catch(Exception e) {
			throw e;
		}
	}
}
