package com.proyecto.springboot.app.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.proyecto.springboot.app.entity.Estudiante;
import com.proyecto.springboot.app.entity.GradoSuperior;
import com.proyecto.springboot.app.entity.Instituto;
import com.proyecto.springboot.app.entity.Oferta;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EstudianteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String email;
	private String telefono;
	private String direccion;

	private InstitutoDTO institutoDto;

	private GradoSuperiorDTO gradoSuperiorDto;
	
	private List<OfertaDTO> ofertas = new ArrayList<>();
	
	public EstudianteDTO(Estudiante estudiante) {
		this.id = estudiante.getId();
		this.nombre = estudiante.getNombre();
		this.apellido = estudiante.getApellido();
		this.edad = estudiante.getEdad();
		this.email = estudiante.getEmail();
		this.telefono = estudiante.getTelefono();
		this.direccion = estudiante.getDireccion();
		this.institutoDto =  new InstitutoDTO(estudiante.getInstituto());
		this.gradoSuperiorDto = new GradoSuperiorDTO(estudiante.getGradoSuperior());
		this.ofertas = crearDTOdeSetOfertas(estudiante.getOfertas());
	}
	
	
	
	
	public OfertaDTO crearOfertaDTO(Oferta oferta) {
		return new OfertaDTO(oferta);
	}
	
	public List<OfertaDTO> crearDTOdeSetOfertas(List <Oferta> ofertas){
		List <OfertaDTO> ofertasDTO = new ArrayList<>();
		
		ofertas.stream().forEach(
			oferta -> {
				ofertasDTO.add(crearOfertaDTO(oferta));
			}
		);
		
		
		return ofertasDTO;
	}
	
}
