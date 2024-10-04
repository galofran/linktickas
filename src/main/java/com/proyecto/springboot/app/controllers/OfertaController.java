package com.proyecto.springboot.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Oferta;
import com.proyecto.springboot.app.service.OfertaService;


@RestController
@RequestMapping("/linktickas/ofertas")
public class OfertaController{

	@Autowired
	private OfertaService ofertaService;
	
	
	
	Logger logger = LoggerFactory.getLogger(OfertaController.class);
	
	@GetMapping
	public ResponseEntity<List<?>> readAll() {
		return  ResponseEntity.ok(StreamSupport
				.stream( ofertaService.findAll().spliterator() , false)
				.collect(Collectors.toList()));
	}

	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody OfertaDTO ofertaDTO) {
		logger.info("OFERTA DTO " + ofertaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.save(ofertaDTO));
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		
		try {
			OfertaDTO ofertaDTO = ofertaService.findById(id);
			return ResponseEntity.ok(ofertaDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La oferta con id " + id +" no existe");
		}
	}

		
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody Oferta oferta, @PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.update(oferta, id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La oferta con " + id +" no existe");
		}
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			ofertaService.deleteById(id);
			return ResponseEntity.ok().body("Oferta borrada existosamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La oferta con " + id +" no existe");	
		}
	}
	
	@GetMapping(value="/busqueda/{nombreDeOferta}",produces = {"application/json"})
	public  ResponseEntity<List<?>> buscarOfertasPorNombre(@PathVariable String nombreDeOferta) {
		return  ResponseEntity.ok(StreamSupport
				.stream( ofertaService.buscarOfertasPorNombre(nombreDeOferta) .spliterator() , false)
				.collect(Collectors.toList()));
	}
	
	
	
}
