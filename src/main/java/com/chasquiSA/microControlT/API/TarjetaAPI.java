package com.chasquiSA.microControlT.API;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microControlT.DAO.DetalleTarjetaDAO;
import com.chasquiSA.microControlT.Dominio.DetalleTarjeta;
import com.chasquiSA.microControlT.Dominio.Tarjeta;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaAPI {
	@PostMapping("/")
	public ResponseEntity <?> registrarTarjeta(@RequestBody Tarjeta tarjeta)throws Exception{
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			tarjeta.setCodigo(dao.registroTarjeta(tarjeta));
			return new ResponseEntity<>(tarjeta.getCodigo(),HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/detalle/")
	public ResponseEntity<?> registrarDetalleTarjeta(@RequestBody Tarjeta tarjeta)throws Exception{
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			dao.registroDetalleTarjeta(tarjeta);
			return new ResponseEntity<>("Detalle registrado con exito",HttpStatus.OK);
			}catch(Exception e) {
				throw e;
			}
	}
	
	@PostMapping("/verificarTarjeta/")
	public ResponseEntity<?> verificarTarjeta(@RequestBody Tarjeta tarjeta) throws Exception{  
		boolean respuesta;
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			respuesta = dao.verificarTarjeta(tarjeta);
			return new ResponseEntity<>(respuesta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/{codigo}/{desdeFecha}/{hastaFecha}")
	public ResponseEntity<?> listarTarjetas(@PathVariable int codigo, @PathVariable("desdeFecha") String desdeFecha,@PathVariable("hastaFecha") String hastaFecha)throws Exception{
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			List<Tarjeta> listaTarjeta = new ArrayList<>();
			listaTarjeta = dao.listarTarjetas(codigo,desdeFecha,hastaFecha);
			return new ResponseEntity<>(listaTarjeta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> obtenerTarjeta(@PathVariable int codigo)throws Exception{
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			Tarjeta tarjeta = new Tarjeta();
			tarjeta = dao.obtenerTarjeta(codigo);
			return new ResponseEntity<>(tarjeta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/{desdeFecha}/{hastaFecha}/{estado}")
	public ResponseEntity<List<Tarjeta>> listarTarjetasEstado(@PathVariable("desdeFecha") String desdeFecha,@PathVariable("hastaFecha") String hastaFecha,
			@PathVariable("estado") String estado)throws Exception{
		List<Tarjeta> listaTarjeta = new ArrayList<>();
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			listaTarjeta = dao.listarTarjetasEstado(desdeFecha, hastaFecha, estado);
			return new ResponseEntity<List<Tarjeta>>(listaTarjeta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/detalleTarjeta/{codigoTarjeta}")
	public ResponseEntity<List<DetalleTarjeta>> listarNumeroVueltaTarjeta(@PathVariable("codigoTarjeta") int codigoTarjeta)throws Exception{
		List<DetalleTarjeta> listaDetallesTarjeta= new ArrayList<>();
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			listaDetallesTarjeta = dao.obtenerNumeroVueltaTarjeta(codigoTarjeta);
			return new ResponseEntity<List<DetalleTarjeta>>(listaDetallesTarjeta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@GetMapping("/detalleTarjeta/{codigoTarjeta}/{codigoRuta}/{numeroVuelta}")
	public ResponseEntity<List<DetalleTarjeta>> listarDetalleTarjeta(@PathVariable("codigoTarjeta") int codigoTarjeta,
			@PathVariable("codigoRuta") int codigoRuta , @PathVariable("numeroVuelta") int numeroVuelta)throws Exception{
		List<DetalleTarjeta> listaDetallesTarjeta= new ArrayList<>();
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			listaDetallesTarjeta = dao.obtenerTiempoDetalleTarjeta(codigoTarjeta,codigoRuta,numeroVuelta);
			return new ResponseEntity<List<DetalleTarjeta>>(listaDetallesTarjeta,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@DeleteMapping("/")
	public ResponseEntity<?> darBajaTarjeta(@RequestBody Tarjeta tarjeta)throws Exception{
		try {
			DetalleTarjetaDAO dao = new DetalleTarjetaDAO();
			dao.darBajaTarjeta(tarjeta);
			return new ResponseEntity<>("Tarjeta dada de baja correctamente",HttpStatus.OK);
		}catch(Exception e){
			throw e;
		}
	}
	
	
}
