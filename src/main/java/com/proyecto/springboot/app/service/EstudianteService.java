package com.proyecto.springboot.app.service;

import java.util.List;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.entity.Estudiante;

public interface EstudianteService {

	public List<EstudianteDTO> findAll();

	public EstudianteDTO findById(Long idEstudiante);
	
	public EstudianteDTO buscarUsuarioPorCorreo(String correo);

	public void deleteById(Long idEstudiante);

	public EstudianteDTO save(EstudianteDTO estudianteDto);

	public Estudiante updateById(Estudiante estudiante, Long idEstudiante);
	
	public EstudianteDTO asignarOfertaAEstudiante(Long idEstudiante, Long idOferta);
	
	public boolean borrarUsuarioEstudianteBuscandoPorCorreoYEliminandoPorId(String correo);
	
}
