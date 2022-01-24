package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class ReembolsoLogic {
	
private DataReembolso db= new DataReembolso();
	
	public LinkedList<Reembolso> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<Reembolso> getAllPendientes() throws SQLException{
		try {
			return db.getAllPendientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public Reembolso getOne(Reembolso obj) throws SQLException {
		try {
			return this.getOne(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public Reembolso getOne(int i) throws SQLException {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
		
	public Reembolso add(Reembolso obj) throws SQLException {
			try {
				return db.add(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		
	}
	
	public void update(Reembolso obj) throws SQLException {
		 try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void delete(Reembolso obj) throws SQLException {
		 try {
			db.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
