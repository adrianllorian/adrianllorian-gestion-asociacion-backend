package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Socio;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private UsuarioDAOJpa usuarioDAO;

	@Override
	public boolean guardarUsuario(Socio usuario) {

		if (!usuario.equals(null)) {
			if (usuario.getRol() == null) {
				usuario.setRol("user");
			}
			usuarioDAO.save(usuario);
			return true;
		}

		else {
			return false; // el Usuario se encuenta en la base de datos
		}

	}

	@Override
	public boolean borrarUsuario(Socio usuario) {
		try {
			usuarioDAO.delete(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean borraUsuarioPorId(Socio socio) {

		try {
			usuarioDAO.deleteById(socio.getId());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Socio listarUsuario(Integer id) {
		try {
			Socio usuario = usuarioDAO.findById(id).get();
			return usuario;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Socio> listarUsuarios() {

		return usuarioDAO.findAll();
	}

	@Override
	public boolean editarUsuario(Socio usuario) {
		try {
			usuarioDAO.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean editarContraseña(Integer id, String contraseña) {
		Socio usuario = usuarioDAO.findById(id).get();
		usuario.setContraseña(contraseña);

		try {
			usuarioDAO.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// En Uso
	@Override
	public Socio verUsuarioPorDni(String dni) {
		try {
		return usuarioDAO.buscarPorDni(dni).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	
	@Override
	public Socio verUsarioPorId(Integer id) {
		try {

			return usuarioDAO.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Socio> buscador(Socio socio) {
		List<Socio> total = new ArrayList<Socio>();
		try {
			for (Socio e : usuarioDAO.buscarPorNombre(socio.getNombre())) {
				total.add(e);
			}
			for (Socio f : usuarioDAO.buscarPorApellido(socio.getNombre())) {
				total.add(f);
			}
			return total;
		}

		catch (Exception e) {
			return total;
		}
	}

}
