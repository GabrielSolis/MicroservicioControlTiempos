package com.chasquiSA.microControlT.Dominio;

import java.util.List;

public class DetalleTarjeta {
	private int codigo;
	private int codigoTarjeta;
	private int codigoRuta;
	private String mensaje;
	private String nombreRuta;
	private String horaInicio;
	private List<TiemposDetalleTarjeta> listaTiemposDetalleTarjeta;
	private boolean vigencia;
	private int numeroVuelta;
	
	
   
    
	public DetalleTarjeta() {}
	
	
	
	public int getNumeroVuelta() {
		return numeroVuelta;
	}



	public void setNumeroVuelta(int numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}



	public List<TiemposDetalleTarjeta> getListaTiemposDetalleTarjeta() {
		return listaTiemposDetalleTarjeta;
	}

	
	public void setListaTiemposDetalleTarjeta(List<TiemposDetalleTarjeta> listaTiemposDetalleTarjeta) {
		this.listaTiemposDetalleTarjeta = listaTiemposDetalleTarjeta;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}




	public int getCodigoTarjeta() {
		return codigoTarjeta;
	}




	public void setCodigoTarjeta(int codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}







	public int getCodigoRuta() {
		return codigoRuta;
	}




	public void setCodigoRuta(int codigoRuta) {
		this.codigoRuta = codigoRuta;
	}




	public String getMensaje() {
		return mensaje;
	}




	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}




	public String getNombreRuta() {
		return nombreRuta;
	}




	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}




	public String getHoraInicio() {
		return horaInicio;
	}




	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}




	public boolean isVigencia() {
		return vigencia;
	}




	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	

	
}
