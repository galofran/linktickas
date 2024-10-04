package com.proyecto.springboot.app.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Oferta;

public interface EmpresaService {

	public List<EmpresaDTO> findAll();

	public EmpresaDTO findById(Long id);

	public EmpresaDTO save(EmpresaDTO empresaDTO);

	public Empresa update(Empresa empresa, Long id);

	public void deleteById(Long id);

}
