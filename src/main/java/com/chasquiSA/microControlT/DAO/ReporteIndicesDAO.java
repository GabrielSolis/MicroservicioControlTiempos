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
			cstm.setString(1,fecha);
			cstm.setString(2,opcion);
			ResultSet rs;
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				List<DetalleTarjeta> listaDetalles = new ArrayList<>();
				detalle.setCodigoRuta(rs.getInt(1));
				tarjeta.setCodigo(rs.getInt(2));
				listaDetalles.add(detalle);
				tarjeta.setListaDetalles(listaDetalles);
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
