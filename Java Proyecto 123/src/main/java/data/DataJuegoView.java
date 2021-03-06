package data;
import entities.*;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class DataJuegoView {
	
	public LinkedList<JuegoView> getAll() throws SQLException{			
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<JuegoView> juegoViewlist= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select j.*,p.nombre as \"nombre_publicador\",d.nombre as \"nombre_desarrollador\" from juego as j \r\n"
					+ " join publicador as p on j.id_publicador = p.id\r\n"
					+ " join desarrollador as d on j.id_desarrollador=d.id\r\n"
					+ " where j.habilitado=1;");			
			if(rs!=null) {
				while(rs.next()) {	
					JuegoView juegoView= new JuegoView();
				    //Juego
				    Juego j=new Juego();
					j.setId(rs.getInt("id"));
					j.setIdPublicador(rs.getInt("id_publicador"));
					j.setIdDesarrollador(rs.getInt("id_desarrollador"));
					j.setNombre(rs.getString("nombre"));
					j.setDescripcion(rs.getString("descripcion"));
					j.setPrecioBase(rs.getDouble("precio_base"));
					j.setDescuento(rs.getDouble("descuento"));
					j.setGenero(rs.getString("genero"));
					j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
					j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));
					j.setUrl(rs.getString("url"));
					j.setTimeImage(rs.getString("timeImage"));
					juegoView.setJuego(j);
					//Publicador
					Publicador p = new Publicador();
					p.setId(rs.getInt("id_publicador"));
					p.setNombre(rs.getString("nombre_publicador"));
					juegoView.setPublicador(p);
					//Desarrollador
					Desarrollador d = new Desarrollador();
					d.setId(rs.getInt("id_desarrollador"));
					d.setNombre(rs.getString("nombre_desarrollador"));
					juegoView.setDesarrollador(d);	
					juegoViewlist.add(juegoView);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) { 
				e.printStackTrace();
				throw e;
			}
		}
			
		return juegoViewlist;
	}
	
	public LinkedList<JuegoView> getAllNotRelesed() throws SQLException{	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		LocalDate localDate = LocalDate.now();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<JuegoView> juegoViewlist= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement("select j.*,p.nombre as nombre_publicador,d.nombre as nombre_desarrollador from juego as j \r\n"
					+ "	join publicador as p on j.id_publicador = p.id\r\n"
					+ "	join desarrollador as d on j.id_desarrollador=d.id\r\n"
					+ " where j.habilitado=1 and fecha_publicacion>?;");
			stmt.setObject(1, dtf.format(localDate));
			rs= stmt.executeQuery();			
			if(rs!=null) {
				while(rs.next()) {	
					JuegoView juegoView= new JuegoView();
				    //Juego
				    Juego j=new Juego();
					j.setId(rs.getInt("id"));
					j.setIdPublicador(rs.getInt("id_publicador"));
					j.setIdDesarrollador(rs.getInt("id_desarrollador"));
					j.setNombre(rs.getString("nombre"));
					j.setDescripcion(rs.getString("descripcion"));
					j.setPrecioBase(rs.getDouble("precio_base"));
					j.setDescuento(rs.getDouble("descuento"));
					j.setGenero(rs.getString("genero"));
					j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
					j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));
					j.setUrl(rs.getString("url"));
					j.setTimeImage(rs.getString("timeImage"));
					juegoView.setJuego(j);
					//Publicador
					Publicador p = new Publicador();
					p.setId(rs.getInt("id_publicador"));
					p.setNombre(rs.getString("nombre_publicador"));
					juegoView.setPublicador(p);
					//Desarrollador
					Desarrollador d = new Desarrollador();
					d.setId(rs.getInt("id_desarrollador"));
					d.setNombre(rs.getString("nombre_desarrollador"));
					juegoView.setDesarrollador(d);	
					juegoViewlist.add(juegoView);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) { 
				e.printStackTrace();
				throw e;
			}
		}
			
		return juegoViewlist;
	}
	
	public JuegoView getOne(int Id) throws SQLException{	
		PreparedStatement stmt = null;
		ResultSet rs=null;
		JuegoView juegoView= new JuegoView();
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement("select j.*,p.nombre as \"nombre_publicador\",d.nombre as \"nombre_desarrollador\" from juego as j \r\n"
					+ " join publicador as p on j.id_publicador = p.id\r\n"
					+ " join desarrollador as d on j.id_desarrollador=d.id\r\n"
					+ " where j.habilitado=1 and j.id=?");
			stmt.setInt(1, Id);
			rs= stmt.executeQuery();

			if(rs!=null) {
				while(rs.next()) {
				    //Juego
				    Juego j=new Juego();
					j.setId(rs.getInt("id"));
					j.setIdPublicador(rs.getInt("id_publicador"));
					j.setIdDesarrollador(rs.getInt("id_desarrollador"));
					j.setNombre(rs.getString("nombre"));
					j.setDescripcion(rs.getString("descripcion"));
					j.setPrecioBase(rs.getDouble("precio_base"));
					j.setDescuento(rs.getDouble("descuento"));
					j.setGenero(rs.getString("genero"));
					j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
					j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));
					j.setUrl(rs.getString("url"));
					j.setTimeImage(rs.getString("timeImage"));
					juegoView.setJuego(j);
					//Publicador
					Publicador p = new Publicador();
					p.setId(rs.getInt("id_publicador"));
					p.setNombre(rs.getString("nombre_publicador"));
					juegoView.setPublicador(p);
					//Desarrollador
					Desarrollador d = new Desarrollador();
					d.setId(rs.getInt("id_desarrollador"));
					d.setNombre(rs.getString("nombre_desarrollador"));
					juegoView.setDesarrollador(d);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) { 
				e.printStackTrace();
				throw e;
			}
		}
			
		return juegoView;
	}

}
	


