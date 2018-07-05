package com.chasquiSA.microControlT.API;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microControlT.DAO.SancionDAO;
import com.chasquiSA.microControlT.Dominio.Sancion;

@RestController
@RequestMapping("/sancion")
public class SancionAPI {
	
	@Autowired
	SancionDAO dao;
	@PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> registrarSancion(@RequestBody Sancion sancion)throws Exception{
		int respuesta=0;
		try {
			respuesta = dao.registroSancion(sancion);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Integer>(respuesta,HttpStatus.OK);
	}
	
	@GetMapping(value="/{codigoDetalle}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sancion> obtenerSancionDetalleTarjeta(@RequestParam("codigoDetalle")int codigoDetalle)throws Exception{
		Logger log = Logger.getLogger("Logger de Ejemplo");
		log.info(codigoDetalle);
		Sancion sancion = new Sancion();
		try {
			sancion = dao.obtenerSancionDetalleTarjeta(codigoDetalle);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Sancion>(sancion,HttpStatus.OK);
	}
}
