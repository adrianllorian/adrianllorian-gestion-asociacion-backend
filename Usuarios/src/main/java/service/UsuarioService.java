package service;

import java.util.List;

import model.Socio;

public interface UsuarioService {


	boolean guardarUsuario(Socio usuario);
	boolean borrarUsuario(Socio usuario);
	boolean borraUsuarioPorId(Socio socio);
	boolean editarUsuario(Socio usuario);
	boolean editarContraseña(Integer id, String contraseña);
	Socio listarUsuario(Integer id);
	List <Socio> listarUsuarios();
	Socio verUsuarioPorDni(String dni); //En uso
	Socio verUsuarioPorId(Socio socio);
	//Buscador
	List <Socio> buscador(Socio socio);
	Socio buscarPorDni(String dni);	
}
