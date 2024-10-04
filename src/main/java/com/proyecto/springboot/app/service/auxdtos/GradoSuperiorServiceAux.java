package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.GradoSuperiorDTO;
import com.proyecto.springboot.app.entity.GradoSuperior;


@Service
public class GradoSuperiorServiceAux {
	public GradoSuperior crearGradoSuperior(GradoSuperiorDTO gsDto) {
		return new GradoSuperior(gsDto);
	}
	
	public GradoSuperiorDTO crearGsDTO(GradoSuperior gs) {
		return new GradoSuperiorDTO(gs);
	}
	
	public List<GradoSuperiorDTO> crearGsDTO(List <GradoSuperior> gradosSuperiores){
		List<GradoSuperiorDTO> gsDtos = new ArrayList<>();
		
		gradosSuperiores.stream().forEach(
				gs -> gsDtos.add(crearGsDTO(gs))
				);
		
		return gsDtos;
	}
	
	
}
