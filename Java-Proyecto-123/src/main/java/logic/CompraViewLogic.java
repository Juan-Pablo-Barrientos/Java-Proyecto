package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataCompraView;
import entities.CompraView;

public class CompraViewLogic {
	
private DataCompraView db= new DataCompraView();
	
	public LinkedList<CompraView> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<CompraView> getAllByUserId(int id) throws SQLException{
		try {
			return db.getAllByUserId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
