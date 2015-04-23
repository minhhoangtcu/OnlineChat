import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JTextArea;

/*
 * This is a thread that always read data from the input of a client
 */
class ReadThread extends Thread {
	private Scanner in;
	private JTextArea display;
	private Client client;
	
	public ReadThread(Scanner in, JTextArea display, Client client) {
		this.in = in;
		this.display = display;
		this.client = client;
	}
	
	public void run() {
		String s;
		try {
			while ((s = in.nextLine()) != null) {
				if (s.equals(SpecialCommands.KEYWORD + SpecialCommands.getName)) client.sendName();
				else if (s.equals(SpecialCommands.KEYWORD + SpecialCommands.kick)) client.disconnect();
				else display.append(s + '\n');
			}
		} catch (NoSuchElementException e) {}
	}
}