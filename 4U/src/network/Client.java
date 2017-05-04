package network;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import network.exceptions.InvalidServerAddressException;
import network.exceptions.InvalidServerPortException;

public class Client {

	/**
	 * Default server address.
	 */
	private static final String SERVER_DEFAULT_ADDRESS = "127.0.0.1";

	/**
	 * Default server port.
	 */
	private static final int SERVER_DEFAULT_PORT = 57000;

	/**
	 * Pattern used to check the given address.
	 */
	private static final Pattern SERVER_ADDRESS_PATTERN = Pattern.compile("\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+");

	/**
	 * Pattern used to check the given port.
	 */
	private static final Pattern SERVER_PORT_PATTERN = Pattern.compile("\\d{1,5}+");

	/**
	 * Variable that defines if the user is in debug mode.
	 */
	private final boolean debugMode;

	/**
	 * Connection to the server.
	 */
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
	 * Creates a new client wich will try to connect to 'address' on port
	 * 'port'.
	 * 
	 * @param address
	 *            address to attempt connection
	 * @param port
	 *            port to attempt connection
	 * @throws InvalidServerAddressException
	 * @throws InvalidServerPortException
	 */
	public Client(String address, String port) throws InvalidServerAddressException, InvalidServerPortException {
		if (!validServerAddress(address))
			throw new InvalidServerAddressException();
		if (!validServerPort(port))
			throw new InvalidServerPortException();

		this.connection = new ConnectionHandler(address, Integer.parseInt(port));
		this.debugMode = false;

		connect();
	}

	/**
	 * Creates a new client similar to Client(String address, String port), but
	 * specifying debug mode (true/false).
	 * 
	 * @param address
	 *            address to attempt connection
	 * @param port
	 *            port to attempt connection
	 * @param debugMode
	 *            debug mode
	 * @throws InvalidServerAddressException
	 * @throws InvalidServerPortException
	 */
	public Client(String address, String port, boolean debugMode)
			throws InvalidServerAddressException, InvalidServerPortException {
		if (!validServerAddress(address))
			throw new InvalidServerAddressException();
		if (!validServerPort(port))
			throw new InvalidServerPortException();

		this.connection = new ConnectionHandler(address, Integer.parseInt(port));
		this.debugMode = debugMode;

		connect();
	}

	/**
	 * Tries to connect to the address with wich the connection was created.
	 */
	private void connect() {
		try {
			this.connection.start();
		} catch (UnknownHostException e) {
			System.err.println("[ERROR] Can't find host: " + this.connection.getAddress() + "\n");
		} catch (IOException e) {
			System.err.println("[ERROR] Couldn't get I/O for the connection to: " + this.connection.getAddress() + ":"
					+ this.connection.getPort());
		}

		if (debugMode)
			System.out.println("[INFO] Connection established to host " + this.connection.getAddress() + " on port "
					+ this.connection.getPort() + ".\n");
	}

	/**
	 * Checks if the given string is a valid address.
	 * @param address address to check
	 * @return true if checks with the regex, false if not.
	 */
	public static boolean validServerAddress(String address) {
		return SERVER_ADDRESS_PATTERN.matcher(address).matches();
	}

	/**
	 * Checks if the given string is a valid port.
	 * @param port port to check
	 * @return true if checks with the regex, false if not.
	 */
	public static boolean validServerPort(String port) {
		return SERVER_PORT_PATTERN.matcher(port).matches();
	}

}
