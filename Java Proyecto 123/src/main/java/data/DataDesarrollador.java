package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DataDesarrollador {
	
	public Desarrollador getOne(Desarrollador de) {
		
		Desarrollador d=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre"
					+ " from desarrollador where id=?"
					);
			
			stmt.setInt(1, de.getId());			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				d=new Desarrollador();				
				d.setId(rs.getInt("id"));
				d.setNombre(rs.getString("nombre"));
										
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
		
		return d;
	}

	public LinkedList<Desarrollador> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Desarrollador> desr= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,nombre from desarrollador");			
			if(rs!=null) {
				while(rs.next()) {
					Desarrollador d=new Desarrollador();
					d.setId(rs.getInt("id"));
					d.setNombre(rs.getString("nombre"));										
					desr.add(d);
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
			
		return desr;
	}
	
	public Desarrollador add(Desarrollador d) {

		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into desarrollador(nombre) "
							+ "values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, d.getNombre());				
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                d.setId(keyResultSet.getInt(1));
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
		  return d; 
    }
 
	public void update(Desarrollador d) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update desarrollador set nombre=? where id=?"
							 );
			
			stmt.setString(1, d.getNombre());	
			stmt.setInt(2, d.getId());					
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

	public void delete(Desarrollador d) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from desarrollador where id=?");
			stmt.setInt(1, d.getId());
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

