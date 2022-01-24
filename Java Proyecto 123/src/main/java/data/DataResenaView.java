package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.ResenaView;
import entities.Compra;
import entities.Juego;
import entities.Resena;
import entities.Usuario;

public class DataResenaView {

    public LinkedList<ResenaView> getAllByJuego(Juego juego) throws SQLException {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	LinkedList<ResenaView> reseñaViewList = new LinkedList<>();

	try {
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "SELECT c.*, r.*, u.* FROM compra c JOIN reseña r ON c.id_reseña = r.id JOIN usuario u ON c.id_usuario = u.id WHERE c.id_juego = ?;");
	    stmt.setInt(1, juego.getId());
	    rs = stmt.executeQuery();

	    if (rs != null) {
		while (rs.next()) {
		    ResenaView reseñaView = new ResenaView();
		    // Compra
		    Compra c = new Compra();
		    c.setNroSerie(rs.getInt("nroSerie"));
		    c.setId_juego(rs.getInt("id_juego"));
		    c.setId_usuario(rs.getInt("id_usuario"));
		    c.setId_reembolso(rs.getInt("id_reembolso"));
		    c.setId_reseña(rs.getInt("id_reseña"));
		    c.setHoras_jugadas(rs.getDouble("horas_jugadas"));
		    c.setDateFechaHora(rs.getObject("fecha_compra", LocalDateTime.class));
		    reseñaView.setCompra(c);

		    // Usuario
		    Usuario u = new Usuario();
		    u.setId(rs.getInt("id_usuario"));
		    u.setNombreUsuario(rs.getString("nombre_usuario"));
		    u.setEmail(rs.getString("email"));
		    u.setNickname(rs.getString("nickname"));
		    u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		    u.setTelefono(rs.getString("telefono"));
		    u.setTipo(rs.getString("tipo"));
		    reseñaView.setUsuario(u);

		    // Reseña
		    Resena r = new Resena();
		    r.setId(rs.getInt("id_reseña"));
		    r.setTitulo(rs.getString("titulo"));
		    r.setDescripcion(rs.getString("descripcion"));
		    r.setPuntuacion(rs.getInt("puntuacion"));
		    reseñaView.setReseña(r);

		    reseñaViewList.add(reseñaView);
		}
	    }

	} catch (SQLException e) {
	    throw e;

	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (stmt != null) {
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    } catch (SQLException e) {
		throw e;
	    }
	}

	return reseñaViewList;
    }

    public ResenaView getByJuegoYUsuario(Juego juego, Usuario usuario) throws SQLException {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	ResenaView reseñaView = new ResenaView();

	try {
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "SELECT c.*, r.*, u.* FROM compra c LEFT JOIN reseña r ON c.id_reseña = r.id LEFT JOIN usuario u ON c.id_usuario = u.id WHERE c.id_usuario = ? AND c.id_juego = ?;");
	    stmt.setInt(1, usuario.getId());
	    stmt.setInt(2, juego.getId());
	    rs = stmt.executeQuery();

	    if (rs != null && rs.next()) {
		    // Compra
		    Compra c = new Compra();
		    c.setNroSerie(rs.getInt("nroSerie"));
		    c.setId_juego(rs.getInt("id_juego"));
		    c.setId_usuario(rs.getInt("id_usuario"));
		    c.setId_reembolso(rs.getInt("id_reembolso"));
		    c.setId_reseña(rs.getInt("id_reseña"));
		    c.setHoras_jugadas(rs.getDouble("horas_jugadas"));
		    c.setDateFechaHora(rs.getObject("fecha_compra", LocalDateTime.class));
		    reseñaView.setCompra(c);

		    // Usuario
		    Usuario u = new Usuario();
		    u.setId(rs.getInt("id_usuario"));
		    u.setNombreUsuario(rs.getString("nombre_usuario"));
		    u.setEmail(rs.getString("email"));
		    u.setNickname(rs.getString("nickname"));
		    u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		    u.setTelefono(rs.getString("telefono"));
		    u.setTipo(rs.getString("tipo"));
		    reseñaView.setUsuario(u);

		    // Reseña
		    Resena r = new Resena();
		    r.setId(rs.getInt("id_reseña"));
		    r.setTitulo(rs.getString("titulo"));
		    r.setDescripcion(rs.getString("descripcion"));
		    r.setPuntuacion(rs.getInt("puntuacion"));
		    reseñaView.setReseña(r);
	    }

	} catch (SQLException e) {
	    throw e;

	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (stmt != null) {
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    } catch (SQLException e) {
		throw e;
	    }
	}

	return reseñaView;
    }

}
