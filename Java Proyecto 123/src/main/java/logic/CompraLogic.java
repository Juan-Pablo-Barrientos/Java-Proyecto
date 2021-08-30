package logic;

import java.util.LinkedList;

import data.*;
import entities.*;


public class CompraLogic {

private DataCompra db= new DataCompra();
	
	public LinkedList<Compra> getAll(){
		return db.getAll();
	}
	
	public Compra getOne(Compra obj) {
		return db.getOne(obj);
		
	} 
	
	public Compra getOneByReembolso(Reembolso obj) {
		return db.getOneByReembolso(obj);
		
	}
		
	public Compra add(Compra obj) {
			return db.add(obj);
		
	}
	
	public void update(Compra obj) {
		 db.update(obj);
		
	}
	public void delete(Compra obj) {
		 db.delete(obj);
		
	}

}
