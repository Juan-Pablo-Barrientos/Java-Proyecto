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
	catch (SQLException e)
	{
	    throw e;
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
	
	public void updatePassword(Usuario obj)
	{
		db.updatePassword(obj);
	}
		
	public Usuario add(Usuario obj) {
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

	public void delete(int id)  throws SQLException {
		 try {
			db.delete(this.getOne(id));
		} catch (SQLException e) {
			throw e;
		}
		 
	}
		  
    public boolean UserNameExist(String userName) throws SQLException { 
		return	db.UserNameExist(userName);	 
    }
    
    public boolean UserEmailExist(String email) throws SQLException { 
		return	db.UserEmailExist(email);	 
    }
}
