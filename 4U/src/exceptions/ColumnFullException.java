package exceptions;

public class ColumnFullException extends Exception {

	private static final long serialVersionUID = 1L;

	public ColumnFullException(){
		super("The Column is full, unable to insert piece in the given column.");
	}
	
	public ColumnFullException(String msg){
		super(msg);
	}
	
}
