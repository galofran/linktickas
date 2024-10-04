package com.proyecto.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.springboot.app.dto.GradoSuperiorDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "grados_superiores")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GradoSuperior implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String seccion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instituto_formacion")
	private Instituto instituto;

	public GradoSuperior(GradoSuperiorDTO gsDto) {
		this.id = gsDto.getId();
		this.nombre = gsDto.getNombre();
		this.seccion = gsDto.getSeccion();
		this.instituto = new Instituto(gsDto.getInstitutoDto());
	}
}
