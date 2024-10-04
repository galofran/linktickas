package com.proyecto.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.springboot.app.dto.InstitutoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "institutos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instituto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String ciudad;
	private String ubicacion;
	
	public Instituto(InstitutoDTO institutoDTO) {
		this.id = institutoDTO.getId();
		this.nombre = institutoDTO.getNombre();
		this.ciudad = institutoDTO.getCiudad();
		this.ubicacion = institutoDTO.getUbicacion();
	}
}
