package com.proyecto.springboot.app.service.auxdtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.app.dto.EmpresaDTO;
import com.proyecto.springboot.app.entity.Empresa;

@Service
public class EmpresaServiceAux {

	
	public Empresa crearEmpresa(EmpresaDTO empresaDTO) {
		return new Empresa(empresaDTO);
	}

	
	public EmpresaDTO crearEmpresaDTO(Empresa empresa) {
		return new EmpresaDTO(empresa);
	}

	public List<EmpresaDTO> crearEmpresasDTO(List<Empresa> listaEmpresas) {
		List<EmpresaDTO> empresasDTO = new ArrayList <>();
		listaEmpresas.stream().forEach(
				empresa -> {
					empresasDTO.add(crearEmpresaDTO(empresa));
				}
			);
		
		return empresasDTO;
	}
}
