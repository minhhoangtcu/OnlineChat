import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControl implements ActionListener{
	
	ServerView view;
	
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
			
		}
	}

	
	
	

}
