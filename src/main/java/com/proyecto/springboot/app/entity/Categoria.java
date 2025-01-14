package com.proyecto.springboot.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categorias")
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_categoria")
	private String nombreCategoria;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "categoria",orphanRemoval = true)
	private List<Oferta> ofertas = new ArrayList<>();
	
	public Categoria(CategoriaDTO categoriaDTO) {
		this.id = categoriaDTO.getId();
		this.nombreCategoria = categoriaDTO.getNombreCategoria();

		
	}
	

}
