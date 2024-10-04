package com.proyecto.springboot.app.service;

import java.util.List;

import com.proyecto.springboot.app.dto.InstitutoDTO;
import com.proyecto.springboot.app.entity.Instituto;

public interface InstitutoService {

	public List<InstitutoDTO> findAll();

	public InstitutoDTO findById(Long id);

	public InstitutoDTO save(InstitutoDTO institutoDTO);

	public Instituto update(Instituto instituto, Long id);

	public void deleteById(Long id);
}
