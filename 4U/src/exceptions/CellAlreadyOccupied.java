package exceptions;

public class CellAlreadyOccupied extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CellAlreadyOccupied(){
		super("Cell already has a piece.");
	}
	
	public CellAlreadyOccupied(String msg){
		super(msg);
	}
	
}
