package server;

import java.io.IOException;
import java.util.Scanner;

public class Server implements Runnable{
	
	private ServerConnectionHandler connection;
	
	public Server(Scanner in){;
		
		try {
			this.connection = new ServerConnectionHandler(in);
		} catch (IOException e) {
			System.err.println("[ERROR] Could not listen on port " + this.connection.getPort());
		}
		
		initConnection();
	}
	
	public Server(int port){
		
		try {
			this.connection = new ServerConnectionHandler(port);
		} catch (IOException e) {
			System.err.println("[ERROR] Could not listen on port " + this.connection.getPort());
		}
		
		initConnection();
	}
	
	public void receive(){
		
		do{
			//Reading Loop
			
		}while(true);
		
	}
	
	public void run() {
		try {
			System.out.println("[INFO] Connection established to client " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
		    this.connection.createStreams();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("[INFO] Streams ready to client " + this.connection.getAddress() + " on port " + this.connection.getPort() + " .\n");
		
		receive();
		
		System.out.println("Ended connection with client " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
	}
	
	private void initConnection(){
		try {
			while(true){
				this.connection.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
