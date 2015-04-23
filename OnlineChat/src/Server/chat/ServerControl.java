package Server.chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.SpecialCommands;
import Server.ServerChat;

public class ServerControl implements ActionListener{
	
	private ServerView view;
	private ServerChat server;
	
	public ServerControl(ServerChat server) {
		this.server = server;
		view = new ServerView();
		view.setVisible(true);
		view.input.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event.equals(view.input)) {
			String text = view.input.getText();
			appendText(text);
			checkIfInputIsSpecialThenProceed(text);
			view.input.setText("");
		}
	}

	public void appendText(String text) {
		view.output.append(text + "\n");
	}
	
	public void checkIfInputIsSpecialThenProceed(String text) {
		String[] inputs = text.split(" ");
		String firstLetter = inputs[0];
		firstLetter = firstLetter.toLowerCase();
		
		if (inputs.length == 1) {
			if (firstLetter.equals(SpecialCommands.help)) doHelp(); 
			else if (firstLetter.equals(SpecialCommands.log)) doLog();
		}
		else if (inputs.length == 2) {
			try {
				int clientID = getClientID(inputs[1]);
				if (firstLetter.equals(SpecialCommands.kick) && server.containClientID(clientID)); doKick(clientID);
			} catch (NumberFormatException e) {
				appendText("Invalid input, the second argument must be a number");
			}
		}
		else {
			appendText("Ivalid input, type \"" + SpecialCommands.help +"\" for list of commands.");
		}
	}
	
	private int getClientID(String text) throws NumberFormatException{
		int number = Integer.parseInt(text);
		return number;
	}
	
	private void doHelp() {
		String text = "\n"
					+ SpecialCommands.kick.toUpperCase() + "\t kick a client out of the system. \n"
					+ SpecialCommands.log.toUpperCase() + "\t show the chat log from the begining of the server \n"
					+ SpecialCommands.users.toUpperCase() + "\t show all clients connected to this server";
		appendText(text);
	}
	
	private void doLog() {
		appendText(SpecialCommands.getLog());
	}
	
	private void doKick(int clientID) {
		server.kick(clientID);
	}
}
