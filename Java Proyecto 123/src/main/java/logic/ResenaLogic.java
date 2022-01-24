package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class ResenaLogic {
	
	private DataResena db= new DataResena();
	
	public LinkedList<Resena> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public Resena getOne(Resena obj) throws SQLException {
		try {
			return this.getOne(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public Resena getOne(int i) throws SQLException {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
		
	public Resena add(Resena obj) throws SQLException {
			try {
				return db.add(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		
	}
	
	public void update(Resena obj) throws SQLException {
		 try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void delete(Resena obj) throws SQLException {
		 try {
			db.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
