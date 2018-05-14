package com.chasquiSA.microControlT.Dominio;

import java.util.List;

public class Tarjeta {
	private int codigo;
	private int codigoRegistroUnidad;
	private String numero;
	private String usuario;
	private String fecha;
	private String estado;
	private String mensaje;
	List<DetalleTarjeta> listaDetalles;
	private boolean vigencia;
	
	public Tarjeta() {
		
	}

		
	public Tarjeta(int codigo, int codigoRegistroUnidad, String numero, String usuario, String fecha, String estado,
			List<DetalleTarjeta> listaDetalles, String mensaje, boolean vigencia) {
		
		this.codigo = codigo;
		this.codigoRegistroUnidad = codigoRegistroUnidad;
		this.numero = numero;
		this.usuario = usuario;
		this.fecha = fecha;
		this.estado = estado;
		this.listaDetalles = listaDetalles;
		this.mensaje = mensaje;
		this.vigencia = vigencia;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	

	public List<DetalleTarjeta> getListaDetalles() {
		return listaDetalles;
	}


	public void setListaDetalles(List<DetalleTarjeta> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}


	public int getCodigoRegistroUnidad() {
		return codigoRegistroUnidad;
	}


	public void setCodigoRegistroUnidad(int codigoRegistroUnidad) {
		this.codigoRegistroUnidad = codigoRegistroUnidad;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	
	
	
}
