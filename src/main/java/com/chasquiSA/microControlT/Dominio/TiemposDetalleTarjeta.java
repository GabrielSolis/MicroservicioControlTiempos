package com.chasquiSA.microControlT.Dominio;


public class TiemposDetalleTarjeta {
	private int codigo;
	private int codigoTiempoEstablecido;
	private DetalleTarjeta detalleTarjeta;
	private int numeroVuelta;
	private int minutosTolerancia;
	private String horaGPS;
	private Double diferencia;
	private String horaControl;
	
	
	public DetalleTarjeta getDetalleTarjeta() {
		return detalleTarjeta;
	}
	public void setDetalleTarjeta(DetalleTarjeta detalleTarjeta) {
		this.detalleTarjeta = detalleTarjeta;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoTiempoEstablecido() {
		return codigoTiempoEstablecido;
	}
	public void setCodigoTiempoEstablecido(int codigoTiempoEstablecido) {
		this.codigoTiempoEstablecido = codigoTiempoEstablecido;
	}
	public int getNumeroVuelta() {
		return numeroVuelta;
	}
	public void setNumeroVuelta(int numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}
	public int getMinutosTolerancia() {
		return minutosTolerancia;
	}
	public void setMinutosTolerancia(int minutosTolerancia) {
		this.minutosTolerancia = minutosTolerancia;
	}
	public String getHoraGPS() {
		return horaGPS;
	}
	public void setHoraGPS(String horaGPS) {
		this.horaGPS = horaGPS;
	}
	public Double getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(Double diferencia) {
		this.diferencia = diferencia;
	}
	public String getHoraControl() {
		return horaControl;
	}
	public void setHoraControl(String horaControl) {
		this.horaControl = horaControl;
	}
	
	
	
}
