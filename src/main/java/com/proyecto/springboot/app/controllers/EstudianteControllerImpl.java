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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.entity.Estudiante;
import com.proyecto.springboot.app.entity.Usuario;
import com.proyecto.springboot.app.repository.UsuarioRepositorio;
import com.proyecto.springboot.app.service.EstudianteService;
import com.proyecto.springboot.app.service.OfertaService;

@RestController
@RequestMapping("/linktickas/estudiantes")
public class EstudianteControllerImpl{

	Logger logger = LoggerFactory.getLogger(EstudianteControllerImpl.class); 
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@GetMapping
	public ResponseEntity<List<?>> readAll() {
		return  ResponseEntity.ok(StreamSupport
				.stream(estudianteService.findAll().spliterator() , false)
				.collect(Collectors.toList()));
		
	}

	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> create(@RequestBody EstudianteDTO estudianteDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudianteDto));
	}

	
	@GetMapping("/{idEstudiante}")
	public ResponseEntity<?> readById(@PathVariable Long idEstudiante) {
		try {
			EstudianteDTO estDTO = estudianteService.findById(idEstudiante);
			return ResponseEntity.ok(estDTO);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante con dni" + idEstudiante +" no existe");
		}
		
	}

	
	@PutMapping("/{idEstudiante}")
	public ResponseEntity<?> updateByIdEstudiante(@RequestBody Estudiante estudiante,@PathVariable Long idEstudiante) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.updateById(estudiante, idEstudiante));	
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante con id: " + idEstudiante +" no existe");
		}
	
	}

	
	@DeleteMapping("/{idEstudiante}")
	public ResponseEntity<?> deleteById(@PathVariable Long idEstudiante) {
		try {
			estudianteService.deleteById(idEstudiante);
			
			return ResponseEntity.ok().body("Estudiante borrado");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante con dni" + idEstudiante +" no existe");	
		}
		
	}
	
	@GetMapping("/{id}/mis-ofertas")
	public ResponseEntity<List<?>> misOfertas(@PathVariable Long id) {
		return  ResponseEntity.ok(StreamSupport
				.stream(ofertaService.buscarOfertasDeEstudiantePorSuId(id).spliterator() , false)
				.collect(Collectors.toList()));
		
	}

	
	@GetMapping("/correo/{correo}")
	public ResponseEntity <EstudianteDTO> verDatos(@PathVariable String correo){
		
		EstudianteDTO estudiante = estudianteService.buscarUsuarioPorCorreo(correo);
		
		return ResponseEntity.ok(estudiante);
	} 
	
	//http://localhost:8080/linktickas/estudiantes/1/ofertas/1
	@PutMapping("{idEstudiante}/ofertas/{idOferta}")
	public EstudianteDTO asignarOfertasAEstudiante(@PathVariable Long idEstudiante, @PathVariable Long idOferta) {
		return estudianteService.asignarOfertaAEstudiante(idEstudiante, idOferta);
	}
	
	
	@GetMapping("/buscarUsuarioEstudiantePorEmail/{email}")
	public ResponseEntity<?> buscarPor(@PathVariable String email) {
		
		try {
			Usuario usuario = usuarioRepositorio.findByEmail(email).get();
			
			return ResponseEntity.ok().body(usuario.getId());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante con email" + email +" no existe");	
		}
		
	}
	
	
	@DeleteMapping("/borrarUsuario-EstudiantePorCorreo/{correo}")
	public ResponseEntity<?> deleteByCorreo(@PathVariable String correo) {
		
		try {
			estudianteService.borrarUsuarioEstudianteBuscandoPorCorreoYEliminandoPorId(correo);
			
			return ResponseEntity.ok().body("Usuario estudiante borrado");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante con correo" + correo +" no existe");	
		}
		
	}
	
	
	
	
}
