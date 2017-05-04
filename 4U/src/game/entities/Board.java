package game.entities;

import exceptions.CellAlreadyOccupied;

public class Board {

	private static final int DEFAULT_ROWS = 6;
	private static final int DEFAULT_COLUMNS = 10;
	
	private int rows;
	private int columns;
	
	private Piece[][] board;
	
	public Board(){
		this.rows = DEFAULT_ROWS;
		this.columns = DEFAULT_COLUMNS;
		this.populateNulls();
	}
	
	public Board(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.populateNulls();
	}
	
	public void setPiece(Player owner, int row, int col) throws CellAlreadyOccupied{
		if(hasPiece(row, col))
			throw new CellAlreadyOccupied();
		
		board[col][row] = new Piece(owner, row, col);
	}
	
	public boolean hasPiece(int row, int col){
		return board[col][row] == null;
	}
	
	public Piece getPiece(int row, int col){
		return board[col][row];
	}
	
	public int getRows(){
		return this.rows;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	private void populateNulls(){
		for(int i = 0; i < this.columns; i++){
			for(int j = 0; j < this.rows; j++){
				board[i][j] = null;
			}
		}
	}
	
	public static boolean validRowsColumns(int rows, int columns){
		return rows > 4 && columns > 4;
	}
	
}
