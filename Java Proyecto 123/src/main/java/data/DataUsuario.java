package data;

import entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DataUsuario {
	
	public Usuario getOne(Usuario us) {
		
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo"
					+ " from usuario where id=?"
					);
			
			stmt.setInt(1, us.getId());			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u=new Usuario();				
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setEmail(rs.getString("email"));				
				u.setNickname(rs.getString("nickname"));
				u.setFechaNacimiento(rs.getObject("fecha_nacimiento",LocalDateTime.class));
				u.setTelefono(rs.getString("telefono"));
				u.setTipo(rs.getString("tipo"));
							
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
		
		return u;
	}
	
	public Usuario getOneByUserName(Usuario us) {
		
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo"
					+ " from usuario where contraseña=? AND (email=? OR nombre_usuario=?)"
					);
			
			stmt.setString(1, us.getContraseña());
			stmt.setString(2, us.getEmail());	
			stmt.setString(3, us.getNombreUsuario());	
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u=new Usuario();				
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setEmail(rs.getString("email"));		
				u.setNickname(rs.getString("nickname"));
				u.setFechaNacimiento(rs.getObject("fecha_nacimiento",LocalDateTime.class));
				u.setTelefono(rs.getString("telefono"));
				u.setTipo(rs.getString("tipo"));
							
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
		
		return u;
	}
	
	public LinkedList<Usuario> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Usuario> usrs= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo from usuario");			
			if(rs!=null) {
				while(rs.next()) {
					Usuario u=new Usuario();
					u.setId(rs.getInt("id"));
					u.setNombreUsuario(rs.getString("nombre_usuario"));
					u.setEmail(rs.getString("email"));					
					u.setNickname(rs.getString("nickname"));
					u.setFechaNacimiento(rs.getObject("fecha_nacimiento",LocalDateTime.class));
					u.setTelefono(rs.getString("telefono"));
					u.setTipo(rs.getString("tipo"));
					usrs.add(u);
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
		
		
		return usrs;
	}

	public Usuario add(Usuario u) {


		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario(nombre_usuario,email,contraseña,nickname,fecha_nacimiento,telefono,tipo) "
							+ "values(?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2,u.getEmail());
			stmt.setString(3, u.getContraseña());
			stmt.setString(4, u.getNickname());
			stmt.setObject(5,u.getFechaNacimiento());
			stmt.setString(6, u.getTelefono());
			stmt.setString(7, u.getTipo());
				
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                u.setId(keyResultSet.getInt(1));
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
		  return u; 
    }

	public void update(Usuario u) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update usuario set nombre_usuario=?,email=?,contraseña=?,"
							+ "nickname=?,fecha_nacimiento=?,telefono=?,tipo=? where id=?");
			
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2,u.getEmail());
			stmt.setString(3, u.getContraseña());
			stmt.setString(4, u.getNickname());
			stmt.setObject(5,u.getFechaNacimiento());
			stmt.setString(6, u.getTelefono());			
			stmt.setInt(7, u.getId());
			stmt.setString(8, u.getTipo());
			
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

	public void delete(Usuario u) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from usuario where id=?");
			stmt.setInt(1, u.getId());
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
