package com.proyecto.springboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.entity.Categoria;
import com.proyecto.springboot.app.repository.CategoriaRepository;
import com.proyecto.springboot.app.service.CategoriaService;
import com.proyecto.springboot.app.service.auxdtos.CategoriaServiceAux;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaServiceAux categoriaServiceAux;

	@Override
	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		return categoriaServiceAux.crearCategoriasDTO(categoriaRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		return categoriaServiceAux.crearCategoriaDTO(categoriaRepository.findById(id).get());
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public CategoriaDTO save(CategoriaDTO categoriaDTO) {
		return categoriaServiceAux.crearCategoriaDTO(categoriaRepository.save(categoriaServiceAux.crearCategoria(categoriaDTO)));
	}

	@Override
	@Transactional
	public Categoria update(Categoria request, Long id) {
		
		Optional <Categoria> categoriaOp = categoriaRepository.findById(id);
		
		Categoria categoria = categoriaOp.get();
		
		categoria.setNombreCategoria(request.getNombreCategoria());
		
		return categoriaRepository.save(categoria);
	}
	

}
