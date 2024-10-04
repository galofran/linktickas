package com.proyecto.springboot.app.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.dto.GradoSuperiorDTO;
import com.proyecto.springboot.app.entity.Categoria;
import com.proyecto.springboot.app.entity.GradoSuperior;
import com.proyecto.springboot.app.service.GradoSuperiorService;

@RestController
@RequestMapping("/linktickas/grados")
public class GradoSuperiorController {

	@Autowired private GradoSuperiorService gsService;
	
	@GetMapping
	public ResponseEntity<List<?>> readAll() {
		return ResponseEntity.ok(
				StreamSupport.stream(gsService.findAll().spliterator(), false)
				.collect(Collectors.toList()));
	}

	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody GradoSuperiorDTO gsDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(gsService.save(gsDTO));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		
		try {
			GradoSuperiorDTO gsDTO = gsService.findById(id);
			return ResponseEntity.ok(gsDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El grado superior con id" + id +" no existe");
		}
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody GradoSuperior gs,@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(gsService.update(gs, id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria con " + id +" no existe");
		}
	
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			gsService.deleteById(id);
			return ResponseEntity.ok().body("Categoria borrada existosamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria con " + id +" no existe");	
		}
		
	}

}
