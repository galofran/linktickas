package com.proyecto.springboot.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Oferta;
import com.proyecto.springboot.app.repository.EmpresaRepository;
import com.proyecto.springboot.app.service.EmpresaService;
import com.proyecto.springboot.app.service.auxdtos.EmpresaServiceAux;
import com.proyecto.springboot.app.service.auxdtos.OfertaServiceAux;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaServiceAux empresaServiceAux;
	
	@Override
	@Transactional(readOnly = true)
	public List<EmpresaDTO> findAll() {
		return empresaServiceAux.crearEmpresasDTO(empresaRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaDTO findById(Long id) {
		return empresaServiceAux.crearEmpresaDTO(empresaRepository.findById(id).get());
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		empresaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public EmpresaDTO save(EmpresaDTO empresaDTO) {
		return empresaServiceAux.crearEmpresaDTO(empresaRepository.save(empresaServiceAux.crearEmpresa(empresaDTO)));
	}

	@Override
	@Transactional
	public Empresa update(Empresa request,Long id) {
		
	Optional <Empresa> empresaOp = empresaRepository.findById(id);

	Empresa empresa = empresaOp.get();
	
	empresa.setNombre(request.getNombre());
	empresa.setCiudad(request.getCiudad());
	empresa.setUbicacion(request.getUbicacion());
		
	return empresaRepository.save(empresa);
	}


}
