package network.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import network.connection.ConnectionHandler;

public class Client{
	
	private ConnectionHandler connection;
	
	public Client(Scanner in){
		this.connection = new ConnectionHandler(in);
		
		connect();
	}
	
	public Client(Scanner in, String address, int port){
		this.connection = new ConnectionHandler(address, port);
		
		connect();
	}
	
	private void connect(){
		try {
			this.connection.start();
		    System.out.println("[INFO] Connection established to host " + this.connection.getAddress() + " on port " + this.connection.getPort() + ".\n");
        } catch (UnknownHostException e) {
            System.err.println("[ERROR] Can't find host: " + this.connection.getAddress() + "\n");
        } catch (IOException e) {
            System.err.println("[ERROR] Couldn't get I/O for the connection to: " + this.connection.getAddress() + ":" + this.connection.getPort());
        }
	}
	
}
