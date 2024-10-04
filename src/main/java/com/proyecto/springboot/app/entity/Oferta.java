package com.proyecto.springboot.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proyecto.springboot.app.dto.OfertaDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ofertas")
@NoArgsConstructor
public class Oferta implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_oferta")
	private String nombreOferta;
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
		
	@ManyToMany(mappedBy = "ofertas",cascade = CascadeType.ALL)
	private Set<Estudiante> estudiantes = new HashSet<>();
	
	public Oferta(OfertaDTO ofertaDTO) {

		this.id = ofertaDTO.getId();
		this.nombreOferta = ofertaDTO.getNombreOferta();
		this.descripcion = ofertaDTO.getDescripcion();
		this.empresa = new Empresa(ofertaDTO.getEmpresaDTO());
		this.categoria = new Categoria(ofertaDTO.getCategoriaDTO());
		
	}
}
