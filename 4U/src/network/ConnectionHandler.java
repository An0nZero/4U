package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionHandler {

	/**
	 * Address to attempt connection.
	 */
	private String address;
	
	/**
	 * Port to attempt connection.
	 */
	private int port;
	
	/**
	 * Socket created with the given address and port.
	 */
	private Socket socket;
	
	/**
	 * Output stream used to send objects.
	 */
	private ObjectOutputStream out;
	
	/**
	 * Input stream created to receive objects.
	 */
	private ObjectInputStream in;

	/**
	 * Creates a new connection to the given address on the given port.
	 * @param address address
	 * @param port port
	 */
	public ConnectionHandler(String address, int port) {
		this.address = address;
		this.port = port;
	}

	/**
	 * Starts the socket by trying to connect to the address given upon creation of the connection.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void start() throws UnknownHostException, IOException {
		this.socket = new Socket(this.address, this.port);
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Closes the connection.
	 * @throws IOException
	 */
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	/**
	 * Tries to send an object over the connection.
	 * @param o
	 */
	public void send(Object o) {
		try {
			this.out.writeObject(o);
			this.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tries to read an object over the connection.
	 * @return the object read.
	 */
	public Object read() {
		try {
			return in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		// This should never happen
		return null;
	}

	/**
	 * Returns the address given on creation.
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Returns the port given on creation.
	 * @return port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Returns the socket used to establish the connection.
	 * @return socket
	 */
	public Socket getSocket() {
		return this.socket;
	}

	/**
	 * Returns the output stream created to write objects.
	 * @return output stream
	 */
	public ObjectOutputStream getOutStream() {
		return this.out;
	}

	/**
	 * Returns the input stream created to read objects.
	 * @return input stream
	 */
	public ObjectInputStream getInStream() {
		return this.in;
	}

}
