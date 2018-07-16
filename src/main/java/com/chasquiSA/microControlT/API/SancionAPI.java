package com.chasquiSA.microControlT.API;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microControlT.DAO.SancionDAO;
import com.chasquiSA.microControlT.Dominio.Sancion;
import com.chasquiSA.microControlT.util.TarjetaSancionDTO;

@RestController
@RequestMapping("/sancion")
public class SancionAPI {
	
	@Autowired
	SancionDAO dao;
	@PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> registrarSancion(@RequestBody Sancion sancion)throws Exception{
		int respuesta=0;
		try {
			Logger log = Logger.getLogger("Logger de Ejemplo");
			log.info(sancion.getDetalleTarjeta().getCodigo());
			respuesta = dao.registroSancion(sancion);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Integer>(respuesta,HttpStatus.OK);
	}
	
	@GetMapping(value="/{codigoDetalle}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sancion> obtenerSancionDetalleTarjeta(@PathVariable("codigoDetalle")int codigoDetalle)throws Exception{

		Sancion sancion = new Sancion();
		try {
			sancion = dao.obtenerSancionDetalleTarjeta(codigoDetalle);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Sancion>(sancion,HttpStatus.OK);
	}
	
	@GetMapping(value="/listarPorUnidad/{codigoRU}/{fechaInicio}/{fechaFin}/{opcion}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TarjetaSancionDTO>> obtenerSancionesUnidad(@PathVariable("codigoRU") int codigoRU,@PathVariable("fechaInicio")String fechaInicio,
			@PathVariable("fechaFin")String fechaFin, @PathVariable("opcion")String opcion) throws Exception{
		List<TarjetaSancionDTO> listaSancionesTarjeta = new ArrayList<>();
		try {
			listaSancionesTarjeta = dao.obtenerSancionesUnidad(fechaInicio, fechaFin, codigoRU, opcion);
			
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<List<TarjetaSancionDTO>>(listaSancionesTarjeta,HttpStatus.OK);	
	}
}
