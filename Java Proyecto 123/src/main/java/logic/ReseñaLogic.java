package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class ReseñaLogic {
	
	private DataReseña db= new DataReseña();
	
	public LinkedList<Reseña> getAll(){
		return db.getAll();
	}
	
	public Reseña getOne(Reseña obj) {
		return db.getOne(obj);
		
	} 
		
	public Reseña add(Reseña obj) {
			return db.add(obj);
		
	}
	
	public void update(Reseña obj) {
		 db.update(obj);
		
	}
	public void delete(Reseña obj) {
		 db.delete(obj);
		
	}

}
