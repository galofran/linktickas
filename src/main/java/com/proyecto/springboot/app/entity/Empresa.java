package com.proyecto.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.proyecto.springboot.app.dto.EmpresaDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresas")
@Data
@NoArgsConstructor
public class Empresa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nombre;
	private String ciudad;

	private String email;
	private String telefono;
	private String ubicacion;
	
	
	public Empresa(EmpresaDTO empresaDTO) {
		this.id = empresaDTO.getId();
		this.nombre = empresaDTO.getNombre();
		this.telefono = empresaDTO.getTelefono();
		this.ciudad = empresaDTO.getCiudad();
		this.ubicacion = empresaDTO.getUbicacion();
		this.email = empresaDTO.getEmail();
	}
	
	
}
