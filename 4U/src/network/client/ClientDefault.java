package network.client;

import java.util.Scanner;

public class ClientDefault extends Client{

	private static final String SERVER_DEFAULT_ADDRESS = "127.0.0.1";
	private static final int SERVER_DEFAULT_PORT = 57000;
	
	public ClientDefault(Scanner in){
		super(in, SERVER_DEFAULT_ADDRESS, SERVER_DEFAULT_PORT);
	}

}
