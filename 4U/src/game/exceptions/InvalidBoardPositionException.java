package game.exceptions;

public class InvalidBoardPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidBoardPositionException(){
		super("Indicated Position of the Board is invalid");
	}
	
	public InvalidBoardPositionException(String msg){
		super(msg);
	}
}
