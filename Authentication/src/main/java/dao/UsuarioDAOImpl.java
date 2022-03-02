package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import model.Aportacion;
import model.Socio;
import model.Usuario;
import util.CrearNombreDeImagen;


@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	String urlUsuarios = "https://usuarios";

	String urlAportacion = "https://aportaciones";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioDAOJpa usuarioDAO;
	
	@Autowired 
	private CrearNombreDeImagen crearNombreImagen;
	
	@Override
	public Optional<Usuario> buscarParaLogin(String dni) {

		return usuarioDAO.buscarPorDni(dni);
	}


	
	@Override
	public List<Socio> listarSociosConAnotacion() {
		List<Socio> socios = Arrays.asList(restTemplate.getForObject(urlUsuarios + "/listar-socios", Socio[].class));
		List<Aportacion> aportacion = Arrays
				.asList(restTemplate.getForObject(urlAportacion + "/listar-aportacion", Aportacion[].class));
		for (Aportacion apor : aportacion) {
			for (Socio soc : socios) {
				if (apor.getIdUsuario().equals(soc.getId())) {
					if (apor.getEstado().equals("Aportacion")) {
						soc.setTotalAnotacion(soc.getTotalAnotacion() + apor.getAportacion());
					} else if (apor.getEstado().equals("Consumicion")) {
						soc.setTotalAnotacion(soc.getTotalAnotacion() - apor.getAportacion());
					}

				}
			}
		}
		return socios;
	}

	@Override
	public List<Aportacion> listarAportacionPorIdSocio(Socio socio) {
		List<Aportacion> aportacion = Arrays
				.asList(restTemplate.getForObject(urlAportacion + "/listar-aportacion", Aportacion[].class));
		List<Aportacion> salida = new ArrayList<Aportacion>();
		for (Aportacion apor : aportacion) {
			if (apor.getIdUsuario().equals(socio.getId())) {
				salida.add(apor);
			}
		}
		return salida;
	}

	@Override
	public boolean borrarUsuarioYAportacion(Socio socio) {
		Aportacion apor= new Aportacion();
		apor.setIdUsuario(socio.getId());
		try {
			restTemplate.postForObject(urlAportacion + "/borrar-aportacion-por-usuario", apor, boolean.class);
				try {
					restTemplate.postForObject(urlUsuarios + "/borrar-socio/", socio, boolean.class);
						return true;

				} catch (Exception e) {
					return false;
				}

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Socio calcularAportacionPorSocio(Socio socio) {
		List<Aportacion> aportacion = Arrays
				.asList(restTemplate.getForObject(urlAportacion + "/listar-aportacion", Aportacion[].class));
		double salida = 0;
		for (Aportacion apor : aportacion) {
			if (apor.getIdUsuario().equals(socio.getId())) {
				if(apor.getEstado().equals("Aportacion")) {
					salida = salida + apor.getAportacion();
				}
				else if(apor.getEstado().equals("Consumicion")) {
					salida = salida - apor.getAportacion();
				}
			}
		}
		socio.setTotalAnotacion(salida);
		return socio;
		
	}

	@Override
	public List<Socio> calcularAportacionPorSocios(Socio socio) {
		List <Socio> socios= Arrays.asList(restTemplate.postForObject(urlUsuarios + "/buscador-socio", socio, Socio[].class));
		List<Aportacion> aportacion = Arrays
				.asList(restTemplate.getForObject(urlAportacion + "/listar-aportacion", Aportacion[].class));
		
		List <Socio> salida = new ArrayList<Socio>();
		for(Socio soc: socios) {
			for(Aportacion aport : aportacion) {
				if(soc.getId().equals(aport.getIdUsuario())) {
					if(aport.getEstado().equals("Aportacion")) {
						soc.setTotalAnotacion(soc.getTotalAnotacion()+ aport.getAportacion());
					}
					else if(aport.getEstado().equals("Consumicion")) {
						soc.setTotalAnotacion(soc.getTotalAnotacion()- aport.getAportacion());
					}
				}
			}
			salida.add(soc);
			Collections.reverse(salida);
		}
		return salida;
	}

	@Override
	public String guardarImagen(MultipartFile foto) {
		Path directorioImagenes  = Paths.get("src//main//resources//static//images"); //Lugar donde guardar la imagen
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath(); //Pasaslo a String
		String[] extension = foto.getContentType().split("/"); //recoger el formatod e al foto
		String nombreDeLaFoto = crearNombreImagen.getClaveFinal() + "."+ extension[1]; //Preparar el nombred 
		try {
			byte [] bytesImg = foto.getBytes(); //Pasar la boto a un array de bytes
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreDeLaFoto); //Ruta completa donde voy a guardar la foto
			Files.write(rutaCompleta, bytesImg);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return nombreDeLaFoto;
	}

	

	@Override
	public byte[] verImagen(String nombreDeLaFoto) {
		String ruta = "src//main//resources//static//images/"+ nombreDeLaFoto;
		byte[] byteArray ;
		try {
			InputStream in = new FileInputStream(ruta);
			byteArray = in.readAllBytes();
			in.close();
			return byteArray;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	
	

}
