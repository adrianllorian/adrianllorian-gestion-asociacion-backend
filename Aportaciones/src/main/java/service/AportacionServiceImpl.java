package service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AportacionDAO;
import model.Aportacion;

@Service
public class AportacionServiceImpl implements AportacionService {
	
	@Autowired
	private AportacionDAO aportacionDAO;

	@Override
	public boolean guardarAportacion(Aportacion aportacion) {
		// TODO Auto-generated method stub
		return aportacionDAO.guardarAportacion(aportacion);
	}

	@Override
	public boolean borrarAportacion(Aportacion aportacion) {
		// TODO Auto-generated method stub
		return aportacionDAO.borrarAportacion(aportacion);
	}

	@Override
	public boolean borrarAportacionPorId(Integer id) {
		// TODO Auto-generated method stub
		return aportacionDAO.borrarAportacionPorId(id);
	}

	@Override
	public boolean actualizarAportacion(Aportacion aportacion) {
		// TODO Auto-generated method stub
		return aportacionDAO.actualizarAportacion(aportacion);
	}

	@Override
	public Aportacion buscarAportacionPorId(Integer id) {
		// TODO Auto-generated method stub
		return aportacionDAO.buscarAportacionPorId(id);
	}

	@Override
	public List<Aportacion> listarAportaciones() {
		// TODO Auto-generated method stub
		return aportacionDAO.listarAportaciones();
	}

	@Override
	public void borrarAportacioPorUsuario(Aportacion aportacion) {
		// TODO Auto-generated method stub
		 aportacionDAO.borrarAportacioPorUsuario(aportacion);
	}

	@Override
	public List<Aportacion> buscarAportacion(Aportacion aportacion) {
		return aportacionDAO.buscadorAportacion(aportacion.getDescripcion(), aportacion.getEstado(), aportacion.getFecha());			
	}
	
	

}
