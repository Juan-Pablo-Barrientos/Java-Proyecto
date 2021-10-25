package data;

import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DataJuego
{
	public boolean GameNameExist(String nombre) throws SQLException

    {
	
	PreparedStatement stmt = null;
	boolean respuesta=false;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn()
		    .prepareStatement("Select count(id) as resultado From juego Where juego.nombre=? and habilitado=1");
	    stmt.setString(1, nombre);
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

    public Juego getOne(int jue)
    {

	Juego j = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url"
			    + " from juego where id=? and habilitado=1");

	    stmt.setInt(1, jue);
	    rs = stmt.executeQuery();
	    if (rs != null && rs.next())
	    {
		j = new Juego();
		j.setId(rs.getInt("id"));
		j.setIdPublicador(rs.getInt("id_publicador"));
		j.setIdDesarrollador(rs.getInt("id_desarrollador"));
		j.setNombre(rs.getString("nombre"));
		j.setDescripcion(rs.getString("descripcion"));
		j.setPrecioBase(rs.getDouble("precio_base"));
		j.setDescuento(rs.getDouble("descuento"));
		j.setGenero(rs.getString("genero"));
		j.setUrl(rs.getString("url"));
		j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
		j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));

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

	return j;
    }

    public LinkedList<Juego> search(String name) throws SQLException
    {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "SELECT id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url"		
		    + "	FROM juego WHERE nombre LIKE ? AND habilitado=1;");

	    stmt.setString(1, "%" + name + "%");
	    rs = stmt.executeQuery();

	    if (rs != null)
	    {
		while (rs.next())
		{
		    Juego j = new Juego();
		    j.setId(rs.getInt("id"));
		    j.setIdPublicador(rs.getInt("id_publicador"));
		    j.setIdDesarrollador(rs.getInt("id_desarrollador"));
		    j.setNombre(rs.getString("nombre"));
		    j.setDescripcion(rs.getString("descripcion"));
		    j.setPrecioBase(rs.getDouble("precio_base"));
		    j.setDescuento(rs.getDouble("descuento"));
		    j.setGenero(rs.getString("genero"));
		    j.setUrl(rs.getString("url"));
		    j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
		    j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));

		    juegs.add(j);
		}
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

	return juegs;
    }

    public LinkedList<Juego> getAll()
    {
	Statement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().createStatement();
	    rs = stmt.executeQuery(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url "
			    + "from juego where habilitado=1");
	    if (rs != null)
	    {
		while (rs.next())
		{
		    Juego j = new Juego();
		    j.setId(rs.getInt("id"));
		    j.setIdPublicador(rs.getInt("id_publicador"));
		    j.setIdDesarrollador(rs.getInt("id_desarrollador"));
		    j.setNombre(rs.getString("nombre"));
		    j.setDescripcion(rs.getString("descripcion"));
		    j.setPrecioBase(rs.getDouble("precio_base"));
		    j.setDescuento(rs.getDouble("descuento"));
		    j.setGenero(rs.getString("genero"));
		    j.setUrl(rs.getString("url"));
		    j.setFecha_publicacion(rs.getObject("fecha_publicacion",LocalDate.class));
		    j.setReestriccionPorEdad(rs.getString("restriccion_por_edad"));

		    juegs.add(j);
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

	return juegs;
    }

    public Juego add(Juego j)
    {

	PreparedStatement stmt = null;
	ResultSet keyResultSet = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "insert into juego(id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url ) "
			    + " values(?,?,?,?,?,?,?,?,?)",
		    PreparedStatement.RETURN_GENERATED_KEYS);

	    stmt.setInt(1, j.getIdPublicador());
	    stmt.setInt(2, j.getIdDesarrollador());
	    stmt.setString(3, j.getNombre());
	    stmt.setString(4, j.getDescripcion());
	    stmt.setDouble(5, j.getPrecioBase());
	    stmt.setDouble(6, j.getDescuento());
	    stmt.setString(7, j.getGenero());
	    stmt.setObject(8, j.getFecha_publicacion());
	    stmt.setString(9, j.getReestriccionPorEdad());
	    stmt.setString(10, j.getUrl());
	    stmt.executeUpdate();

	    keyResultSet = stmt.getGeneratedKeys();
	    if (keyResultSet != null && keyResultSet.next())
	    {
		j.setId(keyResultSet.getInt(1));
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
	return j;
    }

    public void update(Juego j)
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement("update juego set nombre=?,id_publicador=?,id_desarrollador=?,"
		    + "descripcion=?,precio_base=?,descuento=?,genero=?,fecha_publicacion=?,restriccion_por_edad=?,url=? where id=?");

	    stmt.setString(1, j.getNombre());
	    stmt.setInt(2, j.getIdPublicador());
	    stmt.setInt(3, j.getIdDesarrollador());
	    stmt.setString(4, j.getDescripcion());
	    stmt.setDouble(5, j.getPrecioBase());
	    stmt.setDouble(6, j.getDescuento());
	    stmt.setString(7, j.getGenero());
	    stmt.setObject(8, j.getFecha_publicacion());
	    stmt.setString(9, j.getReestriccionPorEdad());
	    stmt.setString(10, j.getUrl());
	    stmt.setInt(11, j.getId());

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

    public void delete(Juego j)
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement("update juego set habilitado=0 where id=?");
	    stmt.setInt(1, j.getId());
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
