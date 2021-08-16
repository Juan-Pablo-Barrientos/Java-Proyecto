package data;

import entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataPublicador {
	
	public Publicador getOne(Publicador pu) {
		
		Publicador p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre"
					+ " from publicador where id=?"
					);
			
			stmt.setInt(1, pu.getId());			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Publicador();				
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
										
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
		
		return p;
	}

	public LinkedList<Publicador> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Publicador> pubs= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,nombre from publicador");			
			if(rs!=null) {
				while(rs.next()) {
					Publicador p=new Publicador();
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));										
					pubs.add(p);
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
		
		
		return pubs;
	}
	
	public Publicador add(Publicador p) {

		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into publicador(nombre) "
							+ "values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, p.getNombre());				
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setId(keyResultSet.getInt(1));
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
		  return p; 
    }

	public void update(Publicador p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update publicador set nombre=? where id=?"
							 );
			
			stmt.setString(1, p.getNombre());	
			stmt.setInt(2, p.getId());					
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
	
	public void delete(Publicador p) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from publicador where id=?");
			stmt.setInt(1, p.getId());
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
