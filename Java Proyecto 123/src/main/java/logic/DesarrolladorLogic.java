package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class DesarrolladorLogic {

	private DataDesarrollador db = new DataDesarrollador();

	public LinkedList<Desarrollador> getAll() throws SQLException {
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Desarrollador getOne(Desarrollador obj) throws SQLException {
		try {
			return this.getOne(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public Desarrollador getOne(int i) throws SQLException  {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Desarrollador add(Desarrollador obj) throws SQLException {
		try {
			return db.add(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void update(Desarrollador obj) throws SQLException {
		try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void delete(Desarrollador obj) throws SQLException {
		try {
			db.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public void delete(int id) throws SQLException {
		try {
			db.delete(this.getOne(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
	
	 public boolean DeveloperNameExist(String nombre) throws SQLException { 
			return	db.DeveloperNameExist(nombre);	 
	    }

}
