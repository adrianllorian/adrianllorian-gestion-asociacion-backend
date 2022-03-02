package dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Socio;

public interface UsuarioDAOJpa extends JpaRepository <Socio,Integer> {

	
	
	@Transactional @Query(value="Select * from usuario u where u.dni=:dni",nativeQuery=true)
	Optional <Socio> buscarPorDni(@Param("dni") String dni);
	
	@Transactional @Query(value="Select * from usuario u where u.nombre LIKE %:nombre%",nativeQuery=true)
	List <Socio> buscarPorNombre(@Param("nombre") String nombre);
	
	@Transactional @Query(value="Select * from usuario u where u.apellidos LIKE %:apellidos%",nativeQuery=true)
	List <Socio> buscarPorApellido(@Param("apellidos") String apellidos);
	
	
	
}
