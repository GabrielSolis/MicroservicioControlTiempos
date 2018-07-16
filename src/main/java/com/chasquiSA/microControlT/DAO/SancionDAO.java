package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Sancion;
import com.chasquiSA.microControlT.Dominio.Tarjeta;
import com.chasquiSA.microControlT.util.TarjetaSancionDTO;

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
			if(rs.next()) {
				sancion.setCodigo(rs.getInt("p_codigo"));
				sancion.setEstado(rs.getString("p_estado"));
				sancion.setCodigoRegistroUnidad(rs.getInt("p_codigoRegistroUnidad"));
				sancion.setSancionTiempo(rs.getDouble("p_sancionTiempo"));
				sancion.setTiempoRetraso(rs.getDouble("p_tiempoRetraso"));
			}
			
			conexion.close();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		return sancion;
	}
	
	public List<TarjetaSancionDTO> obtenerSancionesUnidad(String fechaInicio,String fechaFin, int codigoRU,String opcion)throws Exception{
		List<TarjetaSancionDTO> listaSancionesTarjeta = new ArrayList<>();
		List<DetalleTarjeta> listaDetalle = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lSancionPorUnidad(?,?,?,?)}");
			cstm.setInt(1,codigoRU);
			cstm.setString(2,fechaInicio);
			cstm.setString(3,fechaFin);
			cstm.setString(4,opcion);
			ResultSet rs;
			rs = cstm.executeQuery();
			while(rs.next()) {
				TarjetaSancionDTO tarjetaSancion = new TarjetaSancionDTO();
				Tarjeta tarjeta  = new Tarjeta();
				DetalleTarjeta detalle = new DetalleTarjeta();
				Sancion sancion = new Sancion();
				tarjeta.setCodigo(rs.getInt("pr_iCodigoTarjeat"));
				tarjeta.setFecha(rs.getString("pr_fechaTarjeta"));
				detalle.setNombreRuta(rs.getString("p_nombreRuta"));
				detalle.setNumeroVuelta(rs.getInt("pr_numeroVuelta"));
				detalle.setCodigoRuta(rs.getInt("pr_codigoRuta"));
				sancion.setMotivo(rs.getString("p_motivoSancion"));
				sancion.setTiempoRetraso(rs.getDouble("pr_tiempoRetraso"));
				sancion.setSancionTiempo(rs.getDouble("p_sancionTiempo"));
				sancion.setJustificacion(rs.getString("pr_justifacionRetraso"));
				tarjetaSancion.setTarjeta(tarjeta);
				listaDetalle.add(detalle);
				tarjetaSancion.getTarjeta().setListaDetalles(listaDetalle);
				tarjetaSancion.setSancion(sancion);
				listaSancionesTarjeta.add(tarjetaSancion);
				listaDetalle.clear();
			}
			return listaSancionesTarjeta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
