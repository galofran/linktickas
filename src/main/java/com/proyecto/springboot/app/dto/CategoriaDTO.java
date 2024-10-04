package com.proyecto.springboot.app.dto;

import java.io.Serializable;

import com.proyecto.springboot.app.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6045896036840063299L;
	private Long id;
	private String nombreCategoria;

	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nombreCategoria = categoria.getNombreCategoria();
		
	}
	
	

}
