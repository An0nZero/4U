package network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerConnectionHandler {

	private static final Pattern SERVER_PORT_PATTERN = Pattern.compile( "\\d{1,5}+" );
	
	private String address;
	private int port;
	private ServerSocket servSocket;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public ServerConnectionHandler(Scanner in) throws IOException{
		this.port = readServerPort(in);
		this.servSocket = new ServerSocket(this.port);
	}
	
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
	
	private int readServerPort(Scanner in){
		boolean b;
		String input;
		Matcher m;
		int port;
		
		do{
			System.out.print("Please insert port to listen on:\n-");
			
			input = in.nextLine();
		
			m = SERVER_PORT_PATTERN.matcher(input);
			b = m.matches();
			
			if(!b)
				System.out.println("ERROR: Server port not valid.\n");
			else
				System.out.println("Port: " + input + "\n");
				
		}while(!b);
		
		port = Integer.parseInt(input);
		
		return port;
	}
	
	public static boolean checkRegex(String compare, Pattern regex){
	    Matcher m = regex.matcher(compare);
	    return m.matches();
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
