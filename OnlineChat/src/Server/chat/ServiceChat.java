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
	
	//Create a constructor
	public ServiceChat(Socket client, ServerChat server, int clientID) {
		this.client = client;
		this.server = server;
		this.clientID = clientID;
	}

	//method to run the thread 
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
	//method to return the input to the client associated with this thread
	public void print(String input, String name) {
		String output = "";
		if (clientName.equals(name)) output += "> ";
		output += name + ": " + input;
		out.println(output);
		out.flush();
	}
	
	//return the input to the client
	public void print(String input) {
		out.println(input);
		out.flush();
	}
	
	//get name of the client
	public String getName() {
		return clientName;
	}
	
	//get id of the client
	public int getID() {
		return clientID;
	}

	//request the name of the client when firt run
	private void requestName() {
		out.println(SpecialCommands.KEYWORD + SpecialCommands.getName);
		out.flush();
	}
	
	//receive input from the client's output
	private void doService() {
		String input;
		try {
			while (in.hasNext()) {
				input = in.nextLine();
			//while ((input = in.nextLine()) != null) {
				executeInput(input);
			}
		} catch (NoSuchElementException | IllegalStateException e) {
			// TODO write something to handle the error. 
		}
	}

	//manipulate the input received
	private void executeInput(String input) {
		String[] inputs = input.split(" ");
		String firstLetter = inputs[0];
		
		if (inputs.length == 2 && firstLetter.equals(SpecialCommands.KEYWORD + SpecialCommands.getName)) {
			clientName = inputs[1];
		}
		else server.printAll(input, this);
	}
}

