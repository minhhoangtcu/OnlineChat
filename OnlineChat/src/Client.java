import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client extends JFrame {
	JPanel contentPane;
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	Thread thread;
	ClientView clientView;
	
	public Client() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		clientView = new ClientView(this);
		add(clientView);
	}
	
	public void connect() {
		try {
			Scanner scanner = new Scanner(clientView.userInput.getText());
			if (!scanner.hasNext()) return;
			String host = scanner.next();
			if (!scanner.hasNextInt()) return;
			int port = scanner.nextInt();
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), false);
			thread = new ReadThread(in, clientView.result);
			thread.start();
			clientView.sendButton.setEnabled(true);
			clientView.connectButton.setText("Disconnect");
			clientView.userInput.setText("");
		} catch(UnknownHostException uhe) {
			clientView.errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			clientView.errors.setText(ioe.getMessage());
		}
	}
	
	public void disconnect() {
		try {
			thread.interrupt();
			socket.close();
			in.close();
			out.close();
			clientView.sendButton.setEnabled(false);
			clientView.connectButton.setText("Connect");
		} catch(UnknownHostException uhe) {
			clientView.errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			clientView.errors.setText(ioe.getMessage());
		}
	}
	
	public void send() {
		out.print(clientView.userInput.getText() + "\r\n");
		out.flush();
		clientView.result.append("> " + clientView.userInput.getText() + '\n');
		clientView.userInput.setText("");
	}
	
	
	public static void main(String[] args) {
		Client display = new Client();
		display.setVisible(true);
	}
}

class ReadThread extends Thread {
	BufferedReader in;
	JTextArea display;
	public ReadThread(BufferedReader br, JTextArea jta) {
		in = br;
		display = jta;
	}
	public void run() {
		String s;
		try {
			while ((s = in.readLine()) != null) {
				display.append(s + '\n');
			}
		} catch (IOException ioe) {
			System.out.println("Error reading from socket");
		}
	}
}