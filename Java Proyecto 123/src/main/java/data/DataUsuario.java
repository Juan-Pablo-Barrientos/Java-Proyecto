package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;
import java.time.LocalDate;

public class DataUsuario
{
	
	public Usuario getOne(int us) throws SQLException
    {

	Usuario u = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn()
		    .prepareStatement("select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo,saldo" + " from usuario where id=? and habilitado=1");

	    stmt.setInt(1, us);
	    rs = stmt.executeQuery();
	    if (rs != null && rs.next())
	    {
		u = new Usuario();
		u.setId(rs.getInt("id"));
		u.setNombreUsuario(rs.getString("nombre_usuario"));
		u.setEmail(rs.getString("email"));
		u.setNickname(rs.getString("nickname"));
		u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		u.setTelefono(rs.getString("telefono"));
		u.setTipo(rs.getString("tipo"));
		u.setSaldo(rs.getDouble("saldo"));
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally
	{
	    try
	    {
		if (rs != null)
		{
		    rs.close();
		}
		if (stmt != null)
		{
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}

	return u;
    }

    public boolean UserNameExist(String userName) throws SQLException
    {
	
	PreparedStatement stmt = null;
	boolean respuesta=false;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn()
		    .prepareStatement("Select count(id) as resultado From usuario Where usuario.nombre_usuario=? and habilitado=1");
	    stmt.setString(1, userName);
	    rs = stmt.executeQuery();
	    if (rs != null && rs.next())
	    {		
		respuesta =rs.getBoolean("resultado");		
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally
	{
	    try
	    {
		if (rs != null)
		{
		    rs.close();
		}
		if (stmt != null)
		{
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}

	return respuesta;
    }

    public boolean UserEmailExist(String email) throws SQLException

    {
	
	PreparedStatement stmt = null;
	boolean respuesta=false;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn()
		    .prepareStatement("Select count(id) as resultado From usuario Where usuario.email=? and habilitado=1");
	    stmt.setString(1, email);
	    rs = stmt.executeQuery();
	    if (rs != null && rs.next())
	    {		
		respuesta =rs.getBoolean("resultado");		
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally
	{
	    try
	    {
		if (rs != null)
		{
		    rs.close();
		}
		if (stmt != null)
		{
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}

	return respuesta;
    }
    
    public Usuario getOneByUserName(Usuario us) throws SQLException
    {

	Usuario u = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement("select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo,contraseña,saldo"
		    + " from usuario where contraseña=? AND (email=? OR nombre_usuario=?) AND habilitado=1");

	    stmt.setString(1, us.getContraseña());
	    stmt.setString(2, us.getEmail());
	    stmt.setString(3, us.getNombreUsuario());
	    rs = stmt.executeQuery();
	    if (rs != null && rs.next())
	    {
		u = new Usuario();
		u.setId(rs.getInt("id"));
		u.setNombreUsuario(rs.getString("nombre_usuario"));
		u.setEmail(rs.getString("email"));
		u.setNickname(rs.getString("nickname"));
		u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		u.setTelefono(rs.getString("telefono"));
		u.setTipo(rs.getString("tipo"));
		u.setContraseña(rs.getString("contraseña"));
		u.setSaldo(rs.getDouble("saldo"));
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally
	{
	    try
	    {
		if (rs != null)
		{
		    rs.close();
		}
		if (stmt != null)
		{
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}
	return u;
    }

    public LinkedList<Usuario> getAll()
    {
	Statement stmt = null;
	ResultSet rs = null;
	LinkedList<Usuario> usrs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().createStatement();
	    rs = stmt.executeQuery("select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo,saldo from usuario where habilitado=1");
	    if (rs != null)
	    {
		while (rs.next())
		{
		    Usuario u = new Usuario();
		    u.setId(rs.getInt("id"));
		    u.setNombreUsuario(rs.getString("nombre_usuario"));
		    u.setEmail(rs.getString("email"));
		    u.setNickname(rs.getString("nickname"));
		    u.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		    u.setTelefono(rs.getString("telefono"));
		    u.setTipo(rs.getString("tipo"));
		    u.setSaldo(rs.getDouble("saldo"));
		    usrs.add(u);
		}
	    }

	}
	catch (SQLException e)
	{
	    e.printStackTrace();

	}
	finally
	{
	    try
	    {
		if (rs != null)
		{
		    rs.close();
		}
		if (stmt != null)
		{
		    stmt.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}

	return usrs;
    }

    public void delete(Usuario u)
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement("update usuario set habilitado=? where id=?");
	    stmt.setInt(1, 0);
	    stmt.setInt(2, u.getId());
	    stmt.executeUpdate();
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		if (stmt != null)
		    stmt.close();
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
    }

    public Usuario add(Usuario u)
    {

	PreparedStatement stmt = null;
	ResultSet keyResultSet = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "insert into usuario(nombre_usuario,email,contraseña,nickname,fecha_nacimiento,telefono,tipo,saldo) " + "values(?,?,?,?,?,?,?,?)",
		    PreparedStatement.RETURN_GENERATED_KEYS);

	    stmt.setString(1, u.getNombreUsuario());
	    stmt.setString(2, u.getEmail());
	    stmt.setString(3, u.getContraseña());
	    stmt.setString(4, u.getNickname());
	    stmt.setObject(5, u.getFechaNacimiento());
	    stmt.setString(6, u.getTelefono());
	    stmt.setString(7, u.getTipo());
	    stmt.setDouble(8, u.getSaldo());

	    stmt.executeUpdate();

	    keyResultSet = stmt.getGeneratedKeys();
	    if (keyResultSet != null && keyResultSet.next())
	    {
		u.setId(keyResultSet.getInt(1));
	    }

	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		if (keyResultSet != null)
		    keyResultSet.close();
		if (stmt != null)
		    stmt.close();
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
	return u;
    }

    public void update(Usuario u)
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "update usuario set nombre_usuario=?,email=?," + "nickname=?,fecha_nacimiento=?,telefono=?,tipo=?,saldo=? where id=?");

	    stmt.setString(1, u.getNombreUsuario());
	    stmt.setString(2, u.getEmail());	    
	    stmt.setString(3, u.getNickname());
	    stmt.setObject(4, u.getFechaNacimiento());
	    stmt.setString(5, u.getTelefono());
	    stmt.setString(6, u.getTipo());
	    stmt.setDouble(7, u.getSaldo());
	    stmt.setInt(8, u.getId());
	    stmt.executeUpdate();

	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		if (stmt != null)
		    stmt.close();
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
    }
    
    public void updatePassword(Usuario u)
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "update usuario set contraseña=? where id=?");

	    stmt.setString(1, u.getContraseña());	  
	    stmt.setInt(2, u.getId());	
	    stmt.executeUpdate();
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		if (stmt != null)
		    stmt.close();
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
    }
}
