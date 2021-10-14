package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class PublicadorLogic {
	
private DataPublicador db= new DataPublicador();
	
	public LinkedList<Publicador> getAll(){
		return db.getAll();
	}
	
	public Publicador getOne(Publicador obj) {
		return this.getOne(obj.getId());
		
	} 
	
	public Publicador getOne(int i) {
		return db.getOne(i);
		
	} 
		
	public Publicador add(Publicador obj) {
			return db.add(obj);
		
	}
	
	public void update(Publicador obj) {
		 db.update(obj);
		
	}
	public void delete(Publicador obj) {
		 db.delete(obj);
		
	}

	public void delete(int id) {
		db.delete(this.getOne(id));

	}
	
	 public boolean PublisherNameExist(String nombre) throws SQLException { 
			return	db.PublisherNameExist(nombre);	 
	    }

}
