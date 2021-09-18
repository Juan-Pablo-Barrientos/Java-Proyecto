package data;

import entities.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataCompraView {
		
	public LinkedList<CompraView> getAll(){			
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<CompraView> compraViewList= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT * from compra as c "
					+ "join juego as j on j.id = c.id_juego "
					+ "join usuario as u  on u.id = c.id_usuario "
					+ "join reembolso as r on r.id = c.id_reembolso");			
			if(rs!=null) {
				while(rs.next()) {					
					CompraView compraView = new CompraView();										
					//Compra 
					Compra c=new Compra();
					c.setNroSerie(rs.getInt("nroSerie"));
					c.setId_juego(rs.getInt("id_juego"));
					c.setId_usuario(rs.getInt("id_usuario"));
					c.setId_reembolso(rs.getInt("id_reembolso"));
					c.setId_rese�a(rs.getInt("id_rese�a"));
					c.setHoras_jugadas(rs.getInt("horas_jugadas"));
					c.setDateFechaHora(rs.getObject("fecha_compra",LocalDateTime.class));								
				    compraView.setCompra(c);
				    //Juego
				    Juego j=new Juego();
					j.setId(rs.getInt("id_juego"));
					j.setIdPublicador(rs.getInt("id_publicador"));
					j.setIdDesarrollador(rs.getInt("id_desarrollador"));
					j.setNombre(rs.getString("nombre"));
					j.setDescripcion(rs.getString("descripcion"));
					j.setPrecioBase(rs.getDouble("precio_base"));
					j.setDescuento(rs.getDouble("descuento"));
					j.setGenero(rs.getString("genero"));
					j.setFecha_publicacion(rs.getDate("fecha_publicacion"));
					j.setReestriccionPorEdad(rs.getInt("restriccion_por_edad"));
					compraView.setJuego(j);
				    //Usuario
					Usuario u=new Usuario();
					u.setId(rs.getInt("id_usuario"));
					u.setNombreUsuario(rs.getString("nombre_usuario"));
					u.setEmail(rs.getString("email"));					
					u.setNickname(rs.getString("nickname"));
					u.setFechaNacimiento(rs.getObject("fecha_nacimiento",LocalDate.class));
					u.setTelefono(rs.getString("telefono"));
					u.setTipo(rs.getString("tipo"));
					compraView.setUsuario(u);
					//Reembolso
					Reembolso r=new Reembolso();
					r.setId(rs.getInt("id_reembolso"));
					r.setRazon(rs.getString("razon"));
					r.setEstado(rs.getString("estado"));
					r.setComentario(rs.getString("comentario_evaluador"));	
					compraView.setReembolso(r);					
					compraViewList.add(compraView);							    
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
			
		return compraViewList;
	}

}