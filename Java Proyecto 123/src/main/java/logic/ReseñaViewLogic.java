package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataReseñaView;
import entities.Juego;
import entities.ReseñaView;
import entities.Usuario;

public class ReseñaViewLogic {
    private DataReseñaView db = new DataReseñaView();

    public LinkedList<ReseñaView> getAllByJuego(Juego juego) throws SQLException {
	try {
	    return db.getAllByJuego(juego);
	} catch (SQLException e) {
	    throw e;
	}

    }

    public ReseñaView getByJuegoYUsuario(Juego juego, Usuario usuario) throws SQLException {
	try {
	    return db.getByJuegoYUsuario(juego, usuario);
	} catch (SQLException e) {
	    throw e;
	}

    }

}
