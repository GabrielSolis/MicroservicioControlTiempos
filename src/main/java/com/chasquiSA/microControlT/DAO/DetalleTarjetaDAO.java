package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Tarjeta;
import com.chasquiSA.microControlT.Dominio.TiemposDetalleTarjeta;


public class DetalleTarjetaDAO {
	
	public int registroTarjeta(Tarjeta tarjeta)throws Exception {
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iTarjeta(?,?,?,?,?,?)}");
			ResultSet rs,rs1;
			int codigoTarjeta,codigoDetalleTarjeta;
			cstm.setString(1,tarjeta.getNumero());
			cstm.setString(2,tarjeta.getFecha());
			cstm.setString(3,tarjeta.getUsuario());
			cstm.setString(4,tarjeta.getEstado());
			cstm.setInt(5,tarjeta.getCodigoRegistroUnidad());
			cstm.setBoolean(6,tarjeta.isVigencia());
			rs = cstm.executeQuery();
			rs.next();
			codigoTarjeta = rs.getInt(1);
			Logger log = Logger.getLogger("Logger de Ejemplo");
			log.info(codigoTarjeta);
	
			
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?,?)}");
			cstm1.setInt(1,codigoTarjeta);
			cstm1.setString(2,tarjeta.getListaDetalles().get(0).getEstado());
			cstm1.setInt(3, tarjeta.getListaDetalles().get(0).getNumeroVuelta());
			cstm1.setInt(4,tarjeta.getListaDetalles().get(0).getCodigoRuta());
			cstm1.setString(5,tarjeta.getListaDetalles().get(0).getHoraInicio());
			cstm1.setString(6, tarjeta.getListaDetalles().get(0).getNombreRuta());
			cstm1.setBoolean(7,tarjeta.getListaDetalles().get(0).isVigencia());
			rs1 = cstm1.executeQuery();
			rs1.next();
			codigoDetalleTarjeta = rs1.getInt(1);
			log.info(codigoDetalleTarjeta);
			//CallableStatement cstm2 = conexion.prepareCall("{call pr_iTiemposDetalleTarjeta(?,?,?,?}");
		
			/*for (TiemposDetalleTarjeta tiempo : tarjeta.getListaDetalles().get(0).getListaTiemposDetalleTarjeta()) {
				System.out.println(codigoDetalleTarjeta);
				
				cstm2.setInt(1,codigoDetalleTarjeta);
				cstm2.setInt(2,tiempo.getCodigoTiempoEstablecido());
				cstm2.setInt(3,tiempo.getMinutosTolerancia());
				cstm2.setString(4,tiempo.getHoraControl());				
				cstm2.execute();
			}*/
			Conexion.cerrarConexion();
			return codigoTarjeta;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void registroDetalleTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			ResultSet rs;
			int codigoDetalleTarjeta;
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?,?)}");
				cstm1.setInt(1,tarjeta.getCodigo());
				cstm1.setString(2,tarjeta.getListaDetalles().get(0).getEstado());
				cstm1.setInt(3, tarjeta.getListaDetalles().get(0).getNumeroVuelta());
				cstm1.setInt(4,tarjeta.getListaDetalles().get(0).getCodigoRuta());
				cstm1.setString(5,tarjeta.getListaDetalles().get(0).getHoraInicio());
				cstm1.setString(6, tarjeta.getListaDetalles().get(0).getNombreRuta());
				cstm1.setBoolean(7,tarjeta.getListaDetalles().get(0).isVigencia());
				rs = cstm1.executeQuery();
				rs.next();
				codigoDetalleTarjeta = rs.getInt(1);
				CallableStatement cstm2 = conexion.prepareCall("{call pr_iTiempoDetalleTarjeta(?,?,?,?)}");
				for (TiemposDetalleTarjeta tiempo : tarjeta.getListaDetalles().get(0).getListaTiemposDetalleTarjeta()) {
					cstm2.setInt(1,codigoDetalleTarjeta);
					cstm2.setInt(2,tiempo.getCodigoTiempoEstablecido());
					cstm2.setInt(3,tiempo.getMinutosTolerancia());
					cstm2.setString(4,tiempo.getHoraControl());
					cstm2.execute();
				}
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}
	}
	public boolean verificarTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTarjeta(?,?)}");
			ResultSet rs;
			cstm.setInt(1,tarjeta.getCodigoRegistroUnidad());
			cstm.setString(2, tarjeta.getFecha());
			rs = cstm.executeQuery();
			if(rs.next()) {
				Conexion.cerrarConexion();
				return true;
			}
			Conexion.cerrarConexion();
			return false;
		}catch(Exception e) {
			throw e;
		}
	}
	public List<Tarjeta> listarTarjetasEstado(String desdeFecha , String hastaFecha , String estado)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			List<Tarjeta> listaTarjetas = new ArrayList<>();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTarjetaEstado(?,?,?)}");
			ResultSet rs;
			cstm.setString(1, desdeFecha);
			cstm.setString(2,hastaFecha);
			cstm.setString(3,estado);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("p_codigo"));
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRu"));
				tarjeta.setNumero(rs.getString("p_numeroTarjeta"));
				tarjeta.setFecha(rs.getString("p_fechaTarjeta"));
				tarjeta.setMensaje(rs.getString("p_mensaje"));
				tarjeta.setEstado(rs.getString("p_estado"));
				listaTarjetas.add(tarjeta);
			}
			Conexion.cerrarConexion();
			return listaTarjetas;
		}catch(Exception e) {
			throw e;	
		}
	}
	
	
	public List<Tarjeta> listarTarjetas(int p_codigoRU, String fecha) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			List<Tarjeta> listaTarjetas = new ArrayList<>();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTarjeta(?,?)}");
			ResultSet rs;
			cstm.setInt(1,p_codigoRU);
			cstm.setString(2, fecha);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("p_codigo"));
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRu"));
				tarjeta.setNumero(rs.getString("p_numeroTarjeta"));
				tarjeta.setFecha(rs.getString("p_fechaTarjeta"));
				tarjeta.setMensaje("p_mensaje");
				tarjeta.setEstado(rs.getString("p_estado"));
				listaTarjetas.add(tarjeta);
			}
			Conexion.cerrarConexion();
			return listaTarjetas;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<Tarjeta> listarTarjetasFecha(int p_codigo, String desdeFecha , String hastaFecha) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			List<Tarjeta> listaTarjetas = new ArrayList<>();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTarjetaFechas(?,?,?)}");
			ResultSet rs;
			cstm.setInt(1,p_codigo);
			cstm.setString(2, desdeFecha);
			cstm.setString(3,hastaFecha);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("p_codigo"));
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRu"));
				tarjeta.setNumero(rs.getString("p_numeroTarjeta"));
				tarjeta.setFecha(rs.getString("p_fechaTarjeta"));
				tarjeta.setMensaje("p_mensaje");
				tarjeta.setEstado(rs.getString("p_estado"));
				listaTarjetas.add(tarjeta);
			}
			Conexion.cerrarConexion();
			return listaTarjetas;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public Tarjeta obtenerTarjeta(int codigo)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lTarjeta(?)}");
			ResultSet rs;
			Tarjeta tarjeta = new Tarjeta();
			cstm.setInt(1,codigo);
			rs = cstm.executeQuery();
			if(rs.next()){
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRegistroUnidad"));
				tarjeta.setFecha(rs.getString("p_fecha"));
				tarjeta.setEstado(rs.getString("p_estado"));
				tarjeta.setVigencia(rs.getBoolean("p_vigencia"));
			}
			
			Conexion.cerrarConexion();
			return tarjeta;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void darBajaTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_eTarjeta(?,?,?)}");
			cstm.setInt(1,tarjeta.getCodigo());
			cstm.setString(2, tarjeta.getMensaje());
			cstm.setString(3, tarjeta.getUsuario());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void darBajaDetalleTarjeta(DetalleTarjeta detalleTarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_eDetalleTarjeta(?,?,?)}");
			cstm.setInt(1,detalleTarjeta.getCodigoTarjeta());
			cstm.setInt(2, detalleTarjeta.getCodigoRuta());
			cstm.setString(3, detalleTarjeta.getMensaje());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<DetalleTarjeta> obtenerDetalleTarjeta(int codigoTarjeta) throws Exception{
		List<DetalleTarjeta> listaDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liNumeroVueltaTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoTarjeta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
				detalleTarjeta.setCodigoRuta(rs.getInt("codigoRuta"));
				detalleTarjeta.setCodigoTarjeta(rs.getInt("codigoTarjeta"));
				detalleTarjeta.setNombreRuta(rs.getString("nombreRuta"));
				listaDetalleTarjeta.add(detalleTarjeta);
			}
			Conexion.cerrarConexion();
			return listaDetalleTarjeta;
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<DetalleTarjeta> obtenerTiempoDetalleTarjeta(int codigoTarjeta,int codigoRuta,int numeroVuelta) throws Exception{
		List<DetalleTarjeta> listaDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liDetalleTarjeta(?,?,?)}");
			ResultSet rs;
			cstm.setInt(1,codigoTarjeta);
			cstm.setInt(2,codigoRuta);
			cstm.setInt(3,numeroVuelta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
				detalleTarjeta.setCodigoTarjeta(codigoTarjeta);
				detalleTarjeta.setHoraInicio(rs.getString("p_horaInicio"));
				listaDetalleTarjeta.add(detalleTarjeta);
			}
			Conexion.cerrarConexion();
			return listaDetalleTarjeta;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<DetalleTarjeta> obtenerUltimoDetalleTarjeta(int codigoTarjeta)throws Exception{
		List<DetalleTarjeta> listaDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liUltimaRutaTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoTarjeta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
				detalleTarjeta.setCodigoTarjeta(rs.getInt("p_codigoTarjeta"));
				detalleTarjeta.setCodigo(rs.getInt("p_codigoDetalle"));
				detalleTarjeta.setCodigoRuta(rs.getInt("p_codigoRuta"));
				detalleTarjeta.setHoraInicio(rs.getString("p_horaInicio"));
				listaDetalleTarjeta.add(detalleTarjeta);
			}
			Conexion.cerrarConexion();
			return listaDetalleTarjeta;
		}catch(Exception e) {
			throw e;
		}
	}
	/*
	public void registrarTiemposGPS(List<DetalleTarjeta> listaDetalle) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aTiemposGPSDetalleTarjeta(?,?,?)}");
			
			for (DetalleTarjeta detalleTarjeta : listaDetalle) {
				if(detalleTarjeta.getHoraGPS() != null) {
					cstm.setInt(1,detalleTarjeta.getCodigo());
					cstm.setString(2,detalleTarjeta.getHoraGPS());
					cstm.setDouble(3,detalleTarjeta.getDiferencia());
					cstm.execute();
				}
				
			}
			Conexion.cerrarConexion();
			
		}catch(Exception e) {
			throw e;
		}
	}*/
}
