package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.entity.Estudiante;

@Service
public class EstudianteServiceAux {

	public Estudiante crearEstudiante(EstudianteDTO estudianteDTO) {
		return new Estudiante(estudianteDTO);
	}
	
	public EstudianteDTO crearEstudianteDto(Estudiante estudiante) {
		return new EstudianteDTO(estudiante);
	}
	
	public List<EstudianteDTO> crearEstudiantesDto(List<Estudiante> estudiantes){
		List<EstudianteDTO> listaDeEstudiantes = new ArrayList<>();
		
		estudiantes.stream().forEach(
				est -> {
					listaDeEstudiantes.add(crearEstudianteDto(est));
				}
		);
		
		return listaDeEstudiantes;
	}
	
	
	
}
