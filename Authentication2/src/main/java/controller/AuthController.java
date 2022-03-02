package controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static util.Constans.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Usuario;

//@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	AuthenticationManager authManager;
	
	public AuthController(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	
	
	/*Cambiar este metodo para que devuelva un Usuario con el JWT*/
	
	@PostMapping(value = "/login", consumes= MediaType.APPLICATION_JSON_VALUE)
	public Usuario login(@RequestBody Usuario usuario) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getDni(),usuario.getPassword()));
		HttpHeaders header = new  HttpHeaders();
		String token ="";
		Usuario user =  new Usuario();
		if(authentication.isAuthenticated()) {
			 token= getToken(authentication);
			 header.add("Authorization", token); 
			 user.setContraseña(token);
			 user.setRol(authentication.getAuthorities().toString());;
		}
		else {
			 header.add("Authorization", "");
		}
		return user;
		
	}
	
	
	/*
	@PostMapping(value = "/login", consumes= MediaType.APPLICATION_JSON_VALUE)
	public Usuario login(@RequestBody Usuario usuario) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getDni(),usuario.getPassword()));
		HttpHeaders header = new  HttpHeaders();
		String token ="";
		Usuario user = new Usuario();
		
		
		if(authentication.isAuthenticated()) {
			 token= getToken(authentication);
			 user.setContraseña(token);
			 user.setRol(authentication.getAuthorities().toString());
			 header.add("Authorization", token); 
			 System.out.println("EL rol del usuario es" + user.getRol());
		}
		else {
			 header.add("Authorization", ""); 
		}
		return user;
		
	}
	*/
	
	private String getToken(Authentication authentication) {
		//en el body el token se incluye al usuario
		// y los roles a los que pertenece, además
		//de la fecha de caducidad y los datos de la firma
		
		String token = Jwts.builder()
				.setIssuedAt(new Date()) //Fecha de hoy
				.setSubject(authentication.getName()) // usuario 
				.claim("authorities", authentication.getAuthorities().stream() //roles
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setExpiration(new Date(System.currentTimeMillis() + TIEMPO_VIDA)) //fecha de caducidad
				.signWith(SignatureAlgorithm.HS512, CLAVE).compact(); // clave y algoritmo
		return token;
	}
	

	
	
}
