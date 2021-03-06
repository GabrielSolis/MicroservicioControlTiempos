package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Tarjeta;
import com.chasquiSA.microControlT.Dominio.TiemposDetalleTarjeta;

@Repository
public class DetalleTarjetaDAO {
	@Transactional
	public int registroTarjeta(Tarjeta tarjeta)throws Exception {
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iTarjeta(?,?,?,?,?)}");
			ResultSet rs,rs1;
			int codigoTarjeta,codigoDetalleTarjeta;
			cstm.setString(1,tarjeta.getNumero());
			cstm.setString(2,tarjeta.getFecha());
			cstm.setInt(3,tarjeta.getCodigoUsuario());
			cstm.setInt(4,tarjeta.getCodigoRegistroUnidad());
			cstm.setBoolean(5,tarjeta.isVigencia());
			rs = cstm.executeQuery();
			rs.next();
			codigoTarjeta = rs.getInt(1);
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?)}");
			cstm1.setInt(1,codigoTarjeta);
			cstm1.setInt(2, tarjeta.getListaDetalles().get(0).getNumeroVuelta());
			cstm1.setInt(3,tarjeta.getListaDetalles().get(0).getCodigoRuta());
			cstm1.setString(4,tarjeta.getListaDetalles().get(0).getHoraInicio());
			cstm1.setString(5, tarjeta.getListaDetalles().get(0).getNombreRuta());
			cstm1.setBoolean(6,tarjeta.getListaDetalles().get(0).isVigencia());
			rs1 = cstm1.executeQuery();
			rs1.next();
			codigoDetalleTarjeta = rs1.getInt(1);
			CallableStatement cstm2 = conexion.prepareCall("{call pr_iTiemposDetalleTarjeta(?,?,?,?)}");
		
			for (TiemposDetalleTarjeta tiempo : tarjeta.getListaDetalles().get(0).getListaTiemposDetalleTarjeta()) {
				cstm2.setInt(1,codigoDetalleTarjeta);
				cstm2.setInt(2,tiempo.getCodigoTiempoEstablecido());
				cstm2.setInt(3,tiempo.getMinutosTolerancia());
				cstm2.setString(4,tiempo.getHoraControl());				
				cstm2.execute();
			}
			Conexion.cerrarConexion();
			return codigoTarjeta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	@Transactional
	public void registroDetalleTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			ResultSet rs;
			int codigoDetalleTarjeta;
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?)}");
				cstm1.setInt(1,tarjeta.getCodigo());
				cstm1.setInt(2, tarjeta.getListaDetalles().get(0).getNumeroVuelta());
				cstm1.setInt(3,tarjeta.getListaDetalles().get(0).getCodigoRuta());
				cstm1.setString(4,tarjeta.getListaDetalles().get(0).getHoraInicio());
				cstm1.setString(5, tarjeta.getListaDetalles().get(0).getNombreRuta());
				cstm1.setBoolean(6,tarjeta.getListaDetalles().get(0).isVigencia());
				rs = cstm1.executeQuery();
				rs.next();
				codigoDetalleTarjeta = rs.getInt(1);
				CallableStatement cstm2 = conexion.prepareCall("{call pr_iTiemposDetalleTarjeta(?,?,?,?)}");
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
		}finally {
			Conexion.cerrarConexion();
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
		}finally {
			Conexion.cerrarConexion();
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
				tarjeta.setVigencia(rs.getBoolean("p_vigencia"));
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
				tarjeta.setVigencia(rs.getBoolean("p_vigencia"));
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
				tarjeta.setEstado(rs.getString("p_estado"));
				tarjeta.setMensaje("p_mensaje");
				tarjeta.setVigencia(rs.getBoolean("p_vigencia"));
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
	/*Revisar este método*/
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
				tarjeta.setVigencia(rs.getBoolean("p_vigencia"));
			}
			
			Conexion.cerrarConexion();
			return tarjeta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void darBajaTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_eTarjeta(?,?,?)}");
			cstm.setInt(1,tarjeta.getCodigo());
			cstm.setString(2, tarjeta.getMensaje());
			cstm.setInt(3, tarjeta.getCodigoUsuario());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void darBajaDetalleTarjeta(DetalleTarjeta detalleTarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_eDetalleTarjeta(?,?)}");
			cstm.setInt(1,detalleTarjeta.getCodigo());
			cstm.setString(2,detalleTarjeta.getMensaje());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<DetalleTarjeta> obtenerDetalleTarjeta(int codigoTarjeta) throws Exception{
		List<DetalleTarjeta> listaDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liDetalleTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoTarjeta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
				detalleTarjeta.setCodigo(rs.getInt("codigoDetalleTarjeta"));
				detalleTarjeta.setCodigoRuta(rs.getInt("codigoRuta"));
				detalleTarjeta.setCodigoTarjeta(rs.getInt("codigoTarjeta"));
				detalleTarjeta.setNumeroVuelta(rs.getInt("numeroVuelta"));
				detalleTarjeta.setNombreRuta(rs.getString("nombreRuta"));
				listaDetalleTarjeta.add(detalleTarjeta);
			}
			Conexion.cerrarConexion();
			return listaDetalleTarjeta;
			
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<TiemposDetalleTarjeta> obtenerTiempoDetalleTarjeta(int codigoDetalleTarjeta) throws Exception{
		List<TiemposDetalleTarjeta> listaTiemposDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTiemposDetalleTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoDetalleTarjeta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				TiemposDetalleTarjeta tiempoDetalle  = new TiemposDetalleTarjeta();
				DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
				tiempoDetalle.setCodigo(rs.getInt("codigo"));
				detalleTarjeta.setCodigo(rs.getInt("codigoDetalleTarjeta"));
				detalleTarjeta.setHoraInicio(rs.getString("horaInicio"));
				tiempoDetalle.setDetalleTarjeta(detalleTarjeta);
				tiempoDetalle.setCodigoTiempoEstablecido(rs.getInt("codigoTiempoEstablecido"));
				tiempoDetalle.setHoraControl(rs.getString("horaControl"));
				tiempoDetalle.setHoraGPS((rs.getString("horaGPS")!= null ? rs.getString("horaGPS") : ""));
				tiempoDetalle.setDiferencia(rs.getDouble("diferencia"));
				tiempoDetalle.setMinutosTolerancia(rs.getInt("minutosTolerancia"));
				listaTiemposDetalleTarjeta.add(tiempoDetalle);
			}
			Conexion.cerrarConexion();
			return listaTiemposDetalleTarjeta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	@Transactional
	public DetalleTarjeta obtenerUltimoDetalleTarjeta(int codigoTarjeta)throws Exception{
		List<TiemposDetalleTarjeta> listaTiemposDetalleTarjeta = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liUltimoDetalleTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoTarjeta);
			rs = cstm.executeQuery();
			DetalleTarjeta detalleTarjeta = new DetalleTarjeta();
			while(rs.next()) {
				TiemposDetalleTarjeta tiempo = new TiemposDetalleTarjeta();
				detalleTarjeta.setCodigo(rs.getInt("p_codigoDetalleTarjeta"));
				detalleTarjeta.setCodigoTarjeta(rs.getInt("p_codigoTarjeta"));
				detalleTarjeta.setCodigoRuta(rs.getInt("p_codigoRuta"));
				detalleTarjeta.setNumeroVuelta(rs.getInt("p_numeroVuelta"));
				detalleTarjeta.setNombreRuta(rs.getString("p_nombreRuta"));
				detalleTarjeta.setHoraInicio(rs.getString("p_horaInicio"));
				
				tiempo.setCodigo(rs.getInt("p_codigoTiempoDetalleTarjeta"));
				tiempo.setCodigoTiempoEstablecido(rs.getInt("p_codigoTiempoEstablecido"));
				tiempo.setDiferencia(rs.getDouble("p_diferencia"));
				tiempo.setHoraControl(rs.getString("p_horaControl"));
				tiempo.setHoraGPS(rs.getString("p_horaGPS"));
				tiempo.setMinutosTolerancia(rs.getInt("p_minutosTolerancia"));
				listaTiemposDetalleTarjeta.add(tiempo);
			}
			detalleTarjeta.setListaTiemposDetalleTarjeta(listaTiemposDetalleTarjeta);
			Conexion.cerrarConexion();
			return detalleTarjeta;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void registrarTiemposGPS(List<TiemposDetalleTarjeta> listaTiemposDetalle) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aTiemposGPSDetalleTarjeta(?,?,?)}");
			
			for (TiemposDetalleTarjeta tiemposDetalleTarjeta : listaTiemposDetalle) {
				if(tiemposDetalleTarjeta.getHoraGPS() != null) {
					cstm.setInt(1,tiemposDetalleTarjeta.getCodigo());
					cstm.setString(2,tiemposDetalleTarjeta.getHoraGPS());
					cstm.setDouble(3,tiemposDetalleTarjeta.getDiferencia());
					cstm.execute();
				}
				
			}
			Conexion.cerrarConexion();
			
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<Tarjeta> listarUnidadesTarjetaPorFecha(String fecha,int codigoRegistroUnidad)throws Exception{
		List<Tarjeta> listaTarjetas = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liUnidadesTrabajando(?,?)}");
			ResultSet rs;
			cstm.setString(1,fecha);
			cstm.setInt(2,codigoRegistroUnidad);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("p_codigoTarjeta"));
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRegistroUnidad"));
				tarjeta.setFecha(rs.getString("p_fecha"));
				listaTarjetas.add(tarjeta);
			}
			return listaTarjetas;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<Tarjeta> listarUnidadesTarjetaOpcion(String fecha,int codigoRegistroUnidad, String opcion)throws Exception{
		List<Tarjeta> listaTarjetas = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
		CallableStatement cstm = conexion.prepareCall("{call pr_liUnidadesTrabajandoFecha(?,?,?)}");
			ResultSet rs;
			cstm.setString(1,fecha);
			cstm.setInt(2,codigoRegistroUnidad);
			cstm.setString(3,opcion);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt(1));
				listaTarjetas.add(tarjeta);
			}
			return listaTarjetas;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public String estadoUltimaTarjeta(int codigoRU) throws Exception{
		String estado = "";
		try{
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_estadoUltimaTarjeta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoRU);
			rs= cstm.executeQuery();
			while(rs.next()) {
				estado = rs.getString(1);
			}
			return estado;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public void cambiarEstado(int codigoTarjeta,String estado) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aEstadoTarjeta(?,?)}");
			cstm.setInt(1,codigoTarjeta);
			cstm.setString(2,estado);
			cstm.execute();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
