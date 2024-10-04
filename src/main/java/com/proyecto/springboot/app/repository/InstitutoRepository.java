package com.proyecto.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.springboot.app.entity.Instituto;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long>{

}
