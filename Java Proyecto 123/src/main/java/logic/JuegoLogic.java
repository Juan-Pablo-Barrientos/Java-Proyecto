package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class JuegoLogic {
	
	private DataJuego db= new DataJuego();
	
	public LinkedList<Juego> getAll(){
		return db.getAll();
	}
	
	public Juego getOne(Juego obj) {
		return db.getOne(obj);
		
	}
	
	public Juego getOne(int i) {
		return db.getOne(i);
		
	}
		
	public Juego add(Juego obj) {
			return db.add(obj);
		
	}
	
	public void update(Juego obj) {
		 db.update(obj);
		
	}
	public void delete(Juego obj) {
		 db.delete(obj);
		
	}
	
}
