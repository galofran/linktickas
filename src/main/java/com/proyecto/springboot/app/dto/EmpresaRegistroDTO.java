package com.proyecto.springboot.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class EmpresaRegistroDTO {

	private String nombre;
	private String ciudad;
	private String email;
	private String telefono;
	private String ubicacion;
	private String password;
}
