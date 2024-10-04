package com.proyecto.springboot.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.springboot.app.entity.Empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String ciudad;
	private String ubicacion;
	private String telefono;
	private String email;
	
	public EmpresaDTO(Empresa empresa) {
		this.id = empresa.getId();
		this.nombre = empresa.getNombre();
		this.ciudad = empresa.getCiudad();
		this.ubicacion = empresa.getUbicacion();
		this.telefono = empresa.getTelefono();
		this.email = empresa.getEmail();
	}
	
}
