package com.chasquiSA.microControlT.util;

import com.chasquiSA.microControlT.Dominio.Sancion;
import com.chasquiSA.microControlT.Dominio.Tarjeta;

public class TarjetaSancionDTO {
	Tarjeta tarjeta;
	Sancion sancion;
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	public Sancion getSancion() {
		return sancion;
	}
	public void setSancion(Sancion sancion) {
		this.sancion = sancion;
	}
	
	
}
