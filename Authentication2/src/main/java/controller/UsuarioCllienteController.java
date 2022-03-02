package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Fotos;
import model.Socio;

//@CrossOrigin(origins = "*")
@Api(value = "Rutas para Socios")
@RestController
public class UsuarioCllienteController {

	String url = "https://usuarios";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@ApiOperation(value = "Devuelve el socio o la lista de socios dependiendo los permisos de usuario que se logea", response = List.class)
	@GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Socio> load(Principal p) {
		Socio us = new Socio();
		us.setDni(p.getName());
		Socio user = restTemplate.postForObject(url + "/listar-socio", us, Socio.class);
		if (user.getRol().equals("user")) {
			List<Socio> usuario = new ArrayList<Socio>();
			;
			usuario.add(usuarioDAO.calcularAportacionPorSocio(user));
			return usuario;
		} else if (user.getRol().equals("admin")) {
			return usuarioDAO.listarSociosConAnotacion();
		} else {
			return null;
		}

	}

	@ApiOperation(value = "Devuelve la informacion del socio logeado", response = List.class)
	@GetMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public Socio usuario(Principal p) {
		Socio us = new Socio();
		us.setDni(p.getName());
		Socio user = restTemplate.postForObject(url + "/listar-socio", us, Socio.class);
		/*
		 * Otra manera de hacerlo introduciendo headers
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<Usuario>
		 * requestEntity = new HttpEntity<Usuario>(us, headers); ResponseEntity<Usuario>
		 * response = restTemplate.postForEntity(url+"/listar-socio", requestEntity,
		 * Usuario.class); return response.getBody();
		 */

		return user;

	}

	@ApiOperation(value = "Devuelve la informacion de un socio y su aportacion ya calculada", response = List.class)
	@PostMapping(value = "/ver-socio", produces = MediaType.APPLICATION_JSON_VALUE)
	public Socio verUsuario(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio) {
		Socio soc = new Socio();
		soc = restTemplate.postForObject(url + "/ver-usuario", socio, Socio.class);

		return usuarioDAO.calcularAportacionPorSocio(soc);
	}

	@ApiOperation(value = "Devuelve la lista de todos los socios y su informacion", response = List.class)
	@GetMapping(value = "/listar-todos-usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Socio> listarUsarios() {
		return Arrays.asList(restTemplate.getForObject(url + "/listar-socios", Socio[].class));
	}

	

	@ApiOperation(value = "Busca los socios y devuelve una lista", response = List.class)
	@PostMapping(value = "/buscar-por-socio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Socio> buscarPorNombre(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio) {
		return usuarioDAO.calcularAportacionPorSocios(socio);
	}

	@ApiOperation(value = "Borra un socio", response = List.class)
	@PostMapping(value = "/borrar-socio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean borrarUsuario(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio) {
		return usuarioDAO.borrarUsuarioYAportacion(socio);
	}

	@ApiOperation(value = "Cambia la constrasela de un socio", response = List.class)
	@PostMapping(value = "/cambiar-contrasena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String cambiarContraseña(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio, Principal principal) {
		// boolean salida = restTemplate.put(url + "/cambiar-contrasena", socio,
		// boolean.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Socio> requestEntity = new HttpEntity<Socio>(socio, headers);
		ResponseEntity<String> response = restTemplate.exchange(url + "/cambiar-contrasena", HttpMethod.PUT,
				requestEntity, String.class);
		response.getHeaders().getLocation();
		response.getStatusCode();
		String responseBody = response.getBody();
		return response.getBody();
	}
	
	@ApiOperation(value = "Guarda un socio", response = List.class)
	@PostMapping(value = "/guardar-socio", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean guardarSocio(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio) {
		return restTemplate.postForObject(url + "/anadir-socio", socio, boolean.class);
	}

	@ApiOperation(value = "Guarda el socio con fotografia", response = List.class)
	@PostMapping(value = "/guardar-usuario-con-foto")
	public boolean guardarFotoDelantera(@ApiParam(value="Obejto Json Socio") @RequestParam("imagenDelantera") MultipartFile imagenDelantera,
			@RequestParam("imagenTrasera") MultipartFile imagenTrasera, @RequestParam("socio") String socio)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Socio modelDTO = mapper.readValue(socio, Socio.class);
		if (!imagenDelantera.isEmpty()) {
			String nombreFotoDelantera = usuarioDAO.guardarImagen(imagenDelantera);
			String nombreFotoTrasera = usuarioDAO.guardarImagen(imagenTrasera);
			if (!nombreFotoDelantera.equals(null)) {
				modelDTO.setFotoDniDelantera(nombreFotoDelantera);
			} else {
				return false;
			}
			if (!nombreFotoTrasera.equals(null)) {
				modelDTO.setFotoDniTrasera(nombreFotoTrasera);
			} else {
				return false;
			}

			return restTemplate.postForObject(url + "/anadir-socio", modelDTO, boolean.class);
		} else {

			return false;
		}
	}
	
	@ApiOperation(value = "Devuelve las fotos de un Socio", response = List.class)
	@PostMapping(value = "/extraerFotosUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Fotos extraerFotos(@ApiParam(value="Obejto Json Socio") @RequestBody Socio socio) {
		Socio user = restTemplate.postForObject(url + "/ver-usuario", socio, Socio.class);
		if (!user.getId().equals(null)) {
			Fotos fotos = new Fotos(user.getId(), usuarioDAO.verImagen(user.getFotoDniDelantera()),
					usuarioDAO.verImagen(user.getFotoDniTrasera()));
			return fotos;
		} else {
			System.out.println("No llega la foto");
			return null;
		}

	}

	/*
	 * imagenTrasera
	 * 
	 * @PostMapping(value="/guardar-foto-delantera") public boolean
	 * guardarFotoDelantera(@ModelAttribute("imagenDelantera") MultipartFile
	 * imagenDelantera, Principal principal) { if(imagenDelantera.isEmpty()) {
	 * System.out.println("No hay foto"); return false; } else {
	 * System.out.println("-- SI HAY FOTO -- DALE CARAJO..!!!" +
	 * principal.toString()); return true; } }
	 */
}
