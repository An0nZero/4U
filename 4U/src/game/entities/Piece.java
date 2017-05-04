package game.entities;

public class Piece {

	private Player owner;
	private int row;
	private int col;
	
	public Piece(Player owner, int row, int col){
		this.owner = owner;
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public Player getOwner(){
		return this.owner;
	}
	
}
