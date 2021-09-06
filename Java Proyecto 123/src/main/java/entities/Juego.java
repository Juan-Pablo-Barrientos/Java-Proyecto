package entities;


import java.util.Date;
public class Juego implements java.io.Serializable
{
	private int id;
	private int idPublicador;
	private int idDesarrollador;
	private String nombre;
	private String descripcion;
	private double precioBase;
	private double descuento;
	private String genero;
	private Date fecha_publicacion;
	private int reestriccionPorEdad;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPublicador() {
		return idPublicador;
	}
	public void setIdPublicador(int idPublicador) {
		this.idPublicador = idPublicador;
	}
	public int getIdDesarrollador() {
		return idDesarrollador;
	}
	public void setIdDesarrollador(int idDesarrollador) {
		this.idDesarrollador = idDesarrollador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}
	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	public int getReestriccionPorEdad() {
		return reestriccionPorEdad;
	}
	public void setReestriccionPorEdad(int reestriccionPorEdad) {
		this.reestriccionPorEdad = reestriccionPorEdad;
	}
	
}
