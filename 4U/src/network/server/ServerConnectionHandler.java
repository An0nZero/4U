package network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionHandler {
	
	private String address;
	private int port;
	private ServerSocket servSocket;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ServerConnectionHandler(int port) throws IOException{
		this.port = port;
		this.servSocket = new ServerSocket(this.port);
	}
	
	public void start() throws IOException{
		this.socket = servSocket.accept();
		this.address = socket.getInetAddress().toString();
	}
	
	public void createStreams() throws IOException{
		this.out = new ObjectOutputStream(socket.getOutputStream());
	    this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void close() throws IOException{
		in.close();
		out.close();
		socket.close();
	}
	
	public void send(Object o){
		try {
			this.out.writeObject(o);
			this.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object read() throws ClassNotFoundException, IOException{
		return in.readObject();
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public ServerSocket getServerSocket(){
		return this.servSocket;
	}
	
	public ObjectOutputStream getOutStream(){
		return this.out;
	}
	
	public ObjectInputStream getInStream(){
		return this.in;
	}
	
}
