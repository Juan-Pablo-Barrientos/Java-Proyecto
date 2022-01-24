package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class UsuarioLogic
{

    private DataUsuario db = new DataUsuario();

    public LinkedList<Usuario> getAll() throws SQLException
    {
	try {
		return db.getAll();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw e;
	}
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

    public Usuario getOneByEmail(String email) throws SQLException
    {
	try
	{
	    return db.getOneByEmail(email);
	}
	catch (SQLException e)
	{
	    throw e;
	}
    }
	
	public void updatePassword(Usuario obj) throws SQLException
	{
		try {
			db.updatePassword(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
		
	public Usuario add(Usuario obj) throws SQLException {
		try {
			return db.add(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}	
	
    }
    

    public void update(Usuario obj) throws SQLException
    {
    	try {
	db.update(obj);
    	}
    	catch (SQLException e){
    		throw e;
    	}
    }

    public void delete(Usuario obj) throws SQLException
    {
	try {
		db.delete(obj);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw e;
	}
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
