package dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Usuario;

public interface UsuarioDAOJpa extends JpaRepository <Usuario,Integer> {

	
	
	@Transactional @Query(value="Select * from usuario where dni=:dni",nativeQuery=true)
	Optional <Usuario> buscarPorDni(@Param("dni") String dni);
}
