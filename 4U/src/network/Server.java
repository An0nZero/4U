package network;

import java.io.IOException;
import java.util.regex.Pattern;

import network.exceptions.InvalidServerPortException;

public class Server {

	/**
	 * Default server port.
	 */
	private static final int SERVER_DEFAULT_PORT = 57000;

	/**
	 * Pattern used to check the given port.
	 */
	private static final Pattern SERVER_PORT_PATTERN = Pattern.compile("\\d{1,5}+");

	/**
	 * Variable that defines if the user is in debug mode.
	 */
	private boolean debugMode = false;

	/**
	 * Connection with another client.
	 */
	private ServerConnectionHandler connection;

	/**
	 * Creates a new server listening on port 'port'.
	 * 
	 * @param port
	 *            String that will be verified through a regex and then parsed
	 *            into an integer.
	 * @throws InvalidServerPortException
	 *             Thrown if the regex check fails.
	 */
	public Server(String port) throws InvalidServerPortException {
		if (!validServerPort(port))
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
			send();
		} while (true);

	}

	/**
	 * Sends packet.
	 */
	private void send() {

	}

	/**
	 * Establishes the connection and starts receiving packets.
	 */
	private void run() {
		if (debugMode)
			System.out.println("[INFO] Connection established to client " + this.connection.getClientAddress() + " on port "
					+ this.connection.getPort() + ".\n");
		try {
			this.connection.createStreams();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (debugMode)
			System.out.println("[INFO] Streams ready to client " + this.connection.getClientAddress() + " on port "
					+ this.connection.getPort() + " .\n");

		receive();

		if (debugMode)
			System.out.println("[INFO] Ended connection with client " + this.connection.getClientAddress() + " on port "
					+ this.connection.getPort() + ".\n");
	}

	/**
	 * Creates the connection and calls method run().
	 */
	private void initConnection() {
		try {
			this.connection.start();
			this.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the string 'input' is valid.
	 * 
	 * @param input
	 *            String to check if is valid.
	 * @return true if matches the regex, false if not.
	 */
	public static boolean validServerPort(String input) {
		return SERVER_PORT_PATTERN.matcher(input).matches();
	}

}
