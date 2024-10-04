package com.proyecto.springboot.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormularioContacto {
	
	private String nombre;
	private String apellidos;
	private String correo;
	private String descripcion;
}
