package network;

import java.io.IOException;
import java.util.regex.Pattern;

import network.exceptions.InvalidServerPortException;

public class Server implements Runnable {

	private static final int SERVER_DEFAULT_PORT = 57000;
	private static final Pattern SERVER_PORT_PATTERN = Pattern.compile("\\d{1,5}+");

	private boolean debugMode = false;
	private ServerConnectionHandler connection;

	/**
	 * Creates a new server listening on port 'port'.
	 * @param port String that will be verified through a regex and then parsed into an integer.
	 * @throws InvalidServerPortException Thrown if the regex check fails.
	 */
	public Server(String port) throws InvalidServerPortException {
		if(!validServerPort(port))
			throw new InvalidServerPortException();
			
		try {
			this.connection = new ServerConnectionHandler(Integer.parseInt(port));
		} catch (IOException e) {
			System.err.println("[ERROR] Could not listen on port " + this.connection.getPort());
		}

		initConnection();
	}

	/**
	 * Creates a new server listenin on the default port (57000).
	 */
	public Server() {

		try {
			this.connection = new ServerConnectionHandler(SERVER_DEFAULT_PORT);
		} catch (IOException e) {
			System.err.println("[ERROR] Could not listen on port " + this.connection.getPort());
		}

		initConnection();
	}

	/**
	 * Receives packet.
	 */
	private void receive() {

		do {
			// Reading Loop

		} while (true);

	}
	
	/**
	 * Sends packet.
	 */
	private void send(){
		
	}

	public void run() {
		if(debugMode)
			System.out.println("[INFO] Connection established to client " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
		try {
			this.connection.createStreams();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(debugMode)
			System.out.println("[INFO] Streams ready to client " + this.connection.getAddress() + " on port " + this.connection.getPort() + " .\n");

		receive();

		if(debugMode)
			System.out.println("Ended connection with client " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
	}

	private void initConnection() {
		try {
			this.connection.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean validServerPort(String input) {
		return SERVER_PORT_PATTERN.matcher(input).matches();
	}

}
