package com.proyecto.springboot.app.dto;

import com.proyecto.springboot.app.entity.GradoSuperior;
import com.proyecto.springboot.app.entity.Instituto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class RegistroDto {
	
	private String nombreEmpresa; //
	private String ciudadEmpresa;
	private String ubicacionEmpresa;
	private String telefonoEmpresa;
	
	private String email;
	private String password;
	
	private String nombreEstudiante;
	private String apellidoEstudiante;
	private Integer edadEstudiante;
	private String direccionEstudiante;
	private String telefonoEstudiante;
	private Instituto instituto;
	private GradoSuperior gradoSuperior;
	
}
