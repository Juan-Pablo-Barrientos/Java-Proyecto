package data;

import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.sql.rowset.serial.*;

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
	public byte[] getOneImageById(int id) throws SQLException
    {

	PreparedStatement stmt3 = null;
	ResultSet rs3 = null;
    byte[] blobAsBytes = null;
	try
	{
	    stmt3 = DbConnector.getInstancia().getConn().prepareStatement(
		    "select imagen "
			    + " from juego where id=? and habilitado=1");

	    stmt3.setInt(1, id);
	    rs3 = stmt3.executeQuery();
	    if (rs3 != null && rs3.next())
	    {
	    Blob blob = rs3.getBlob("imagen");
	    if (blob!=null) {
		    int blobLength = (int) blob.length();  
		    blobAsBytes = blob.getBytes(1, blobLength);
		    blob.free();
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
		if (rs3 != null)
		{
		    rs3.close();
		}
		if (stmt3 != null)
		{
		    stmt3.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}

	return blobAsBytes;
    }
    public Juego getOne(int jue) throws SQLException
    {

	Juego j = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen \n"
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
	    Blob blob = rs.getBlob("imagen");
	    byte[] blobAsBytes = null;
	    if (blob!=null) {
		    int blobLength = (int) blob.length();  
		    blobAsBytes = blob.getBytes(1, blobLength);
		    blob.free();
	    }
	    j.setImagen(blobAsBytes);

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

	return j;
    }

    public Juego getOneByGenero(int jue, String gen) throws SQLException
    {

	Juego j = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen \n"
			    + " from juego where id=? and habilitado=1 and genero=?");

	    stmt.setInt(1, jue);
	    stmt.setString(2, gen);
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
	    Blob blob = rs.getBlob("imagen");
	    byte[] blobAsBytes = null;
	    if (blob!=null) {
		    int blobLength = (int) blob.length();  
		    blobAsBytes = blob.getBytes(1, blobLength);
		    blob.free();
	    }
	    j.setImagen(blobAsBytes);

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
		    "SELECT id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen \n"		
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
		    Blob blob = rs.getBlob("imagen");
		    byte[] blobAsBytes = null;
		    if (blob!=null) {
			    int blobLength = (int) blob.length();  
			    blobAsBytes = blob.getBytes(1, blobLength);
			    blob.free();
		    }
		    j.setImagen(blobAsBytes);

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
    
    public LinkedList<Juego> getAllConDescuento() throws SQLException
    {
	Statement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().createStatement();
	    rs = stmt.executeQuery(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen \n"
			    + "from juego where habilitado=1 and descuento!=0");
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
		    Blob blob = rs.getBlob("imagen");
		    byte[] blobAsBytes = null;
		    if (blob!=null) {
			    int blobLength = (int) blob.length();  
			    blobAsBytes = blob.getBytes(1, blobLength);
			    blob.free();
		    }
		    j.setImagen(blobAsBytes);

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
    
    public LinkedList<Juego> getAllMini() throws SQLException
    {
	Statement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().createStatement();
	    rs = stmt.executeQuery(
		    "select id,descuento,genero,imagen \n"
			    + "from juego where habilitado=1");
	    if (rs != null)
	    {
		while (rs.next())
		{
		    Juego j = new Juego();
		    j.setId(rs.getInt("id"));
		    j.setNombre(rs.getString("nombre"));
		    j.setDescuento(rs.getDouble("descuento"));
		    j.setGenero(rs.getString("genero"));
		    Blob blob = rs.getBlob("imagen");
		    byte[] blobAsBytes = null;
		    if (blob!=null) {
			    int blobLength = (int) blob.length();  
			    blobAsBytes = blob.getBytes(1, blobLength);
			    blob.free();
		    }
		    j.setImagen(blobAsBytes);

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
    
    
    
    public LinkedList<Juego> getAll() throws SQLException
    {
	Statement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().createStatement();
	    rs = stmt.executeQuery(
		    "select id,id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen \n"
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
		    Blob blob = rs.getBlob("imagen");
		    byte[] blobAsBytes = null;
		    if (blob!=null) {
			    int blobLength = (int) blob.length();  
			    blobAsBytes = blob.getBytes(1, blobLength);
			    blob.free();
		    }
		    j.setImagen(blobAsBytes);

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
    
    public LinkedList<Juego> getAllNotReleased() throws SQLException
    {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    LocalDate localDate = LocalDate.now();
    PreparedStatement stmt = null;
	ResultSet rs = null;
	LinkedList<Juego> juegs = new LinkedList<>();

	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(   
		    "SELECT * FROM market_tp.juego where fecha_publicacion>? ");
		stmt.setObject(1, dtf.format(localDate));	 
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
		    Blob blob = rs.getBlob("imagen");
		    byte[] blobAsBytes = null;
		    if (blob!=null) {
			    int blobLength = (int) blob.length();  
			    blobAsBytes = blob.getBytes(1, blobLength);
			    blob.free();
		    }
		    j.setImagen(blobAsBytes);

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

    public Juego add(Juego j) throws SQLException
    {

	PreparedStatement stmt = null;
	ResultSet keyResultSet = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement(
		    "insert into juego(id_publicador,id_desarrollador,nombre,descripcion,precio_base,descuento,genero,fecha_publicacion,restriccion_por_edad,url,imagen ) \n"
			    + " values(?,?,?,?,?,?,?,?,?,?,?)",
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
	    Blob blob = new javax.sql.rowset.serial.SerialBlob(j.getImagen());
	    stmt.setBlob(11,blob);
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
	    throw e;
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
		throw e;
	    }
	}
	return j;
    }

    public void update(Juego j) throws SQLException
    {
	PreparedStatement stmt = null;
	try
	{
	    stmt = DbConnector.getInstancia().getConn().prepareStatement("update juego set nombre=?,id_publicador=?,id_desarrollador=?, \n"
		    + "descripcion=?,precio_base=?,descuento=?,genero=?,fecha_publicacion=?,restriccion_por_edad=?,url=?,imagen=? where id=?");

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
	    Blob blob = new javax.sql.rowset.serial.SerialBlob(j.getImagen());
	    stmt.setBlob(11,blob);
	    stmt.setInt(12, j.getId());

	    stmt.executeUpdate();
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
		if (stmt != null)
		    stmt.close();
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}
    }

    public void delete(Juego j) throws SQLException
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
	    throw e;
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
		throw e;
	    }
	}
    }
}
