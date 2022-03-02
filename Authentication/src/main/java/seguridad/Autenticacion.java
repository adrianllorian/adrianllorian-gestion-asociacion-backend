package seguridad;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dao.UsuarioDAO;
import model.Usuario;
@Service
public class Autenticacion implements UserDetailsService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		Optional <Usuario> user = usuarioDAO.buscarParaLogin(dni);
		
		if(user.isPresent()) {
			return user.get(); 
		}
		else {
			throw new  UsernameNotFoundException("" + dni);
			
			
		}
		
	}

}
