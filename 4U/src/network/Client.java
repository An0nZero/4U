package network;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import network.exceptions.InvalidServerAddressException;
import network.exceptions.InvalidServerPortException;

public class Client {

	private static final String SERVER_DEFAULT_ADDRESS = "127.0.0.1";
	private static final int SERVER_DEFAULT_PORT = 57000;

	private static final Pattern SERVER_ADDRESS_PATTERN = Pattern.compile("\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+");
	private static final Pattern SERVER_PORT_PATTERN = Pattern.compile("\\d{1,5}+");

	private final boolean debugMode;
	
	private ConnectionHandler connection;
	
	/**
	 * Creates a new client with the localhost address (127.0.0.1) and with a
	 * default port (57000).
	 */
	public Client() {
		this.connection = new ConnectionHandler(SERVER_DEFAULT_ADDRESS, SERVER_DEFAULT_PORT);
		this.debugMode = false;
		
		connect();
	}

	/**
	 * @throws InvalidServerAddressException 
	 * @throws InvalidServerPortException 
	 * 
	 */
	public Client(String address, String port) throws InvalidServerAddressException, InvalidServerPortException {
		if(!validServerAddress(address))
			throw new InvalidServerAddressException();
		if(!validServerPort(port))
			throw new InvalidServerPortException();
		
		this.connection = new ConnectionHandler(address, Integer.parseInt(port));
		this.debugMode = false;
		
		connect();
	}
	
	public Client(String address, String port, boolean debugMode) throws InvalidServerAddressException, InvalidServerPortException {
		if(!validServerAddress(address))
			throw new InvalidServerAddressException();
		if(!validServerPort(port))
			throw new InvalidServerPortException();
		
		this.connection = new ConnectionHandler(address, Integer.parseInt(port));
		this.debugMode = debugMode;
		
		connect();
	}

	private void connect() {
		try {
			this.connection.start();
		} catch (UnknownHostException e) {
			System.err.println("[ERROR] Can't find host: " + this.connection.getAddress() + "\n");
		} catch (IOException e) {
			System.err.println("[ERROR] Couldn't get I/O for the connection to: " + this.connection.getAddress() + ":"
					+ this.connection.getPort());
		}
		
		if(debugMode)
			System.out.println("[INFO] Connection established to host " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
	}

	public static boolean validServerAddress(String address) {
		return SERVER_ADDRESS_PATTERN.matcher(address).matches();
	}

	public static boolean validServerPort(String port) {
		return SERVER_PORT_PATTERN.matcher(port).matches();
	}

}
