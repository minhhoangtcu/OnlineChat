import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControl implements ActionListener{
	
	private ServerView view;
	
	public static void main(String[] args) {
		ServerControl control = new ServerControl();
	}
	
	public ServerControl() {
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
		text = text.toLowerCase();
		if (text.equals(SpecialCommands.help)) doHelp(); 
		if (text.equals(SpecialCommands.log)) doLog();
	}
	
	private void doHelp() {
		String text = "> KICK clientNumber \t to kick a client out of the system. For example: KICK 0 \n"
					+ "> LOG \t\t show the chat log from the begining of the server \n";
		appendText(text);
	}
	
	private void doLog() {
		appendText(SpecialCommands.getLog());
	}
}
