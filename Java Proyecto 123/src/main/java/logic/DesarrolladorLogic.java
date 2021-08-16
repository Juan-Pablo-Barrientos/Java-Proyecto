package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class DesarrolladorLogic {
	
private DataDesarrollador db= new DataDesarrollador();
	
	public LinkedList<Desarrollador> getAll(){
		return db.getAll();
	}
	
	public Desarrollador getOne(Desarrollador obj) {
		return db.getOne(obj);
		
	} 
		
	public Desarrollador add(Desarrollador obj) {
			return db.add(obj);
		
	}
	
	public void update(Desarrollador obj) {
		 db.update(obj);
		
	}
	public void delete(Desarrollador obj) {
		 db.delete(obj);
		
	}

}
