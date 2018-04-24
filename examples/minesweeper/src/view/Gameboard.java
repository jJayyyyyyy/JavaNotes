package view;

import java.util.ArrayList;

import model.Cell;

public class Gameboard {
	private int cntRow, cntCol;
	private Cell[][] gameboard;
	
	public Gameboard(int row, int col) {
		this.cntRow = row;
		this.cntCol = col;
		gameboard = new Cell[row][col];
		generateMine();
		setNeiborMine();
	}
	
	public int getRow() {
		return cntRow;
	}
	
	public int getCol() {
		return cntCol;
	}
		
	public void generateMine() {
		for ( int i = 0; i < cntRow; i++ ) {
			for ( int j = 0; j < cntCol; j++ ) {
				Cell cell = new Cell();
				gameboard[i][j] = cell;
				if ( Math.random() < 0.1 ) {
					cell.isMine = true;
				}				
			}
		}
	}
	
	public Cell getCell(int row, int col) {
		return gameboard[row][col];
	}

	public Cell[] getNeighborList(int row, int col) {
		ArrayList<Cell> neighborList = new ArrayList<Cell>();
		for( int i = -1; i <= 1; i++ ) {
			for ( int j = -1; j <= 1; j++ ) {
				if ( isCenter(i, j) == false ) {
					int nextRow = row + i;
					int nextCol = col + j;
					if( isInBoard(nextRow, nextCol) ) {
						neighborList.add(gameboard[nextRow][nextCol]);
					}
				}
			}
		}
		return neighborList.toArray(new Cell[neighborList.size()]);
	}
	
	public int cntNeighBorMine(int row, int col) {
		int cnt = 0;
		for( int i = -1; i <= 1; i++ ) {
			for ( int j = -1; j <= 1; j++ ) {
				if ( isCenter(i, j) == false ) {
					int nextRow = row + i;
					int nextCol = col + j;
					if( isInBoard(nextRow, nextCol) ) {
						if ( gameboard[nextRow][nextCol].isMine ) {
							cnt++;
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	public void setNeiborMine() {
		for(int i = 0; i < this.cntRow; i++ ) {
			for ( int j = 0; j <  this.cntCol; j++ ) {
				Cell cell = gameboard[i][j]; 
				cell.neiborMine = cntNeighBorMine(i, j);
			}
		}
	}

	public void floodFill(int row, int col) {
		Cell cell = gameboard[row][col];
		if( cell.revealed == false && cell.isFlag == false ) {
			cell.revealed = true;
			if( cell.neiborMine == 0 ) {
				for( int i = -1; i <= 1 ; i++ ) {
					for( int j = -1; j <= 1; j++ ) {
						if ( isCenter(i, j) == false ) {
							int nextRow = row + i;
							int nextCol = col + j;
							if( isInBoard(nextRow, nextCol) ) {
								floodFill(nextRow, nextCol);
							}
						}
					}
				}	
			}
			
		}
	}
	
	public boolean isInBoard(int row, int col) {
		if( row > -1 && row < this.cntRow && col >-1 && col < this.cntCol ) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isCenter(int offsetRow, int offsetCol) {
		if( offsetRow == 0 && offsetCol == 0) {
			return true;
		}else {
			return false;
		}
	}
}
