package com.proyecto.springboot.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.controllers.EstudianteControllerImpl;
import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.entity.Estudiante;
import com.proyecto.springboot.app.entity.Oferta;
import com.proyecto.springboot.app.entity.Usuario;
import com.proyecto.springboot.app.repository.EstudianteRepository;
import com.proyecto.springboot.app.repository.OfertaRepository;
import com.proyecto.springboot.app.repository.UsuarioRepositorio;
import com.proyecto.springboot.app.service.EstudianteService;
import com.proyecto.springboot.app.service.auxdtos.EstudianteServiceAux;

@Service
public class EstudianteServiceImpl implements EstudianteService {

	Logger logger = LoggerFactory.getLogger(EstudianteServiceImpl.class); 
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
	private OfertaRepository ofertaRepository;

	@Autowired 
	private EstudianteServiceAux estServiceAux;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstudianteDTO> findAll() {
		return estServiceAux.crearEstudiantesDto(estudianteRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public EstudianteDTO findById(Long idEstudiante) {
		return estServiceAux.crearEstudianteDto(estudianteRepository.findById(idEstudiante).get());
	}

	@Override
	@Transactional
	public void deleteById(Long idEstudiante) {
		estudianteRepository.deleteById(idEstudiante);
	}

	
	@Override
	@Transactional
	public EstudianteDTO save(EstudianteDTO estudianteDto) {
		/*System.out.println("************************");
		System.out.println(estudianteDto.getInstitutoDto());
		logger.info("CTMMMM ------>>> "+estudianteDto.getInstitutoDto());
		logger.info("CTMMMM ----->>> "+estudianteDto.getGradoSuperiorDto());
		System.out.println(estudianteDto.getGradoSuperiorDto());
		System.out.println("************************");*/
		
		return estServiceAux.crearEstudianteDto(
				estudianteRepository.save(
						estServiceAux.crearEstudiante(estudianteDto)));
	}

	@Override
	@Transactional
	public Estudiante updateById(Estudiante request, Long idEstudiante) {
		Optional <Estudiante> estudianteOp = estudianteRepository.findById(idEstudiante);
		Estudiante estudiante = estudianteOp.get();
		estudiante.setNombre(request.getNombre());
		estudiante.setApellido(request.getApellido());
		estudiante.setEdad(request.getEdad());
		estudiante.setEmail(request.getEmail());
		estudiante.setDireccion(request.getDireccion());
		//estudiante.setInstituto(request.getInstituto());
		estudiante.setGradoSuperior(request.getGradoSuperior());
		
		return estudianteRepository.save(estudiante);
	}

	@Override
	@Transactional(readOnly = true)
	public EstudianteDTO buscarUsuarioPorCorreo(String correo) {
		logger.info("CORREO -> " +correo);
		return estServiceAux.crearEstudianteDto(estudianteRepository.findByEmail(correo) );
	}

	
	@Override
	@Transactional
	public EstudianteDTO asignarOfertaAEstudiante(Long idEstudiante, Long idOferta) {
	
		List <Oferta> ofertas = null; 
		
		Estudiante estudiante = estudianteRepository.findById(idEstudiante).get();
		
		Oferta oferta = ofertaRepository.findById(idOferta).get();
		
		ofertas = estudiante.getOfertas();
		
		ofertas.add(oferta);
		
		estudiante.setOfertas(ofertas);
		
		
		return estServiceAux.crearEstudianteDto(estudianteRepository.save(estudiante));
	}

	@Override
	@Transactional
	public boolean borrarUsuarioEstudianteBuscandoPorCorreoYEliminandoPorId(String correo) {
		Optional <Usuario> usuario = usuarioRepositorio.findByEmail(correo);
		
		if(!usuario.isPresent()) {
			return false;
		}
		
		String emailUsuario = usuario.get().getEmail();
		
		Estudiante estudiante = estudianteRepository.findByEmail(emailUsuario);
		
		usuarioRepositorio.deleteById(usuario.get().getId());
		estudianteRepository.deleteById(estudiante.getId());
		
		return true;
	}
	
	

}
