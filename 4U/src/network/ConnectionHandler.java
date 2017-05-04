package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionHandler {

	private String address;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ConnectionHandler(String address, int port) {
		this.address = address;
		this.port = port;
	}

	public void start() throws UnknownHostException, IOException {
		this.socket = new Socket(this.address, this.port);
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public void send(Object o) {
		try {
			this.out.writeObject(o);
			this.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object read() {
		try {
			return in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		// This should never happen
		return null;
	}

	public String getAddress() {
		return this.address;
	}

	public int getPort() {
		return this.port;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public ObjectOutputStream getOutStream() {
		return this.out;
	}

	public ObjectInputStream getInStream() {
		return this.in;
	}

}
