import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JTextArea;

/*
 * This is a thread that always read data from the input of a client
 */
class ReadThread extends Thread {
	Scanner in;
	JTextArea display;
	
	public ReadThread(Scanner in, JTextArea display) {
		this.in = in;
		this.display = display;
	}
	
	public void run() {
		String s;
		try {
			while ((s = in.nextLine()) != null) {
				display.append(s + '\n');
			}
		} catch (NoSuchElementException e) {}
	}
}