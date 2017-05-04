package exceptions;

public class CellAlreadyOccupiedException extends Exception{

	private static final long serialVersionUID = 1L;

	public CellAlreadyOccupiedException(){
		super("Cell already has a piece.");
	}
	
	public CellAlreadyOccupiedException(String msg){
		super(msg);
	}
	
}
