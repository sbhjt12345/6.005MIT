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
	
	private static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{0,-1},{1,1},{1,0},{1,-1}};
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
	    		mmsboard[i][j] = new Cell(0,"UNTOUCHED");
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
						newboard.mmsboard[i][j] = new Cell(-1,"UNTOUCHED");
					}
					
				}
			}
			
		}finally{
			lines.close();
		}
		
		//TODO: count neighbour bombs for evey cell
		
		for (int i=0;i<m;i++){
			for (int j=0;j<n;j++){
				if (!newboard.mmsboard[i][j].isBomb){
					int count = 0;
					for (int[] dir : dirs){
						int x = i + dir[0];
						int y = j + dir[1];
						if (x>=0 && x<m && y>=0 && y<n && newboard.mmsboard[x][y].isBomb){
							count++;
						}
					}
					newboard.mmsboard[i][j] = new Cell(count,"UNTOUCHED");
				}
			}
		}
		return newboard;
	}
	
	
	/**
	 * Implement dig function.
	 * @param row the row position of the cell 
	 * @param col the col position of the cell
	 * 
	 * */
	public synchronized String dig(int row, int col){
		if (row<0 || row>=lenX || col<0 || col>=lenY || 
				!mmsboard[row][col].getState().equals("UNTOUCHED")){
			return "dealing with it";           //look()
		}
		if (mmsboard[row][col].getBomb()){
			int count = 0;
			//If square x,y contains a bomb, change it so that it contains no bomb 
			//When modifying a square from containing a bomb to no longer containing a bomb, 
			//make sure that subsequent BOARD messages show updated bomb counts in the adjacent squares
			for (int[] dir : dirs){
				int x = row + dir[0];
				int y = col + dir[1];
				Cell origin = mmsboard[x][y];
				if (!mmsboard[x][y].getBomb()){
					int tmp = mmsboard[x][y].bombAround;
					mmsboard[x][y].setBombAround(tmp-1);
				}
				else{
					count++;
				}
			}
			mmsboard[row][col] = new Cell(count,"DUG");
      	  //new cell is not a bomb, has a new bombaround, and is digged
			if (count==0){           // deal with dfs
				dfs(row,col);
			}
			return "BOOM!\r\n";
		}
		else if (mmsboard[row][col].getBombAround()==0){         // if no bombs around now
			dfs(row,col);
		}
		return "the look board info";       //TODO: look function
	}
	
	public void dfs(int row, int col){
		//TODO : if no bomb around for this cell, dfs around and dig all those cells with no bombs around
	}
	
}

	
    
    // TODO: Specify, test, and implement in problem 2

	
    

