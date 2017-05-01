package network.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionHandler {

	private static final String SERVER_ADDRESS_PATTERN = "\\d{1,3}+.\\d{1,3}+.\\d{1,3}+.\\d{1,3}+";
	private static final String SERVER_PORT_PATTERN = "\\d{1,5}+";
	
	private String address;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public ConnectionHandler(Scanner in){
		this.address = readServerAddress(in);
		this.port = readServerPort(in);
	}
	
	public ConnectionHandler(String address, int port){
		this.address = address;
		this.port = port;
	}
	
	public void start() throws UnknownHostException, IOException{
		this.socket = new Socket(this.address, this.port);
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
	
	public Object read(){
			try {
				return in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			//This should never happen
			return null;
	}
	
	private String readServerAddress(Scanner in){
		boolean b;
		String input;
		
		do{
			System.out.print("Please insert server address:\n-");
			
			input = in.nextLine();
		
			b = checkRegex(input, SERVER_ADDRESS_PATTERN);
			
			if(!b)
				System.out.println("[ERROR] Server address not valid.\n");
			else
				System.out.println("[INFO] Address: " + input + "\n");
				
		}while(!b);
		
		return input;
	}
	
	private int readServerPort(Scanner in){
		boolean b;
		String input;
		int port;
		
		do{
			System.out.print("Please insert server port:\n-");
			
			input = in.nextLine();
		
			b = checkRegex(input, SERVER_PORT_PATTERN);
			
			if(!b)
				System.out.println("[ERROR] Server port not valid.\n");
			else
				System.out.println("Port: " + input + "\n");
				
		}while(!b);
		
		port = Integer.parseInt(input);
		
		return port;
	}
	
	public static boolean checkRegex(String compare, String regex){
		Pattern p;
		Matcher m;
		
		p = Pattern.compile(regex);
		m = p.matcher(compare);
		
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
	
	public ObjectOutputStream getOutStream(){
		return this.out;
	}
	
	public ObjectInputStream getInStream(){
		return this.in;
	}
	
}
