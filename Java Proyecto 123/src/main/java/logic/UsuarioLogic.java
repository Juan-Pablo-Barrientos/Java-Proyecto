package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class UsuarioLogic
{

    private DataUsuario db = new DataUsuario();

    public LinkedList<Usuario> getAll()
    {
	return db.getAll();
    }

    public Usuario getOne(Usuario obj) throws SQLException
    {
	try
	{
	    return this.getOne(obj.getId());
	}
<<<<<<< HEAD
	
	public Usuario getOne(Usuario obj) {
		return this.getOne(obj.getId());	
	} 
	
	public Usuario getOne(int i) {
		return db.getOne(i);		
	} 
	
	public Usuario getOneByUserName(Usuario obj) {
		return db.getOneByUserName(obj);	
	} 
		
	public Usuario add(Usuario obj) {
		return db.add(obj);	
=======
	catch (SQLException e)
	{
	    throw e;
>>>>>>> branch 'main' of git@github.com:Juan-Pablo-Barrientos/Java-Proyecto.git
	}
    }

    public Usuario getOne(int i) throws SQLException
    {
	try
	{
	    return db.getOne(i);
	}
	catch (SQLException e)
	{
	    throw e;
	}
<<<<<<< HEAD
	public void delete(int id) {
		 db.delete(this.getOne(id));
		 
	}
=======
    }

    public Usuario getOneByUserName(Usuario obj) throws SQLException
    {
	try
	{
	    return db.getOneByUserName(obj);
	}
	catch (SQLException e)
	{
	    throw e;
	}
    }

    public Usuario add(Usuario obj)
    {
	return db.add(obj);
    }

    public void update(Usuario obj)
    {
	db.update(obj);

    }

    public void delete(Usuario obj)
    {
	db.delete(obj);
    }
>>>>>>> branch 'main' of git@github.com:Juan-Pablo-Barrientos/Java-Proyecto.git

}
