package com.proyecto.springboot.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.springboot.app.entity.GradoSuperior;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GradoSuperiorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String seccion;
	private InstitutoDTO institutoDto;
	
	public GradoSuperiorDTO(GradoSuperior gs) {
		this.id = gs.getId();
		this.nombre = gs.getNombre();
		this.seccion = gs.getSeccion();
		this.institutoDto = new InstitutoDTO(gs.getInstituto());
	}
}