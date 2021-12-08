package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class JuegoLogic {

	private DataJuego db = new DataJuego();

	public LinkedList<Juego> getAll() throws SQLException {
		try {
			return db.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Juego> getAllNotRelesed() throws SQLException {
		try {
			return db.getAllNotRelesed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public LinkedList<Juego> search(String name) throws SQLException {
		try {
			return db.search(name);
		} catch (SQLException e) {
			throw e;
		}

	}

	public Juego getOne(Juego obj) throws SQLException {
		try {
			return this.getOne(obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Juego getOne(int i) throws SQLException {
		try {
			return db.getOne(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Juego add(Juego obj) throws SQLException {
		try {
			return db.add(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void update(Juego obj) throws SQLException {
		try {
			db.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void delete(Juego obj) throws SQLException {
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

	public boolean GameNameExist(String nombre) throws SQLException {
		return db.GameNameExist(nombre);
	}

}
