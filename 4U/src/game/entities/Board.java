package game.entities;

import exceptions.CellAlreadyOccupied;

public class Board {

	private static final int DEFAULT_ROWS = 6;
	private static final int DEFAULT_COLUMNS = 10;
	
	private int rows;
	private int columns;
	
	private Piece[][] board;
	
	public Board(){
		this( DEFAULT_ROWS, DEFAULT_COLUMNS );
	}
	
	public Board(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		for(int i = 0; i < rows; i++){
		    board[i] = new Piece[columns];
		}
	}
	
	public void setPiece(Player owner, int row, int col) throws CellAlreadyOccupied{
		if(hasPiece(row, col))
			throw new CellAlreadyOccupied();
		
		board[row][col] = new Piece(owner, row, col);
	}
	
	public boolean hasPiece(int row, int col){
		return board[row][col] == null;
	}
	
	public Piece getPiece(int row, int col){
		return board[row][col];
	}
	
	public int getRows(){
		return this.rows;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	public void resetBoard(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				board[i][j] = null;
			}
		}
	}
	
	public static boolean validRowsColumns(int rows, int columns){
		return rows > 4 && columns > 4;
	}
	
}
