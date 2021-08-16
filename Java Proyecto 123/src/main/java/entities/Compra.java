package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.*;

public class Compra {
	
	private int nroSerie;
	private int id_juego;
	private int id_usuario;
	private int id_reembolso;
	private int id_reseña;
	private int horas_jugadas;
	private LocalDateTime dateFechaHora;
	private LocalDate dateFecha;
	private LocalTime dateHora;
	
	public LocalDateTime getDateFechaHora() {
		return dateFechaHora;
	}
	public void setDateFechaHora(LocalDateTime dateFechaHora) {
		this.dateFechaHora = dateFechaHora;
	}
	public LocalDate getDateFecha() {
		return dateFecha;
	}
	public void setDateFecha(LocalDate dateFecha) {
		this.dateFecha = dateFecha;
	}
	public LocalTime getDateHora() {
		return dateHora;
	}
	public void setDateHora(LocalTime dateHora) {
		this.dateHora = dateHora;
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
	public int getHoras_jugadas() {
		return horas_jugadas;
	}
	public void setHoras_jugadas(int horas_jugadas) {
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

}
