package com.proyecto.springboot.app.service;

import java.util.List;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.entity.Categoria;

public interface CategoriaService {

	public List<CategoriaDTO> findAll();

	public CategoriaDTO findById(Long id);

	public CategoriaDTO save(CategoriaDTO empresaDTO);

	public Categoria update(Categoria empresa, Long id);

	public void deleteById(Long id);
}
