package com.proyecto.springboot.app.service;

import java.util.List;

import com.proyecto.springboot.app.dto.GradoSuperiorDTO;
import com.proyecto.springboot.app.entity.GradoSuperior;


public interface GradoSuperiorService {
	public List<GradoSuperiorDTO> findAll();

	public GradoSuperiorDTO findById(Long id);

	public GradoSuperiorDTO save(GradoSuperiorDTO gsDto);

	public GradoSuperior update(GradoSuperior gs, Long id);

	public void deleteById(Long id);
}
