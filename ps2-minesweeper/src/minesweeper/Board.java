/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO: Specification
 * every cell of the board contains either nothing or bomb or a number telling how many bombs arround
 */
public class Board {
	
	private final int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{0,-1},{1,1},{1,0},{1,-1}};
	private final int lenX;
	private final int lenY;
	private Cell[][] mmsboard;
	
	
	
	/**
	 * an empty board without being read from file
	 * */
	
	public Board(int lenX, int lenY){
	    this.lenX = lenX;
	    this.lenY = lenY;
	    mmsboard = new Cell[lenX][lenY];
	    for (int i=0;i<lenX;i++){
	    	for (int j=0;j<lenY;j++){
	    		mmsboard[i][j] = new Cell(0);
	    	}
	    }
	}
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
	/**
	 * Implement a board with file read
	 * */
	public static Board fromFile(File file) throws IOException{
		BufferedReader lines = new BufferedReader(new FileReader(file));
		String[] firstline = lines.readLine().split("\\s");
		int m = Integer.parseInt(firstline[1]);
		int n = Integer.parseInt(firstline[0]);
		if (m<=0 || n<=0){
			lines.close();
			throw new RuntimeException("Invalid inputs\n");
		}
		Board newboard = new Board(m,n);
		try{
			for (int i=0;i<m;i++){
				String[] bombRow = lines.readLine().split("\\s");
				for (int j=0;j<n;j++){
					if (bombRow[j]=="1"){  //it is a bomb
						newboard.mmsboard[i][j] = new Cell(-1);
					}
					
				}
			}
			
		}finally{
			lines.close();
		}
		
		//TODO: count neighbour bombs for evey cell
		
		
		
		return newboard;
	}
}

	
    
    // TODO: Specify, test, and implement in problem 2



	
	/**
	 * what do we need to know about the cell?
	 * is it digged?
	 * is it flagged?
	 * is it a bomb?
	 * if not a bomb, how many bombs around?
	 * */
	class Cell{  //only one public class survives in a single java file
		public boolean digged, flagged, isBomb;
		public final int bombAround;
		
		/**
		 * Initialize a cell.
		 * @param bombFactor: a factor to decide whether cell is a bomb.
		 *                    >0, not bomb, representing num of bombs around
		 *                    <0, bomb
		 * */                 
		public Cell(int bombFactor){
			digged = false;
			flagged = false;
			bombAround = bombFactor;
			if (bombFactor<0){
				isBomb = true;
			}
			else isBomb = false;
		}
	}
	
    

