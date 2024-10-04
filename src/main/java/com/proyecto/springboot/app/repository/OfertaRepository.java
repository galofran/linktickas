package com.proyecto.springboot.app.repository;

import java.util.List;

import org.aspectj.weaver.reflect.Java15AnnotationFinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.springboot.app.entity.Empresa;
import com.proyecto.springboot.app.entity.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta,Long> {
	//@Query(value = "SELECT ofertas.nombre_oferta, ofertas.descripcion FROM empresas INNER JOIN ofertas ON empresas.id=ofertas.id_empresa WHERE empresas.id=:id",nativeQuery = true)
	//List <?> misOfertas(@Param("id") Long id );
	
	//@Query(value = "SELECT ofertas.nombre_oferta FROM ofertas WHERE id_empresa = :id",nativeQuery = true)
	//List<Oferta> findAllPorId(@Param("id") Long id);
	
	
	List<Oferta> findByCategoriaId(Long categoriaId);
	
	List<Oferta> findByEmpresaId(Long idEmpresa);
	
	List<Oferta> findByEstudiantesId(Long id);
	
	@Query("select o from Oferta o where o.nombreOferta like %?1%")
	List<Oferta> findByNombreOferta(String nombreDeOferta);
	
	
	
}
