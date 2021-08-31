package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class UsuarioLogic {
	
private DataUsuario db= new DataUsuario();
	
	public LinkedList<Usuario> getAll(){
		return db.getAll();
	}
	
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
	}
	
	public void update(Usuario obj) {
		 db.update(obj);
		
	}
	public void delete(Usuario obj) {
		 db.delete(obj);		
	}

}
