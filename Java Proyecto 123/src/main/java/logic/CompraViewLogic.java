package logic;

import java.util.LinkedList;

import data.DataCompraView;
import entities.CompraView;

public class CompraViewLogic {
	
private DataCompraView db= new DataCompraView();
	
	public LinkedList<CompraView> getAll(){
		return db.getAll();
	}
	
	public LinkedList<CompraView> getAllByUserId(int id){
		return db.getAllByUserId(id);
	}

}
