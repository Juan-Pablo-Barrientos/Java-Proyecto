package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class DataReembolso {
	
private DataReembolso db= new DataReembolso();
	
	public LinkedList<Reembolso> getAll(){
		return db.getAll();
	}
	
	public Reembolso getOne(Reembolso obj) {
		return db.getOne(obj);
		
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
