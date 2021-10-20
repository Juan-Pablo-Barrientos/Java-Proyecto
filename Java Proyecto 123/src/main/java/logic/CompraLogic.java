package logic;

import java.util.LinkedList;

import data.*;
import entities.*;


public class CompraLogic {

private DataCompra db= new DataCompra();
	
	public int NumeroDeCompras(int IdUsuario,int IdJuego){
		return db.NumeroDeCompras(IdUsuario, IdJuego);
	}
	
	
	public LinkedList<Compra> getAll(){
		return db.getAll();
	}
	
	public Compra getOne(Compra obj) {
		return this.getOne(obj.getNroSerie());
		
	} 
	
	public Compra getOne(int i) {
		return db.getOne(i);
		
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
	public void updateIdReseña(Compra obj) {
		 db.updateIdReseña(obj);
		
	}
	public void updateIdReembolso(Compra obj) {
		 db.updateIdReembolso(obj);
		
	}
	public void delete(Compra obj) {
		 db.delete(obj);
		
	}

}
