package dao;


import java.util.List;
import model.Socio;

public interface UsuarioDAO {

	boolean guardarUsuario(Socio usuario);
	boolean borrarUsuario(Socio usuario);
	boolean borraUsuarioPorId(Socio socio);
	boolean editarUsuario(Socio usuario);
	boolean editarContraseña(Integer id, String contraseña);
	Socio listarUsuario(Integer id);
	Socio verUsuarioPorDni(String dni); //En Uso
	List <Socio> listarUsuarios();
	Socio verUsarioPorId(Integer id);
	
	//Buscador
	List <Socio> buscador(Socio socio);
	
	
}
