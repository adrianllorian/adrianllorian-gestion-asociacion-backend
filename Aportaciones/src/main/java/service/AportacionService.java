package service;

import java.util.List;

import model.Aportacion;

public interface AportacionService {

	boolean guardarAportacion(Aportacion aportacion);
	boolean borrarAportacion(Aportacion aportacion);
	boolean borrarAportacionPorId(Integer id);
	boolean actualizarAportacion(Aportacion aportacion);
	Aportacion buscarAportacionPorId(Integer id);
	List <Aportacion> listarAportaciones();
	void borrarAportacioPorUsuario(Aportacion aportacion);
	List<Aportacion> buscarAportacion(Aportacion aportacion);
	 
}
