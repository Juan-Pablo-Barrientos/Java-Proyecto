package entities;

import java.time.LocalDateTime;

public class Compra implements java.io.Serializable{
	
	private int nroSerie;
	private int id_juego;
	private int id_usuario;
	private int id_reembolso;
	private int id_reseña;
	private double horas_jugadas;
	private double importe;
	private LocalDateTime dateFechaHora;
	
	public LocalDateTime getDateFechaHora() {
		return dateFechaHora;
	}
	public void setDateFechaHora(LocalDateTime dateFechaHora) {
		this.dateFechaHora = dateFechaHora;
	}
	public int getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(int nroSerie) {
		this.nroSerie = nroSerie;
	}
	public int getId_juego() {
		return id_juego;
	}
	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public double getHoras_jugadas() {
		return horas_jugadas;
	}
	public void setHoras_jugadas(double horas_jugadas) {
		this.horas_jugadas = horas_jugadas;
	}
	public int getId_reembolso() {
		return id_reembolso;
	}
	public void setId_reembolso(int id_reembolso) {
		this.id_reembolso = id_reembolso;
	}
	public int getId_reseña() {
		return id_reseña;
	}
	public void setId_reseña(int id_reseña) {
		this.id_reseña = id_reseña;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
