package com.proyecto.springboot.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.springboot.app.entity.Instituto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InstitutoDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String ciudad;
	private String ubicacion;
	
	public InstitutoDTO(Instituto instituto) {
		this.id = instituto.getId();
		this.nombre = instituto.getNombre();
		this.ciudad = instituto.getCiudad();
		this.ubicacion = instituto.getUbicacion();
	}
	

}
