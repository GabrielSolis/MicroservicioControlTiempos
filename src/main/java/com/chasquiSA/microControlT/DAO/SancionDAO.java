package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.chasquiSA.microControlT.Dominio.Sancion;

@Repository
public class SancionDAO {
	public int registroSancion(Sancion sancion) throws Exception{
		int respuesta = 0;
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iSancion(?,?,?,?,?,?,?,?,?)}");
			cstm.setInt(1,sancion.getCodigoRegistroUnidad());
			cstm.setString(2,sancion.getJustificacion());
			cstm.setString(3,sancion.getMotivo());
			cstm.setString(4,sancion.getEstado());
			cstm.setDouble(5,sancion.getTiempoRetraso());
			cstm.setDouble(6,sancion.getSancionTiempo());
			cstm.setDouble(7,sancion.getSancionMonto());
			cstm.setBoolean(8,sancion.isVigencia());
			cstm.setInt(9,sancion.getDetalleTarjeta().getCodigo());
			cstm.execute();
			respuesta = 1;
			conexion.close();
			return respuesta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public Sancion obtenerSancionDetalleTarjeta(int  codigoDetalleTarjeta) throws Exception{
		Sancion sancion = new Sancion();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lSancionDetalleTarjeta(?)}");
			cstm.setInt(1,codigoDetalleTarjeta);
			ResultSet rs;
			rs = cstm.executeQuery();
			sancion.setCodigo(rs.getInt("p_codigo"));
			sancion.setEstado(rs.getString("p_estado"));
			sancion.setCodigoRegistroUnidad(rs.getInt("p_codigoRegistroUnidad"));
			sancion.setSancionTiempo(rs.getDouble("p_sancionTiempo"));
			sancion.setTiempoRetraso(rs.getDouble("p_tiempoRetraso"));
			conexion.close();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		return sancion;
	}
}
