package game;

public class Piece {

	private Player owner;
	
	public Piece(Player owner){
		this.owner = owner;
	}
	
	public Player getOwner(){
		return this.owner;
	}
	
}
