package com.proyecto.springboot.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.springboot.app.controllers.AuthControlador;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "estudiantes")
@NoArgsConstructor
public class Estudiante implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String email;
	//@Column(name = "telefono_estudiante")
	private String telefono;
	private String direccion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instituto_id")
	private Instituto instituto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gradosuperior_id")
	private GradoSuperior gradoSuperior;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ofertas_estudiantes",joinColumns = 
	@JoinColumn(name = "id_estudiante",referencedColumnName = "id")
			,inverseJoinColumns = @JoinColumn(name="id_oferta"))
	private List<Oferta> ofertas = new ArrayList<>();

	public Estudiante(EstudianteDTO estudianteDto) {
		this.id = estudianteDto.getId();
		this.nombre = estudianteDto.getNombre();
		this.apellido = estudianteDto.getApellido();
		this.edad = estudianteDto.getEdad();
		this.email = estudianteDto.getEmail();
		this.telefono = estudianteDto.getTelefono();
		this.direccion = estudianteDto.getDireccion();
		this.instituto = new Instituto(estudianteDto.getInstitutoDto());
		this.gradoSuperior = new GradoSuperior(estudianteDto.getGradoSuperiorDto());
		this.ofertas = crearSetOfertas(estudianteDto.getOfertas());
	}
	
	
	
	public Oferta crearOferta(OfertaDTO ofertaDTO) {
		return new Oferta(ofertaDTO);
	}
	
	public List<Oferta> crearSetOfertas(List <OfertaDTO> ofertasDTO){
		List <Oferta> ofertas = new ArrayList<>();
		
		ofertasDTO.stream().forEach(
			oferta -> {
				ofertas.add(crearOferta(oferta));
			}
		);
		
		
		return ofertas;
	}
	
}
