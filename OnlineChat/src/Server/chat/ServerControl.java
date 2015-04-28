package Server.chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data.SpecialCommands;
import Server.ServerChat;
import Server.commands.*;

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
			appendText("> " + text);
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
		
		if (firstLetter.equals(SpecialCommands.help) && (inputs.length == 1)) doHelp(); 
		else if (firstLetter.equals(SpecialCommands.log)  && (inputs.length == 1)) doLog();
		else if (firstLetter.equals(SpecialCommands.users)  && (inputs.length == 1)) doUsers();
		else if (firstLetter.equals(SpecialCommands.kick) && (inputs.length == 2)) doKick(inputs);
		else {
			appendText("Ivalid input, type \"" + SpecialCommands.help +"\" for list of commands.");
		}
	}
	
	private int getClientID(String text) throws NumberFormatException{
		int number = Integer.parseInt(text);
		return number;
	}
	
	private void doHelp() {
		new HelpCommand().executeCommand(this);
	}
	
	private void doLog() {
		new LogCommand().executeCommand(this);
	}
	
	private void doKick(String[] inputs) {
		String firstLetter = inputs[0];
		firstLetter = firstLetter.toLowerCase();
		try {
			int clientID = getClientID(inputs[1]);
			if (server.containClientID(clientID)) {
				new KickCommand().executeCommand(this, server, clientID);
			}
		} catch (NumberFormatException e) {
			appendText("Invalid input, the second argument must be a client ID");
		}
		
	}
	
	private void doUsers() {
		new UsersCommand().executeCommand(this, server);
	}
}
