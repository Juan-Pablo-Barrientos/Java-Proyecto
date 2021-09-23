package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DataReembolso {

	public Reembolso getOne(int rem) {
		
		Reembolso r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,razon,estado,comentario_evaluador"
					+ " from reembolso where id=? and habilitado=1"
					);
			
			stmt.setInt(1, rem);			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Reembolso();		
				r.setId(rs.getInt("id"));
				r.setRazon(rs.getString("razon"));
				r.setEstado(rs.getString("estado"));
				r.setComentario(rs.getString("comentario_evaluador"));
						
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
		
		return r;
	}
	
	public LinkedList<Reembolso> getAll(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Reembolso> rems= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,razon,estado,comentario_evaluador from reembolso where habilitado=1");			
			if(rs!=null) {
				while(rs.next()) {
					Reembolso r=new Reembolso();
					r.setId(rs.getInt("id"));
					r.setRazon(rs.getString("razon"));
					r.setEstado(rs.getString("estado"));
					r.setComentario(rs.getString("comentario_evaluador"));							
					rems.add(r);
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
			
		return rems;
	}
	
	public LinkedList<Reembolso> getAllPendientes(){		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Reembolso> rems= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id,razon,estado,comentario_evaluador from reembolso WHERE estado='Pendiente' and habilitado=1 ");			
			if(rs!=null) {
				while(rs.next()) {
					Reembolso r=new Reembolso();
					r.setId(rs.getInt("id"));
					r.setRazon(rs.getString("razon"));
					r.setEstado(rs.getString("estado"));
					r.setComentario(rs.getString("comentario_evaluador"));							
					rems.add(r);
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
			
		return rems;
	}

	public Reembolso add(Reembolso r) {


		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into reembolso(razon,estado,comentario_evaluador) "
							+ "values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, r.getRazon());
			stmt.setString(2, r.getEstado());
			stmt.setString(3, r.getComentario());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                r.setId(keyResultSet.getInt(1));
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
		  return r; 
    }

	public void update(Reembolso r) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update reembolso set razon=?,estado=?,comentario_evaluador=?"
							+ "where id=?");
			
			stmt.setString(1, r.getRazon());
			stmt.setString(2, r.getEstado());
			stmt.setString(3, r.getComentario());			
			stmt.setInt(4, r.getId());
		
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

	public void delete(Reembolso r) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update reembolso set habilitado=0 where id=?");
			stmt.setInt(1, r.getId());
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
