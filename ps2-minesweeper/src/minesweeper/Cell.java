package minesweeper;

import java.util.ArrayList;
import java.util.List;

/**
 * what do we need to know about the cell?
 * is it digged?
 * is it flagged?
 * is it a bomb?
 * if not a bomb, how many bombs around?
 * */
public class Cell{  //only one public class survives in a single java file
	public boolean isBomb;
	String state;
	
	public int bombAround;
	
	/**
	 * Initialize a cell.
	 * @param bombFactor: a factor to decide whether cell is a bomb.
	 *                    >0, not bomb, representing num of bombs around
	 *                    <0, bomb
	 * */                 
	public Cell(int bombFactor, String state){
		this.state = state; 
		this.bombAround = bombFactor;
		if (bombFactor<0){
			isBomb = true;
		}
		else isBomb = false;
	}
	
	public void changeState(String t) throws IllegalStateException{
		if (!t.equals("DUG") || !t.equals("UNTOUCHED") || !t.equals("FLAGGED")){
			throw new IllegalStateException("illegal board state");
		}
	}
	
	public String getState(){
		return this.state;
	}
	
	public void setBombAround(int delta){
		this.bombAround = delta;
	}
	
	public int getBombAround(){
		return this.bombAround;
	}
	
	public void setBomb(boolean flag){
		this.isBomb = flag;
	}
	
	public boolean getBomb(){
		return this.isBomb;
	}


}
