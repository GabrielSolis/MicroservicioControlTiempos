package com.chasquiSA.microControlT.API;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microControlT.DAO.ReporteIndicesDAO;
import com.chasquiSA.microControlT.Dominio.Tarjeta;

@RestController
@RequestMapping("/reporte")
public class ReporteAPI {
	@Autowired
	ReporteIndicesDAO dao;
	
	@GetMapping(value = "/indicesRutas/{fecha}/{opcion}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tarjeta>> obtenerIndicesRutas(@PathVariable("fecha")String fecha, @PathVariable("opcion")String opcion)throws Exception{
		List<Tarjeta> listaIndices = new ArrayList<>();
		try {
			listaIndices = dao.listarIndicesRescorridoRutas(fecha, opcion);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<List<Tarjeta>>(listaIndices,HttpStatus.OK);
	}
}
