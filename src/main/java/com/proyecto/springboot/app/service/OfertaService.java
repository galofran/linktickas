package com.proyecto.springboot.app.service;

import java.util.List;
import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Oferta;

public interface OfertaService{
	public List<OfertaDTO> findAll();
	
	public List<OfertaDTO> buscarOfertasPorIdDeCategoria(Long idCategoria);
	
	public List<OfertaDTO> buscarOfertasPorIdDeEmpresa(Long idEmpresa);
	
	public List<OfertaDTO> buscarOfertasDeEstudiantePorSuId(Long idEstudiante);
	//public List<OfertaDTO> buscarOfertasDeEstudiantePorSuCorreo(String correo);

	public OfertaDTO findById(Long id);

	public OfertaDTO save(OfertaDTO ofertaDTO);

	public Oferta update(Oferta oferta, Long id);

	public void deleteById(Long id);
	
	public List<OfertaDTO> buscarOfertasPorNombre(String nombreDeOferta);
	
	
}
