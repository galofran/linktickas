package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Oferta;
@Service
public class OfertaServiceAux {

	
	public Oferta crearOferta(OfertaDTO ofertaDTO) {
		return new Oferta(ofertaDTO);
	}

	
	public OfertaDTO crearOfertaDTO(Oferta oferta) {
		return new OfertaDTO(oferta);
	}

	public List<OfertaDTO> crearOfertasDTO(List<Oferta> ofertas) {
		List<OfertaDTO> ofertasDTO = new ArrayList <>();
		ofertas.stream().forEach(
				oferta -> {
					ofertasDTO.add(crearOfertaDTO(oferta));
				}
			);
		
		return ofertasDTO;
	}
	

	
}
