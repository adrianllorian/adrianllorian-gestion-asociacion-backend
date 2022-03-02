package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Socio;
import service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
	

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping(value="/anadir-socio", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean aniadirSocio(@RequestBody Socio usuario) {
		return usuarioService.guardarUsuario(usuario);
	}
	
	@DeleteMapping(value="/borrar-socio", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarSocio(@RequestBody Socio usuario) {
		return usuarioService.borrarUsuario(usuario);
	}
	
	@PostMapping(value="/borrar-socio", produces=MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarSocioPorId(@RequestBody Socio socio) {
		return usuarioService.borraUsuarioPorId(socio);
	}
	
	@PutMapping(value="/editar-socio", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean editarSocio(@RequestBody Socio usuario) {
		return usuarioService.editarUsuario(usuario);
	}
	
	@PutMapping(value="/cambiar-contrasena", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean cambiarContraseña(@RequestBody Socio socio) {
		return usuarioService.editarContraseña(socio.getId(), socio.getContraseña());
	}
	
	@GetMapping(value="/listar-socios", produces=MediaType.APPLICATION_JSON_VALUE)
	public List <Socio> listarUsuarios(){
		return usuarioService.listarUsuarios();
	}
	
	@PostMapping(value="/listar-socio", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Socio verUsuario( @RequestBody Socio usuario){
		return usuarioService.verUsuarioPorDni(usuario.getDni());
	}
	
	@PostMapping(value="/ver-usuario", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Socio verUsuarioId( @RequestBody Socio socio){
		return usuarioService.verUsuarioPorId(socio);
	}
	

	@PostMapping(value="/buscador-socio", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List <Socio> buscarPorNombre( @RequestBody Socio socio){
		return usuarioService.buscador(socio);
	}
	
	@PostMapping(value="/buscar-por-dni", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Socio buscarPorDni(@RequestBody String dni) {
		return usuarioService.buscarPorDni(dni);
	}
	
	
}
