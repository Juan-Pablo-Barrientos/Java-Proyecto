package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class ReembolsoLogic {
	
private DataReembolso db= new DataReembolso();
	
	public LinkedList<Reembolso> getAll(){
		return db.getAll();
	}
	
	public LinkedList<Reembolso> getAllPendientes(){
		return db.getAllPendientes();
	}
	
	public Reembolso getOne(Reembolso obj) {
		return this.getOne(obj.getId());
		
	} 
	
	public Reembolso getOne(int i) {
		return db.getOne(i);
		
	} 
		
	public Reembolso add(Reembolso obj) {
			return db.add(obj);
		
	}
	
	public void update(Reembolso obj) {
		 db.update(obj);
		
	}
	public void delete(Reembolso obj) {
		 db.delete(obj);
		
	}

}
