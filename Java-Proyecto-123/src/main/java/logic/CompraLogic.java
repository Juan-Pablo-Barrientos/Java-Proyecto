package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;


public class CompraLogic {

private DataCompra db= new DataCompra();
	
	public int NumeroDeCompras(int IdUsuario,int IdJuego) throws SQLException{
		try {
			return db.NumeroDeCompras(IdUsuario, IdJuego);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public int NumeroDeComprasHabilitadas(int IdUsuario,int IdJuego) throws SQLException{
		try {
			return db.NumeroDeComprasHabilitadas(IdUsuario, IdJuego);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<Compra> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public Compra getOne(Compra obj) throws SQLException {
		try {
			return this.getOne(obj.getNroSerie());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public Compra getOne(int i) throws SQLException {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public Compra getOneByReembolso(Reembolso obj) throws SQLException {
		try {
			return db.getOneByReembolso(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
		
	public Compra add(Compra obj) throws SQLException {
			try {
				return db.add(obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		
	}
	
	public void update(Compra obj) throws SQLException {
		 try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void updateIdReseña(Compra obj) throws SQLException {
		 try {
			db.updateIdReseña(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void updateIdReembolso(Compra obj) throws SQLException {
		 try {
			db.updateIdReembolso(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public void delete(Compra obj) throws SQLException {
		 try {
			db.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
