package dao;



import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import model.Aportacion;
import model.Socio;
import model.Usuario;

public interface UsuarioDAO {

	Optional <Usuario> buscarParaLogin(String dni);
	
	List <Socio> listarSociosConAnotacion();
	List <Aportacion> listarAportacionPorIdSocio(Socio socio); //Me qedo aqui tengo que desarrollar este metodo para que me devuelva los aportes por id.usuario
	boolean borrarUsuarioYAportacion(Socio socio);
	Socio calcularAportacionPorSocio(Socio socio);
	List<Socio> calcularAportacionPorSocios(Socio socios);
	String guardarImagen(MultipartFile foto);
	byte[] verImagen(String nombreDeLaFoto);

	
	
}
