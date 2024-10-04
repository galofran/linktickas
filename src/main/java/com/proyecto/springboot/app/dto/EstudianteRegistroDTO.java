package com.proyecto.springboot.app.dto;

import lombok.Data;

@Data
public class EstudianteRegistroDTO {
	private String nombre;
	private String apellido;
	private Integer edad;
	private String email;
	private String telefono;
	private String direccion;
	private String password;
	private InstitutoDTO institutoDto;
	private GradoSuperiorDTO gradoSuperiorDto;	
}
