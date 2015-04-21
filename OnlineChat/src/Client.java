import javax.swing.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
	private final static int PORT = 6969;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private Thread thread;
	private ClientControl control;
	
	public static void main(String[] args) {
		Client display = new Client();
	}
	
	public Client() {
		control = new ClientControl(this);
	}
	
	public void connect() {
		String host = control.view.tfSever.getText();
		String name = control.view.tfID.getText();
		
		if (host.equals("") || name.equals("")) {
			return;
		}
		try{
			socket = new Socket(host, PORT);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
		} catch(UnknownHostException uhe) {
			control.view.errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			control.view.errors.setText(ioe.getMessage());
		}
		thread = new ReadThread(in, control.view.result);
		thread.setName(name);
		thread.start();
		
		changeViewAfterConnect(name);
	}
	
	public void changeViewAfterConnect(String name) {
		control.view.sendButton.setEnabled(true);
		control.view.sendButton.setContentAreaFilled(true);
		control.view.setTitle(name);
		control.view.connectButton.setText("Disconnect");
		control.setConnected(true);
		control.view.userInput.setText("");
	}
	
	public void disconnect() {
		thread.interrupt();
		try{
			socket.close();
		} catch(UnknownHostException uhe) {
			control.view.errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			control.view.errors.setText(ioe.getMessage());
		}
		in.close();
		out.print(false);
		out.close();
		changeViewAfterDisconnect();
	}
	
	public void changeViewAfterDisconnect() {
		control.view.sendButton.setEnabled(false);
		control.view.sendButton.setContentAreaFilled(false);
		control.view.connectButton.setText("Connect");
		control.setConnected(false);
		control.view.result.setText("");
		control.view.userInput.setText("");
	}
	
	public void send() {
		out.println(control.view.userInput.getText());
		out.println(thread.getName());
		out.flush();
		changeViewAfterSend();
	}
	
	public void changeViewAfterSend() {
		control.view.userInput.setText("");
	}
}

class ReadThread extends Thread {
	Scanner in;
	JTextArea display;
	public ReadThread(Scanner br, JTextArea jta) {
		in = br;
		display = jta;
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
