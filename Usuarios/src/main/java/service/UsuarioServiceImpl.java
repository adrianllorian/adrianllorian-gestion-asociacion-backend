package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dao.UsuarioDAO;
import model.Socio;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Override
	public boolean guardarUsuario(Socio usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
		
		return usuarioDAO.guardarUsuario(usuario);
	}

	@Override
	public boolean borrarUsuario(Socio usuario) {
		
		return usuarioDAO.borrarUsuario(usuario);
	}

	@Override
	public boolean borraUsuarioPorId(Socio socio) {
		
		return usuarioDAO.borraUsuarioPorId(socio);
		
	}

	@Override
	public boolean editarUsuario(Socio usuario) {
	
		return usuarioDAO.editarUsuario(usuario);
	}

	@Override
	public boolean editarContraseña(Integer id, String contraseña) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		contraseña = passwordEncoder.encode(contraseña);
		return usuarioDAO.editarContraseña(id,contraseña);
	}

	@Override
	public Socio listarUsuario(Integer id) {
		
		return usuarioDAO.listarUsuario(id);
	}

	@Override
	public List<Socio> listarUsuarios() {
		
		return usuarioDAO.listarUsuarios();
	}

	//En Uso
	@Override
	public Socio verUsuarioPorDni(String dni) {
		return usuarioDAO.verUsuarioPorDni(dni);
	}

	@Override
	public Socio verUsuarioPorId(Socio socio) {	
		return usuarioDAO.verUsarioPorId(socio.getId());
	}

	@Override
	public List<Socio> buscador(Socio socio) {
		// TODO Auto-generated method stub
		return usuarioDAO.buscador(socio);
	}

	@Override
	public Socio buscarPorDni(String dni) {
		
		return usuarioDAO.verUsuarioPorDni(dni);
	}

	



	
	

}
