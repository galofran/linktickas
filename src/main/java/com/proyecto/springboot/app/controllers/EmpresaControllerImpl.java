package com.proyecto.springboot.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.seguridad.JwtTokenProvider;
import com.proyecto.springboot.app.service.EmpresaService;
import com.proyecto.springboot.app.service.OfertaService;

@RestController
@RequestMapping("/linktickas/empresas")
@CrossOrigin
public class EmpresaControllerImpl /*implements ControllerBase<Empresa>*/{

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private OfertaService ofertaService;
	
	/*@Autowired
	private JwtTokenProvider jwt;
	
	@GetMapping
	public ResponseEntity<List<?>> readAll(@RequestHeader(name = "Authorization") String token) {
		
			return ResponseEntity.ok(empresaService.findAll());
		

	}*/

	@GetMapping
	public ResponseEntity<List<?>> readAll() {		
			return ResponseEntity.ok(empresaService.findAll());
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody EmpresaDTO empresaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresaDTO));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id) {
		
		try {
			EmpresaDTO empresaDTO = empresaService.findById(id);
			return ResponseEntity.ok(empresaDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa con id" + id +" no existe");
		}
		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@RequestBody Empresa empresa,@PathVariable Long id) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.update(empresa, id));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa con " + id +" no existe");
		}
	
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		try {
			empresaService.deleteById(id);
			return ResponseEntity.ok().body("Empresa borrada existosamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa con " + id +" no existe");	
		}
		
	}
	
	@GetMapping("/{idEmpresa}/ofertas")
	public ResponseEntity<List<?>> oferasPorIdDeEmpresa(@PathVariable Long idEmpresa){
		return ResponseEntity.ok(StreamSupport.stream(ofertaService.buscarOfertasPorIdDeEmpresa(idEmpresa).spliterator() , false)
				.collect(Collectors.toList()));
	}

}
