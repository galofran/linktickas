package com.proyecto.springboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.dto.GradoSuperiorDTO;
import com.proyecto.springboot.app.entity.GradoSuperior;
import com.proyecto.springboot.app.repository.GradoSuperiorRepository;
import com.proyecto.springboot.app.service.GradoSuperiorService;
import com.proyecto.springboot.app.service.auxdtos.GradoSuperiorServiceAux;

@Service
public class GradoSuperiorServiceImpl implements GradoSuperiorService{

	@Autowired
	private GradoSuperiorRepository gsRepository;
	
	@Autowired
	private GradoSuperiorServiceAux gsAux;
	
	@Override
	@Transactional(readOnly = true)
	public List<GradoSuperiorDTO> findAll() {
		return gsAux.crearGsDTO(gsRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public GradoSuperiorDTO findById(Long id) {
		return gsAux.crearGsDTO(gsRepository.findById(id).get());
	}

	@Override
	@Transactional
	public GradoSuperiorDTO save(GradoSuperiorDTO gsDto) {
		return gsAux.crearGsDTO(gsRepository.save(gsAux.crearGradoSuperior(gsDto)));
	}

	@Override
	@Transactional
	public GradoSuperior update(GradoSuperior gs, Long id) {

		Optional <GradoSuperior> gsOp = gsRepository.findById(id);
		GradoSuperior gradoSuperior = gsOp.get() ;
		gradoSuperior.setNombre(gs.getNombre());
		gradoSuperior.setSeccion(gs.getSeccion());
		gradoSuperior.setInstituto(gs.getInstituto());
		
		return gsRepository.save(gradoSuperior);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		gsRepository.deleteById(id);
	}

}
