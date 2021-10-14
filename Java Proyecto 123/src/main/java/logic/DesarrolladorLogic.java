package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class DesarrolladorLogic {

	private DataDesarrollador db = new DataDesarrollador();

	public LinkedList<Desarrollador> getAll() {
		return db.getAll();
	}

	public Desarrollador getOne(Desarrollador obj) {
		return this.getOne(obj.getId());

	}

	public Desarrollador getOne(int i)  {
		return db.getOne(i);
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

	public void delete(int id) {
		db.delete(this.getOne(id));

	}
	
	 public boolean DeveloperNameExist(String nombre) throws SQLException { 
			return	db.DeveloperNameExist(nombre);	 
	    }

}
