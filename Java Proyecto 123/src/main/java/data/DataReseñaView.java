package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.Rese�aView;
import entities.Compra;
import entities.Juego;
import entities.Rese�a;
import entities.Usuario;

public class DataRese�aView {

    public LinkedList<Rese�aView> getAllByJuego(Juego juego) throws SQLException {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	LinkedList<Rese�aView> rese�aViewList = new LinkedList<>();

	try {
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "SELECT c.*, r.*, u.* FROM compra c JOIN rese�a r ON c.id_rese�a = r.id JOIN usuario u ON c.id_usuario = u.id WHERE c.id_juego = ?;");
	    stmt.setInt(1, juego.getId());
	    rs = stmt.executeQuery();

	    if (rs != null) {
		while (rs.next()) {
		    Rese�aView rese�aView = new Rese�aView();
		    // Compra
		    Compra c = new Compra();
		    c.setNroSerie(rs.getInt("nroSerie"));
		    c.setId_juego(rs.getInt("id_juego"));
		    c.setId_usuario(rs.getInt("id_usuario"));
		    c.setId_reembolso(rs.getInt("id_reembolso"));
		    c.setId_rese�a(rs.getInt("id_rese�a"));
		    c.setHoras_jugadas(rs.getDouble("horas_jugadas"));
		    c.setDateFechaHora(rs.getObject("fecha_compra", LocalDateTime.class));
		    rese�aView.setCompra(c);

		    // Usuario
		    Usuario u = new Usuario();
		    u.setId(rs.getInt("id_usuario"));
		    u.setNombreUsuario(rs.getString("nombre_usuario"));
		    u.setEmail(rs.getString("email"));
		    u.setNickname(rs.getString("nickname"));
		    u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		    u.setTelefono(rs.getString("telefono"));
		    u.setTipo(rs.getString("tipo"));
		    rese�aView.setUsuario(u);

		    // Rese�a
		    Rese�a r = new Rese�a();
		    r.setId(rs.getInt("id_rese�a"));
		    r.setTitulo(rs.getString("titulo"));
		    r.setDescripcion(rs.getString("descripcion"));
		    r.setPuntuacion(rs.getInt("puntuacion"));
		    rese�aView.setRese�a(r);

		    rese�aViewList.add(rese�aView);
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

	return rese�aViewList;
    }

    public Rese�aView getByJuegoYUsuario(Juego juego, Usuario usuario) throws SQLException {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Rese�aView rese�aView = new Rese�aView();

	try {
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "SELECT c.*, r.*, u.* FROM compra c LEFT JOIN rese�a r ON c.id_rese�a = r.id LEFT JOIN usuario u ON c.id_usuario = u.id WHERE c.id_usuario = ? AND c.id_juego = ?;");
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
		    c.setId_rese�a(rs.getInt("id_rese�a"));
		    c.setHoras_jugadas(rs.getDouble("horas_jugadas"));
		    c.setDateFechaHora(rs.getObject("fecha_compra", LocalDateTime.class));
		    rese�aView.setCompra(c);

		    // Usuario
		    Usuario u = new Usuario();
		    u.setId(rs.getInt("id_usuario"));
		    u.setNombreUsuario(rs.getString("nombre_usuario"));
		    u.setEmail(rs.getString("email"));
		    u.setNickname(rs.getString("nickname"));
		    u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		    u.setTelefono(rs.getString("telefono"));
		    u.setTipo(rs.getString("tipo"));
		    rese�aView.setUsuario(u);

		    // Rese�a
		    Rese�a r = new Rese�a();
		    r.setId(rs.getInt("id_rese�a"));
		    r.setTitulo(rs.getString("titulo"));
		    r.setDescripcion(rs.getString("descripcion"));
		    r.setPuntuacion(rs.getInt("puntuacion"));
		    rese�aView.setRese�a(r);
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

	return rese�aView;
    }

}
