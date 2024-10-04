package com.proyecto.springboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.dto.InstitutoDTO;
import com.proyecto.springboot.app.entity.Instituto;
import com.proyecto.springboot.app.repository.InstitutoRepository;
import com.proyecto.springboot.app.service.InstitutoService;
import com.proyecto.springboot.app.service.auxdtos.InstitutoServiceAux;

@Service
public class InstitutoServiceImpl implements InstitutoService{

	@Autowired
	private InstitutoRepository instiRepository;
	
	@Autowired
	private InstitutoServiceAux instiServiceAux;
	
	@Override
	@Transactional(readOnly = true)
	public List<InstitutoDTO> findAll() {
		return instiServiceAux.crearInstitutosDTO(instiRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public InstitutoDTO findById(Long id) {
		return instiServiceAux.crearInstitutoDTO(instiRepository.findById(id).get());
	}

	@Override
	@Transactional
	public InstitutoDTO save(InstitutoDTO institutoDTO) {
		
		return instiServiceAux.crearInstitutoDTO(instiRepository.save(instiServiceAux.crearInstituto(institutoDTO)));
	}

	@Override
	@Transactional
	public Instituto update(Instituto instituto, Long id) {
		Optional <Instituto> instiOp = instiRepository.findById(id);
		Instituto insti = instiOp.get();
		insti.setNombre(instituto.getNombre());
		insti.setCiudad(instituto.getCiudad());
		insti.setUbicacion(instituto.getUbicacion());
		return instiRepository.save(insti);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		instiRepository.deleteById(id);
		
	}

}
