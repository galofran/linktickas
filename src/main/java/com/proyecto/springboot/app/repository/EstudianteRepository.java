package com.proyecto.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.springboot.app.entity.Estudiante;


@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante,Long>{

	Estudiante findByEmail(String correo);
}
