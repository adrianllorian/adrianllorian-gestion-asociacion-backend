package model;


public class Fotos {

	Integer id;
	byte[] fotoDelantera;
	byte[] fotoTrasera;
	
	
	
	public Fotos(Integer id, byte[] fotoDelantera, byte[] fotoTrasera) {
		super();
		this.id = id;
		this.fotoDelantera = fotoDelantera;
		this.fotoTrasera = fotoTrasera;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getFotoDelantera() {
		return fotoDelantera;
	}
	public void setFotoDelantera(byte[] fotoDelantera) {
		this.fotoDelantera = fotoDelantera;
	}
	public byte[] getFotoTrasera() {
		return fotoTrasera;
	}
	public void setFotoTrasera(byte[] fotoTrasera) {
		this.fotoTrasera = fotoTrasera;
	}
	
	
}
