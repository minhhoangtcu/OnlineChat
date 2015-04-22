import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			if (firstLetter.equals(SpecialCommands.kick)); doKick(getClientID(inputs[1]));
		}
	}
	
	private int getClientID(String text) {
		int number = -1;
		try {
			number = Integer.parseInt(text);
			
			}
		}
		catch (NumberFormatException e) {
			appendText("Invalid input, the second argument must be a number");
		}
		return number;
	}
	
	private void doHelp() {
		String text = "\n"
					+ "KICK clientNumber \t to kick a client out of the system. For example: KICK 0 \n"
					+ "LOG \t\t show the chat log from the begining of the server \n";
		appendText(text);
	}
	
	private void doLog() {
		appendText(SpecialCommands.getLog());
	}
	
	private void doKick(int clientID) {
		
	}
}
