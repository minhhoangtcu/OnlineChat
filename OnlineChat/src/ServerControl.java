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
		view.output.append(text);
	}
	
	public void checkIfInputIsSpecialThenProceed(String text) {
		text = text.toLowerCase();
		if (text.equals("help")) {
			
		}
	}
}
