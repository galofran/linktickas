package com.proyecto.springboot.app.dto;

import java.io.Serializable;
import com.proyecto.springboot.app.entity.Oferta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfertaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombreOferta;
	private String descripcion;
	private EmpresaDTO empresaDTO;
	private CategoriaDTO categoriaDTO;
	
	public OfertaDTO(Oferta oferta) {
		this.id = oferta.getId();
		this.nombreOferta = oferta.getNombreOferta();
		this.descripcion = oferta.getDescripcion();
		this.empresaDTO = new EmpresaDTO(oferta.getEmpresa());
		this.categoriaDTO = new CategoriaDTO(oferta.getCategoria());
	}
}	
