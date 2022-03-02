package model;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Socio  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String dni;	
	@Column
	private String nombre;
	@Column
	private String apellidos;	
	@Column
	private String telefono;
	@Column
	private String domicilio;
	@Column
	private String municipio;
	@Column
	private String provincia;
	@Column
	private String fotoDniDelantera;
	@Column
	private String fotoDniTrasera;
	@Column
	private String contraseña;	
	@Column
	private String rol;

	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getFotoDNI() {
		return fotoDniDelantera;
	}
	public void setFotoDNI(String fotoDNI) {
		this.fotoDniDelantera = fotoDNI;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getFotoDniDelantera() {
		return fotoDniDelantera;
	}
	public void setFotoDniDelantera(String fotoDniDelantera) {
		this.fotoDniDelantera = fotoDniDelantera;
	}
	public String getFotoDniTrasera() {
		return fotoDniTrasera;
	}
	public void setFotoDniTrasera(String fotoDniTrasera) {
		this.fotoDniTrasera = fotoDniTrasera;
	}
	

	
	
	
}
