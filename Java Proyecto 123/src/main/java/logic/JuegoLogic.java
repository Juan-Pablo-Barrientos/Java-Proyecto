package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class JuegoLogic
{

    private DataJuego db = new DataJuego();

    public LinkedList<Juego> getAll()
    {
	return db.getAll();
    }
    
    public LinkedList<Juego> search(String name) throws SQLException
    {
	try
	{
	    return db.search(name);
	}
	catch (SQLException e)
	{
	    throw e;
	}
	
    }

    public Juego getOne(Juego obj)
    {
	return this.getOne(obj.getId());
    }

    public Juego getOne(int i)
    {
	return db.getOne(i);
    }

    public Juego add(Juego obj)
    {
	return db.add(obj);
    }

    public void update(Juego obj)
    {
	db.update(obj);
    }

    public void delete(Juego obj)
    {
	db.delete(obj);
    }

    public void delete(int id)
    {
	db.delete(this.getOne(id));
    }
    
    public boolean GameNameExist(String nombre) throws SQLException { 
		return	db.GameNameExist(nombre);	 
    }

}
