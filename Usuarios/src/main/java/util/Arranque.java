package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dao.UsuarioDAO;
import model.Socio;

@Component
public class Arranque implements ApplicationListener<ContextRefreshedEvent>{
 
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		Socio s = new Socio();
		s.setId(1);
		s.setDni("00000000Z");
		s.setNombre("Admin");
		s.setContraseña(passwordEncoder.encode("admin"));
		s.setApellidos("All Privileges");
		s.setTelefono("123123123");
		s.setDomicilio("Calle Puerta del Sol");
		s.setMunicipio("Madrid");
		s.setProvincia("Madrid");
		s.setFotoDniDelantera("pruebaA.jpg");
		s.setFotoDniTrasera("pruebaB.jpg");
		s.setRol("admin");
		
		Socio s1 = new Socio();
		s1.setId(2);
		s1.setDni("00000001Z");
		s1.setNombre("User");
		s1.setContraseña(passwordEncoder.encode("user"));
		s1.setApellidos("All Privileges");
		s1.setTelefono("123123123");
		s1.setDomicilio("Calle Puerta del Sol");
		s1.setMunicipio("Madrid");
		s1.setProvincia("Madrid");
		s1.setFotoDniDelantera("pruebaA.jpg");
		s1.setFotoDniTrasera("pruebaB.jpg");
		s1.setRol("user");
		
		usuarioDAO.guardarUsuario(s);
		usuarioDAO.guardarUsuario(s1);
	}
	
}
