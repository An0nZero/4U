package network.exceptions;

public class InvalidServerAddressException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidServerAddressException(){
		super("Invalid server address.");
	}
	
	public InvalidServerAddressException(String msg){
		super(msg);
	}
	
}
