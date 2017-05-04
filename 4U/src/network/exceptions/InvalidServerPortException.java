package network.exceptions;

public class InvalidServerPortException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidServerPortException(){
		super("Invalid server port.");
	}
	
	public InvalidServerPortException(String msg){
		super(msg);
	}
	
}
