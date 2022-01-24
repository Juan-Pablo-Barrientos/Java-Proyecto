package logic;

import java.sql.SQLException;
import java.util.LinkedList;
import data.DataJuegoView;
import entities.JuegoView;

public class JuegoViewLogic {
	
private DataJuegoView db= new DataJuegoView();
	
	public LinkedList<JuegoView> getAll() throws SQLException{
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public LinkedList<JuegoView> getAllNotRelesed() throws SQLException{
		try {
			return db.getAllNotRelesed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public JuegoView getOne(int Id) throws SQLException{
		try {
			return db.getOne(Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
