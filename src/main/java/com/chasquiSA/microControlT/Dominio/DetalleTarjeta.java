package com.chasquiSA.microControlT.Dominio;

public class DetalleTarjeta {
	private int codigo;
	private int codigoTarjeta;
	private String horaControl;
	private String estado;
	private String horaGPS;
	private Double diferencia;
	private int codigoTiempoEstablecido;
	private int numeroVuelta;
	private int minutosTolerancia;
	private int codigoRuta;
	private String horaInicio;
	private boolean vigencia;
	
	
   
    
	public DetalleTarjeta() {}

	
	public DetalleTarjeta(int codigo, int codigoTarjeta, String horaControl, String estado, String horaGPS,
			Double diferencia, int codigoTiempoEstablecido, int numeroVuelta, int minutosTolerancia, int codigoRuta,
			String horaInicio, boolean vigencia) {
		super();
		this.codigo = codigo;
		this.codigoTarjeta = codigoTarjeta;
		this.horaControl = horaControl;
		this.estado = estado;
		this.horaGPS = horaGPS;
		this.diferencia = diferencia;
		this.codigoTiempoEstablecido = codigoTiempoEstablecido;
		this.numeroVuelta = numeroVuelta;
		this.minutosTolerancia = minutosTolerancia;
		this.codigoRuta = codigoRuta;
		this.horaInicio = horaInicio;
		this.vigencia = vigencia;
	}


	public int getCodigoRuta() {
		return codigoRuta;
	}


	public void setCodigoRuta(int codigoRuta) {
		this.codigoRuta = codigoRuta;
	}


	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public int getCodigoTiempoEstablecido() {
		return codigoTiempoEstablecido;
	}


	public int getMinutosTolerancia() {
		return minutosTolerancia;
	}


	public void setMinutosTolerancia(int minutosTolerancia) {
		this.minutosTolerancia = minutosTolerancia;
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


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
//	public Tarjeta getTarjeta() {
//		return tarjeta;
//	}
//
//	public void setTarjeta(Tarjeta tarjeta) {
//		this.tarjeta = tarjeta;
//	}

	public int getCodigoTarjeta() {
		return codigoTarjeta;
	}


	public void setCodigoTarjeta(int codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}


	public String getHoraControl() {
		return horaControl;
	}

	public void setHoraControl(String horaControl) {
		this.horaControl = horaControl;
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
