package dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Aportacion;

public interface AportacionDAOJpa extends JpaRepository <Aportacion,Integer>{

	@Modifying
	@Transactional
	@Query(value="Delete from aportacion where id_usuario=:idUsuario", nativeQuery=true)
	void borrarAportacionPorUsuaurio(@Param("idUsuario") Integer idUsuario);
	
	@Transactional @Query(value="select * from aportacion a where (a.descripcion like %:descripcinon%) or (a.estado like %:estado%) or (a.fecha like %:fecha%);",nativeQuery=true)
	List <Aportacion> buscadorAportacion(@Param("descripcinon") String descripcinon, @Param("estado") String estado, @Param("fecha") String fecha);
	
	
}
