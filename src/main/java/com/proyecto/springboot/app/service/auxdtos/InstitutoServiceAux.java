package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.InstitutoDTO;
import com.proyecto.springboot.app.entity.Instituto;

@Service
public class InstitutoServiceAux {

	public Instituto crearInstituto(InstitutoDTO institutoDTO) {
		return new Instituto(institutoDTO);
	}
	
	public InstitutoDTO crearInstitutoDTO(Instituto instituto) {
		return new InstitutoDTO(instituto);
	}
	
	public List<InstitutoDTO> crearInstitutosDTO(List <Instituto> institutos){
		List<InstitutoDTO> institutosDTO = new ArrayList<>();
		
		institutos.stream().forEach(
				insti -> institutosDTO.add(crearInstitutoDTO(insti))
				);
		return institutosDTO;
	}
	
}
