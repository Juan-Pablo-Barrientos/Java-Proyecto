package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class ReseñaLogic {
	
	private DataReseña db= new DataReseña();
	
	public LinkedList<Reseña> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public Reseña getOne(Reseña obj) throws SQLException {
		try {
			return this.getOne(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public Reseña getOne(int i) throws SQLException {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
		
	public Reseña add(Reseña obj) throws SQLException {
			try {
				return db.add(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		
	}
	
	public void update(Reseña obj) throws SQLException {
		 try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void delete(Reseña obj) throws SQLException {
		 try {
			db.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
