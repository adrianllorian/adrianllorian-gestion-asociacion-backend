package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dao.UsuarioDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Aportacion;
import model.Socio;

//@CrossOrigin(origins = "*")
@Api(value = "Rutas para Aportaciones")
@RestController
public class AportacionClienteController {
	

	String url = "https://aportaciones";
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@ApiOperation(value = "Guarda una aportacion de un socio", response = List.class)
	@PostMapping(value="/guardarAportacion", consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean guardarAportacion(@ApiParam(value="Obejto Json Aportacion")@RequestBody Aportacion aportacion) {
		boolean respuesta = restTemplate.postForObject(url + "/anadir-aportacion", aportacion, boolean.class);
		return respuesta;
	}
	
	@ApiOperation(value = "Devuelve las aportaciones de un socio", response = List.class)
	@PostMapping(value="/verAportacion", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List <Aportacion> listarAportacionPorId(@ApiParam(value="Obejto Json Aportacion") @RequestBody Socio socio){
		List<Aportacion> salida= usuarioDAO.listarAportacionPorIdSocio(socio);
		Collections.reverse(salida);
		return salida;
	}
	
	@ApiOperation(value = "Devuelve todas las aportaciones", response = List.class)
	@GetMapping(value="/ver-todas-aportaciones", produces=MediaType.APPLICATION_JSON_VALUE)
	public List <Aportacion> listarTodasLasAportaciones(){
		return Arrays.asList(restTemplate.getForObject(url +"/listar-aportacion", Aportacion[].class));
	}
	
	@ApiOperation(value = "Busca la apaortacion de devuelve una lista de aportaciones", response = List.class)
	@PostMapping(value="/buscador-aportaciones", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List <Aportacion> buscadorAportacion(@ApiParam(value="Obejto Json Aportacion") @RequestBody Aportacion aportacion){
		System.out.println("La apottacion que llea a la autenticacione es " + aportacion.getDescripcion());
		List <Aportacion> aportaciones = new ArrayList <Aportacion>(); 
		aportaciones = Arrays.asList(restTemplate.postForObject(url + "/buscador-aportacion", aportacion, Aportacion[].class));
		Collections.reverse(aportaciones);
		return aportaciones;
	}
	
	
}
