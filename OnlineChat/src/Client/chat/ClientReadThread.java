package Client.chat;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JTextArea;

import data.SpecialCommands;

/*
 * This is a thread that always read data from the input of a client
 */
class ClientReadThread extends Thread {
	private Scanner in;
	private JTextArea display;
	private Client client;
	
	//Constructor of the thread
	public ClientReadThread(Scanner in, JTextArea display, Client client) {
		this.in = in;
		this.display = display;
		this.client = client;
	}
	
	//Receive input from server output
	public void run() {
		String input;
		try {
			while ((input = in.nextLine()) != null) {
				executeInput(input);
			}
		} catch (NoSuchElementException | IllegalStateException e) {
			// TODO write something to handle the error. 
		}
	}
	
	//manipulate the inputs by sending back to server or display it on the client chat view
	private void executeInput(String input) {
		if (input.equals(SpecialCommands.KEYWORD + SpecialCommands.getName)) client.sendName();
		else if (input.equals(SpecialCommands.KEYWORD + SpecialCommands.kick)) client.disconnect();
		else display.append(input + '\n');
	}
}