package data;
import entities.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataJuegoView {
	
	public LinkedList<JuegoView> getAll(){			
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
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
			
		return juegoViewlist;
	}

}
	


