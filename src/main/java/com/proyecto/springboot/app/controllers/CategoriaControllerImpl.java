package com.proyecto.springboot.app.controllers;

import java.util.ArrayList;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.entity.Categoria;
import com.proyecto.springboot.app.seguridad.JwtTokenProvider;
import com.proyecto.springboot.app.service.CategoriaService;
import com.proyecto.springboot.app.service.OfertaService;

@RestController
@RequestMapping("/linktickas/categorias")
@CrossOrigin(origins = "*")
public class CategoriaControllerImpl{

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private JwtTokenProvider jwtUtil;
	
	
	
	Logger logger = LoggerFactory.getLogger(CategoriaControllerImpl.class); 
	
	
	@GetMapping
	public ResponseEntity<List<?>> readAll() {
			
		return ResponseEntity.ok(
				StreamSupport.stream(categoriaService.findAll().spliterator(), false)
				.collect(Collectors.toList()));
	}
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody CategoriaDTO categoriaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaDTO));
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		logger.info("LEYENDO ID : " + id);
		try {
			CategoriaDTO catDTO = categoriaService.findById(id);
			return ResponseEntity.ok(catDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria con id" + id +" no existe");
		}
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody Categoria categoria,@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.update(categoria, id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria con " + id +" no existe");
		}
	
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			categoriaService.deleteById(id);
			return ResponseEntity.ok().body("Categoria borrada existosamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria con " + id +" no existe");	
		}
		
	}
	
	@GetMapping("/{idCategoria}/ofertas")
	public ResponseEntity<List<?>> readAllOfertasByCategory(@PathVariable(value = "idCategoria" )Long idCategoria) {
		
		return  ResponseEntity.ok(StreamSupport
				.stream( ofertaService.buscarOfertasPorIdDeCategoria(idCategoria).spliterator() , false)
				.collect(Collectors.toList()));
	}

}
