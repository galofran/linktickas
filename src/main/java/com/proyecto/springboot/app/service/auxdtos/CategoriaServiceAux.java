package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.CategoriaDTO;
import com.proyecto.springboot.app.entity.Categoria;


@Service
public class CategoriaServiceAux {
	
	public Categoria crearCategoria(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO);
	}

	
	public CategoriaDTO crearCategoriaDTO(Categoria categoria) {
		return new CategoriaDTO(categoria);
	}

	public List<CategoriaDTO> crearCategoriasDTO(List<Categoria> listaCategorias) {
		List<CategoriaDTO> categoriasDTO = new ArrayList <>();
		listaCategorias.stream().forEach(
				categoria -> {
					categoriasDTO.add(crearCategoriaDTO(categoria));
				}
			);
		
		return categoriasDTO;
	}
}
