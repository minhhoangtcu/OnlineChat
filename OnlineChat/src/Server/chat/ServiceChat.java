package Server.chat;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import data.SpecialCommands;
import Server.ServerChat;

public class ServiceChat implements Runnable {
	private Socket client;
	private ServerChat server;
	private Scanner in;
	private PrintWriter out;
	private int clientID;
	private String clientName;
	
	public ServiceChat(Socket client, ServerChat server, int clientID) {
		this.client = client;
		this.server = server;
		this.clientID = clientID;
	}

	public void run() {
		try {
			in  = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			requestName();
			doService();
			client.close();
			server.removeFromServices(this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String input, String name) {
		String output = "";
		if (clientName.equals(name)) output += "> ";
		output += name + ": " + input;
		out.println(output);
		out.flush();
	}
	
	public void print(String input) {
		out.println(input);
		out.flush();
	}
	
	public String getName() {
		return clientName;
	}
	
	public int getID() {
		return clientID;
	}

	private void requestName() {
		out.println(SpecialCommands.KEYWORD + SpecialCommands.getName);
		out.flush();
	}
	
	private void doService() {
		String input;
		try {
			while ((input = in.nextLine()) != null) {
				executeInput(input);
			}
		} catch (NoSuchElementException | IllegalStateException e) {
			// TODO write something to handle the error. 
		}
	}

	private void executeInput(String input) {
		String[] inputs = input.split(" ");
		String firstLetter = inputs[0];
		
		if (inputs.length == 2 && firstLetter.equals(SpecialCommands.KEYWORD + SpecialCommands.getName)) {
			clientName = inputs[1];
		}
		else server.printAll(input, this);
	}
}

