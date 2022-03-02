package controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Aportacion;
import service.AportacionService;

@RestController
public class RutasControlador {

	@Autowired
	private AportacionService aportacionService;
	
	@PostMapping(value="/anadir-aportacion", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean aniadirAportacion(@RequestBody Aportacion aportacion) {
		return aportacionService.guardarAportacion(aportacion);
	}
	
	@DeleteMapping(value="/borrar-aportacion", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarAportacion(@RequestBody Aportacion aportacion) {
		return aportacionService.borrarAportacion(aportacion);
	}
	
	@DeleteMapping(value="/borrar-aportacion/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarAportacionPorId(@PathVariable Integer id) {
		return aportacionService.borrarAportacionPorId(id);
	}
	
	@PutMapping(value="/editar-aportacion",consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean editarAportacion(@RequestBody Aportacion aportacion) {
		return aportacionService.actualizarAportacion(aportacion);
	} 
	
	@GetMapping(value="/listar-aportacion/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Aportacion listarPortacionPorId(@PathVariable Integer id){
		return aportacionService.buscarAportacionPorId(id);
	}
	
	@GetMapping(value="/listar-aportacion", produces=MediaType.APPLICATION_JSON_VALUE)
	public List <Aportacion> listarAportacion(){
		return aportacionService.listarAportaciones();
	}
	
	@PostMapping(value="/borrar-aportacion-por-usuario", produces=MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarPorUsuario(@RequestBody Aportacion aportacion) {
		 aportacionService.borrarAportacioPorUsuario(aportacion);
		 return true;
	}
	
	@PostMapping(value="/buscador-aportacion", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List <Aportacion> buscadorAportacion(@RequestBody Aportacion aportacion){
		return aportacionService.buscarAportacion(aportacion); 
	} 
}
