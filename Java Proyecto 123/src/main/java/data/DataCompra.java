package data;

import entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataCompra {

	public Compra getOne(Compra com) {
		
		Compra c=null;;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select nroSerie,id_juego,id_usuario,id_reembolso,id_reseña,horas_jugadas,fecha_compra"
					+ " from compra where nroSerie=?"
					);
			
			stmt.setInt(1, com.getNroSerie());			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Compra();		
				c.setNroSerie(rs.getInt("nroSerie"));
				c.setId_juego(rs.getInt("id_juego"));
				c.setId_usuario(rs.getInt("id_usuario"));
				c.setId_reembolso(rs.getInt("id_reembolso"));
				c.setId_reseña(rs.getInt("id_reseña"));
				c.setHoras_jugadas(rs.getInt("horas_jugadas"));
				c.setDateFechaHora(rs.getObject("fecha_compra",LocalDateTime.class));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}
	
	public Compra getOneByReembolso(Reembolso com) {
		
		Compra c=null;;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select nroSerie,id_juego,id_usuario,id_reembolso,id_reseña,horas_jugadas,fecha_compra"
					+ " from compra where id_reembolso=?"
					);
			
			stmt.setInt(1, com.getId());			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Compra();		
				c.setNroSerie(rs.getInt("nroSerie"));
				c.setId_juego(rs.getInt("id_juego"));
				c.setId_usuario(rs.getInt("id_usuario"));
				c.setId_reembolso(rs.getInt("id_reembolso"));
				c.setId_reseña(rs.getInt("id_reseña"));
				c.setHoras_jugadas(rs.getInt("horas_jugadas"));
				c.setDateFechaHora(rs.getObject("fecha_compra",LocalDateTime.class));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}
	
	public LinkedList<Compra> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Compra> coms= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select nroSerie,id_juego,id_usuario,id_reembolso,id_reseña,horas_jugadas,fecha_compra from compra");			
			if(rs!=null) {
				while(rs.next()) {
					Compra c=new Compra();
					c.setNroSerie(rs.getInt("nroSerie"));
					c.setId_juego(rs.getInt("id_juego"));
					c.setId_usuario(rs.getInt("id_usuario"));
					c.setId_reembolso(rs.getInt("id_reembolso"));
					c.setId_reseña(rs.getInt("id_reseña"));
					c.setHoras_jugadas(rs.getInt("horas_jugadas"));
					c.setDateFechaHora(rs.getObject("fecha_compra",LocalDateTime.class));								
					coms.add(c);
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
			
		return coms;
	}

	public Compra add(Compra c) {


		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into compra(id_juego,id_usuario,id_reembolso,id_reseña,horas_jugadas,fecha_compra) "
							+ "values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setInt(1, c.getId_juego());
			stmt.setInt(2, c.getId_usuario());		
			stmt.setInt(3, c.getId_reembolso());
			stmt.setInt(4, c.getId_reseña());
			stmt.setInt(5, c.getHoras_jugadas());			
			stmt.setObject(6, c.getDateFechaHora());				
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setNroSerie(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }        
		}
		  return c; 
    }
	
	public void update(Compra c) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update compra set id_juego=?,id_usuario=?,id_reembolso=?,"
							+ "id_reseña=?,horas_jugadas=?,fecha_compra=? where nroSerie=?");
			
			stmt.setInt(1, c.getId_juego());
			stmt.setInt(2, c.getId_usuario());		
			stmt.setInt(3, c.getId_reembolso());
			stmt.setInt(4, c.getId_reseña());
			stmt.setInt(5, c.getHoras_jugadas());			
			stmt.setObject(6, c.getDateFechaHora());
			stmt.setInt(7, c.getNroSerie());
		
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public void delete(Compra c) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from compra where nroSerie=?");
			stmt.setInt(1, c.getNroSerie());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}
