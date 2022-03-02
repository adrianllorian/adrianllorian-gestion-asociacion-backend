package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Aportacion;

@Repository
public class AportacionDAOImpl implements AportacionDAO {

	@Autowired
	private AportacionDAOJpa aportacionDAO;

	@Override
	public boolean guardarAportacion(Aportacion aportacion) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" HH:mm:ss dd/MM/yyyy");
        aportacion.setFecha(dtf.format(LocalDateTime.now()));
		try {
			aportacionDAO.save(aportacion);
			return true;
		}

		catch(Exception e) {
			return false; // el Usuario se encuenta en la base de datos
		}

	}

	@Override
	public boolean borrarAportacion(Aportacion aportacion) {
		try {
			aportacionDAO.delete(aportacion);
			return true;
		} catch(Exception e) {
			return false; // el Usuario se encuenta en la base de datos
		}
	}

	@Override
	public boolean borrarAportacionPorId(Integer id) {
		try {
			aportacionDAO.deleteById(id);

			return true;
		} catch (Exception e) {
			return false; // el Usuario se encuenta en la base de datos
		}
	}

	@Override
	public boolean actualizarAportacion(Aportacion aportacion) {
		try {
			aportacionDAO.save(aportacion);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Aportacion buscarAportacionPorId(Integer id) {
		try {
			return aportacionDAO.findById(id).get();
		} catch (Exception e) {
			return new Aportacion();
		}

	}

	@Override
	public List<Aportacion> listarAportaciones() {
		try {
			return aportacionDAO.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
		} catch (Exception e) {
			return null;
		}
	}

	
	@Override
	public void borrarAportacioPorUsuario(Aportacion aportacion) {
		try {
		 aportacionDAO.borrarAportacionPorUsuaurio(aportacion.getIdUsuario());
		}
		catch(Exception e ){
			
		}
		
	}

	@Override
	public List<Aportacion> buscadorAportacion(String descripcinon, String estado, String fecha) {
		return aportacionDAO.buscadorAportacion( descripcinon,  estado,  fecha);
		
	}

	
}
