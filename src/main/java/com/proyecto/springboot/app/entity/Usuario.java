package com.proyecto.springboot.app.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Table(name = "usuarios", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }) })
@Entity
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;

	private String password;
	
	
	/*@Size(max = 100,message = "Limite excedido para nombre de empresa")
	@Column(name = "nombre_empresa")
	private String nombreEmpresa; 
	
	
	@Size(max = 100,message = "Limite excedido para ciudad de empresa")
	@Column(name = "ciudad_empresa")
	private String ciudadEmpresa;
	
	
	@Size(max = 50)
	@Column(name = "ubicacion_empresa")
	private String ubicacionEmpresa;
	
	
	@Size(max = 9)
	@Column(name = "telefono_empresa")
	private String telefonoEmpresa;
	
	
	@Column(name = "nombre_estudiante")
	private String nombreEstudiante;
	
	@Column(name = "apellido_estudiante")
	private String apellidoEstudiante;
	
	@Column(name = "edad_estudiante")
	private Integer edadEstudiante;
	
	@Column(name = "direccion_estudiante")
	private String direccionEstudiante;
	
	@Column(name = "telefono_estudiante")
	private String telefonoEstudiante;
	
	private Instituto instituto;
	
	@Column(name = "grado_superior")
	private GradoSuperior gradoSuperior;*/

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@JoinTable(name = "usuarios_roles",joinColumns = 
		@JoinColumn(name = "usuario_id",referencedColumnName = "id")
				,inverseJoinColumns = @JoinColumn(name="rol_id"))
	private Set<Rol> roles = new HashSet<>();
}
