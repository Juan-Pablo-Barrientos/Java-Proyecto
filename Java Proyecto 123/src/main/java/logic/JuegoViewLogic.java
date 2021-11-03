package logic;

import java.util.LinkedList;
import data.DataJuegoView;
import entities.JuegoView;

public class JuegoViewLogic {
	
private DataJuegoView db= new DataJuegoView();
	
	public LinkedList<JuegoView> getAll(){
		return db.getAll();
	}
	
	public LinkedList<JuegoView> getAllNotRelesed(){
		return db.getAllNotRelesed();
	}
	public JuegoView getOne(int Id){
		return db.getOne(Id);
	}
}
