package com.proyecto.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.springboot.app.dto.OfertaDTO;
import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Oferta;

@Repository
public interface EmpresaRepository extends JpaRepository <Empresa,Long>{

	Optional <Empresa> findOneByNombre(String nombre);
	
	
}