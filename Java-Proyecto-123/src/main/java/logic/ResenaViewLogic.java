package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataResenaView;
import entities.Juego;
import entities.ResenaView;
import entities.Usuario;

public class ResenaViewLogic {
    private DataResenaView db = new DataResenaView();

    public LinkedList<ResenaView> getAllByJuego(Juego juego) throws SQLException {
	try {
	    return db.getAllByJuego(juego);
	} catch (SQLException e) {
	    throw e;
	}

    }

    public ResenaView getByJuegoYUsuario(Juego juego, Usuario usuario) throws SQLException {
	try {
	    return db.getByJuegoYUsuario(juego, usuario);
	} catch (SQLException e) {
	    throw e;
	}

    }

}
