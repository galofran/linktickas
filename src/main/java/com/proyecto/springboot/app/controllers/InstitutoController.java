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
import com.proyecto.springboot.app.dto.InstitutoDTO;
import com.proyecto.springboot.app.entity.Instituto;
import com.proyecto.springboot.app.service.InstitutoService;

@RestController
@RequestMapping("/linktickas/institutos")
public class InstitutoController {

	@Autowired
	private InstitutoService instiService;
	
	@GetMapping
	public ResponseEntity<List<?>> readAll() {
		return  ResponseEntity.ok(StreamSupport
				.stream(instiService.findAll().spliterator() , false)
				.collect(Collectors.toList()));
		
	}

	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody InstitutoDTO instiDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(instiService.save(instiDTO));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		
		try {
			InstitutoDTO instiDTO = instiService.findById(id);
			return ResponseEntity.ok(instiDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El instituto con id" + id +" no existe");
		}
		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody Instituto insti,@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(instiService.update(insti, id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa con " + id +" no existe");
		}
	
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			instiService.deleteById(id);
			return ResponseEntity.ok().body("Se borró el instituto");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El instituto con " + id +" no existe");	
		}
	}
}
