package com.chasquiSA.microControlT.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Tarjeta;

public class DetalleTarjetaDAO {
	public int registroTarjeta(Tarjeta tarjeta)throws Exception {
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iTarjeta(?,?,?,?,?,?)}");
			ResultSet rs;
			int codigoTarjeta;
			cstm.setString(1,tarjeta.getNumero());
			cstm.setString(2,tarjeta.getFecha());
			cstm.setString(3,tarjeta.getUsuario());
			cstm.setString(4,tarjeta.getEstado());
			cstm.setInt(5,tarjeta.getCodigoRegistroUnidad());
			cstm.setBoolean(6,tarjeta.isVigencia());
			
			rs = cstm.executeQuery();
			rs.next();
			codigoTarjeta = rs.getInt(1);
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?,?,?,?,?)}");
			for(int i=0;i<tarjeta.getListaDetalles().size();i++) {
				cstm1.setInt(1,codigoTarjeta);
				cstm1.setInt(2,tarjeta.getListaDetalles().get(i).getCodigoTiempoEstablecido());
				cstm1.setString(3,tarjeta.getListaDetalles().get(i).getHoraControl());
				cstm1.setString(4,tarjeta.getListaDetalles().get(i).getEstado());
				cstm1.setInt(5,tarjeta.getListaDetalles().get(i).getNumeroVuelta());
				cstm1.setInt(6, tarjeta.getListaDetalles().get(i).getMinutosTolerancia());
				cstm1.setInt(7,tarjeta.getListaDetalles().get(i).getCodigoRuta());
				cstm1.setString(8, tarjeta.getListaDetalles().get(i).getHoraInicio());
				cstm1.setString(9, tarjeta.getListaDetalles().get(i).getNombreRuta());
				cstm1.setBoolean(10,tarjeta.getListaDetalles().get(i).isVigencia());
				cstm1.execute();
			}
			Conexion.cerrarConexion();
			return codigoTarjeta;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void registroDetalleTarjeta(Tarjeta tarjeta) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();	
			CallableStatement cstm1 = conexion.prepareCall("{call pr_iDetalleTarjeta(?,?,?,?,?,?,?,?,?,?)}");
			for(int i=0;i<tarjeta.getListaDetalles().size();i++) {
				cstm1.setInt(1,tarjeta.getCodigo());
				cstm1.setInt(2,tarjeta.getListaDetalles().get(i).getCodigoTiempoEstablecido());
				cstm1.setString(3,tarjeta.getListaDetalles().get(i).getHoraControl());
				cstm1.setString(4,tarjeta.getListaDetalles().get(i).getEstado());
				cstm1.setInt(5,tarjeta.getListaDetalles().get(i).getNumeroVuelta());
				cstm1.setInt(6, tarjeta.getListaDetalles().get(i).getMinutosTolerancia());	
				cstm1.setInt(7,tarjeta.getListaDetalles().get(i).getCodigoRuta());
				cstm1.setString(8, tarjeta.getListaDetalles().get(i).getHoraInicio());
				cstm1.setString(9, tarjeta.getListaDetalles().get(i).getNombreRuta());
				cstm1.setBoolean(9,tarjeta.getListaDetalles().get(i).isVigencia());
				cstm1.execute();
			}
			Conexion.cerrarConexion();
			cstm1.execute();
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
				tarjeta.setEstado(rs.getString("p_estado"));
				listaTarjetas.add(tarjeta);
			}
			Conexion.cerrarConexion();
			return listaTarjetas;
		}catch(Exception e) {
			throw e;	
		}
	}
	
	public List<Tarjeta> listarTarjetas(int p_codigo, String p_fecha) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			List<Tarjeta> listaTarjetas = new ArrayList<>();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTarjeta(?,?)}");
			ResultSet rs;
			cstm.setInt(1,p_codigo);
			cstm.setString(2, p_fecha);
			rs = cstm.executeQuery();
			while(rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setCodigo(rs.getInt("p_codigo"));
				tarjeta.setCodigoRegistroUnidad(rs.getInt("p_codigoRu"));
				tarjeta.setNumero(rs.getString("p_numeroTarjeta"));
				tarjeta.setFecha(rs.getString("p_fechaTarjeta"));
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
	
	public List<DetalleTarjeta> obtenerNumeroVueltaTarjeta(int codigoTarjeta) throws Exception{
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
				detalleTarjeta.setNumeroVuelta(rs.getInt("numeroVuelta"));
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
}
