package com.proyecto.springboot.app.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.dto.EstudianteDTO;
import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Estudiante;
import com.proyecto.springboot.app.entity.Oferta;
import com.proyecto.springboot.app.repository.EstudianteRepository;
import com.proyecto.springboot.app.repository.OfertaRepository;
import com.proyecto.springboot.app.service.OfertaService;
import com.proyecto.springboot.app.service.auxdtos.OfertaServiceAux;

@Service
public class OfertaServiceImpl implements OfertaService{
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
	private OfertaServiceAux ofertaServiceAux;
	
	@Override
	@Transactional(readOnly = true)
	public List<OfertaDTO> findAll() {
		return ofertaServiceAux.crearOfertasDTO(ofertaRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public OfertaDTO findById(Long id) {
		return ofertaServiceAux.crearOfertaDTO(ofertaRepository.findById(id).get());
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		ofertaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public OfertaDTO save(OfertaDTO ofertaDTO) {
		return ofertaServiceAux.crearOfertaDTO(ofertaRepository.save(ofertaServiceAux.crearOferta(ofertaDTO)));
	}

	
	@Override
	@Transactional
	public Oferta update(Oferta request,Long id) {
		Optional <Oferta> ofertaOp = ofertaRepository.findById(id);
		
		Oferta oferta = ofertaOp.get();
		oferta.setNombreOferta(request.getNombreOferta());
		oferta.setDescripcion(request.getDescripcion());
		oferta.setDescripcion(request.getDescripcion());
		oferta.setEmpresa(request.getEmpresa());
		//oferta.setCategoria(request.getCategoria());
		
		return ofertaRepository.save(oferta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OfertaDTO> buscarOfertasPorIdDeCategoria(Long idCategoria) {
		List<Oferta> ofertas = ofertaRepository.findByCategoriaId(idCategoria) ;
		
		return ofertaServiceAux.crearOfertasDTO(ofertas) ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OfertaDTO> buscarOfertasPorIdDeEmpresa(Long idEmpresa) {

		return ofertaServiceAux.crearOfertasDTO(ofertaRepository.findByEmpresaId(idEmpresa));
	}

	@Override
	@Transactional(readOnly = true)
	public List<OfertaDTO> buscarOfertasDeEstudiantePorSuId(Long idEstudiante) {
		
		return ofertaServiceAux.crearOfertasDTO(ofertaRepository.findByEstudiantesId(idEstudiante));
	}

	@Override
	@Transactional(readOnly = true)
	public List<OfertaDTO> buscarOfertasPorNombre(String nombreDeOferta) {
		return ofertaServiceAux.crearOfertasDTO(ofertaRepository.findByNombreOferta(nombreDeOferta)) ;
	}


}
