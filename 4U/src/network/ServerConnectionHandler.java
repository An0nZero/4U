package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionHandler {

	/**
	 * Client address.
	 */
	private String clientAddress;

	/**
	 * Server port.
	 */
	private int port;

	/**
	 * Server socket established with the client.
	 */
	private ServerSocket servSocket;

	/**
	 * Generic socket of the server socket.
	 */
	private Socket socket;

	/**
	 * Output stream used to write to the client.
	 */
	private ObjectOutputStream out;

	/**
	 * Input stream used to receive from the client.
	 */
	private ObjectInputStream in;

	/**
	 * Creates a new socket.
	 * 
	 * @param port
	 *            Port to listen on.
	 * @throws IOException
	 *             thrown if something goes wrong.
	 */
	public ServerConnectionHandler(int port) throws IOException {
		this.port = port;
		this.servSocket = new ServerSocket(this.port);
	}

	/**
	 * Start waiting for an incoming connection.
	 * 
	 * @throws IOException
	 *             thrown if something goes wrong.
	 */
	public void start() throws IOException {
		this.socket = servSocket.accept();
		this.clientAddress = socket.getInetAddress().toString();
	}

	/**
	 * Creates the streams to receive and send.
	 * 
	 * @throws IOException
	 */
	public void createStreams() throws IOException {
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Closes the streams and the socket.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	/**
	 * Sends an object over the established connection.
	 * 
	 * @param o
	 *            Object to send.
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
	 * Reads and object over the established connection.
	 * 
	 * @return Object read
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object read() throws IOException, ClassNotFoundException {
		return in.readObject();
	}

	/**
	 * Returns the address from the client.
	 * 
	 * @return address from the client.
	 */
	public String getClientAddress() {
		return this.clientAddress;
	}

	/**
	 * Returns the port on wich the server is listening.
	 * 
	 * @return server port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Returns the generic socket.
	 * 
	 * @return generic socket
	 */
	public Socket getSocket() {
		return this.socket;
	}

	/**
	 * Returns the server socket.
	 * 
	 * @return server socket
	 */
	public ServerSocket getServerSocket() {
		return this.servSocket;
	}

	/**
	 * Returns the stream to where is writing.
	 * @return output stream
	 */
	public ObjectOutputStream getOutStream() {
		return this.out;
	}

	/**
	 * Returns the stream from where is reading.
	 * @return input stream
	 */
	public ObjectInputStream getInStream() {
		return this.in;
	}

}
