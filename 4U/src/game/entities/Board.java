package game.entities;

import exceptions.CellAlreadyOccupiedException;

public class Board {

	private static final int DEFAULT_ROWS = 6;
	private static final int DEFAULT_COLUMNS = 10;
	
	private final int rows;
	private final int columns;
	
	private Piece[][] board;
	private Piece lastPiece;
	
	public Board(){
		this( DEFAULT_ROWS, DEFAULT_COLUMNS );
	}
	
	public Board(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.board = new Piece[rows][columns];
	}
	
	public void setPiece(Player owner, int col) throws CellAlreadyOccupiedException{
	    
	    int row = 0;
		
	    // Make the piece fall into the correct place
	    for(int i = row + 1; i < this.rows && board[i][col] == null; i++) {
	        row++;
	    }
        
	    if(hasPiece(row, col))
	        throw new CellAlreadyOccupiedException();
        
	    lastPiece = board[row][col] = new Piece(owner, row, col);
	}
	
	public Piece lastPiece() {
	    return this.lastPiece;
	}
	
	public boolean hasPiece(int row, int col){
		return board[row][col] != null;
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
	    this.lastPiece = null;
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
