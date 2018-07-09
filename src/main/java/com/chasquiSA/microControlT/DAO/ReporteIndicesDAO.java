package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Tarjeta;

@Repository
public class ReporteIndicesDAO {
	public List<Tarjeta> listarIndicesRescorridoRutas(String fecha, String opcion) throws Exception{
		List<Tarjeta> listaTarjetas = new ArrayList<>();
		DetalleTarjeta detalle = new DetalleTarjeta();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liIndicadorRutas(?,?)}");
			ResultSet rs;
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("count"));
				detalle.setCodigoRuta(rs.getInt("codigoRuta"));
				listaTarjetas.add(tarjeta);
			}
			Conexion.cerrarConexion();
			return listaTarjetas;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
