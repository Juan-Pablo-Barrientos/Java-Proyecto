package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataRese�aView;
import entities.Juego;
import entities.Rese�aView;
import entities.Usuario;

public class Rese�aViewLogic {
    private DataRese�aView db = new DataRese�aView();

    public LinkedList<Rese�aView> getAllByJuego(Juego juego) throws SQLException {
	try {
	    return db.getAllByJuego(juego);
	} catch (SQLException e) {
	    throw e;
	}

    }

    public Rese�aView getByJuegoYUsuario(Juego juego, Usuario usuario) throws SQLException {
	try {
	    return db.getByJuegoYUsuario(juego, usuario);
	} catch (SQLException e) {
	    throw e;
	}

    }

}
