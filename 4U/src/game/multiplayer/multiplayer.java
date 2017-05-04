package game.multiplayer;

import java.util.Scanner;

import network.client.Client;

public class multiplayer {

	private String readServerAddress(Scanner in) {
		boolean b;
		String input;

		do {
			System.out.print("Please insert server address:\n-");

			input = in.nextLine();

			b = Client.validServerAddress(input);

			if (!b)
				System.out.println("[ERROR] Server address not valid.\n");
			else
				System.out.println("[INFO] Address: " + input + "\n");

		} while (!b);

		return input;
	}
	
	private int readServerPort(Scanner in) {
		boolean b;
		String input;
		int port;

		do {
			System.out.print("Please insert server port:\n-");

			input = in.nextLine();

			b = Client.validServerPort(input);

			if (!b)
				System.out.println("[ERROR] Server port not valid.\n");
			else
				System.out.println("Port: " + input + "\n");

		} while (!b);

		port = Integer.parseInt(input);

		return port;
	}
	
}
