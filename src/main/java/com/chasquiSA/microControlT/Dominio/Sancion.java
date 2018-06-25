package com.chasquiSA.microControlT.Dominio;

public class Sancion {
	private int codigo;
	private DetalleTarjeta detalleTarjeta;
	private int codigoRegistroUnidad;
	private String justificacion;
	private String motivo;
	private String estado;
	private Double tiempoRetraso;
	private Double sancionTiempo;
	private Double sancionMonto;
	private boolean vigencia;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	public int getCodigoRegistroUnidad() {
		return codigoRegistroUnidad;
	}
	public void setCodigoRegistroUnidad(int codigoRegistroUnidad) {
		this.codigoRegistroUnidad = codigoRegistroUnidad;
	}
	public DetalleTarjeta getDetalleTarjeta() {
		return detalleTarjeta;
	}
	public void setDetalleTarjeta(DetalleTarjeta detalleTarjeta) {
		this.detalleTarjeta = detalleTarjeta;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getTiempoRetraso() {
		return tiempoRetraso;
	}
	public void setTiempoRetraso(Double tiempoRetraso) {
		this.tiempoRetraso = tiempoRetraso;
	}
	public Double getSancionTiempo() {
		return sancionTiempo;
	}
	public void setSancionTiempo(Double sancionTiempo) {
		this.sancionTiempo = sancionTiempo;
	}
	public Double getSancionMonto() {
		return sancionMonto;
	}
	public void setSancionMonto(Double sancionMonto) {
		this.sancionMonto = sancionMonto;
	}
	public boolean isVigencia() {
		return vigencia;
	}
	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	
	
	
	
}
