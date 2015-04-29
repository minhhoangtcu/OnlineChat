package Client.chat;
import java.io.*;
import java.util.*;
import java.net.*;

import data.SpecialCommands;

public class Client {
	private final static int PORT = 6969;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private Thread thread;
	private String name;
	private String host;
	ClientControl control;
	
	public Client() {
		control = new ClientControl(this);
	}
	
	//Connect to the server
	public void connect() {
		setNameAndHost();
		if (isLegit(name, host)) {
			connectAndStartReadingThread();
			changeViewAfterConnect();
		}
		else {
			control.view.result.append("INVALID NAME. NAME MUST NOT HAVE SPACE. NAME MUST NOT EMPTY \n");
		}
	}
	
	//Get the name of host and ID of client
	private void setNameAndHost() {
		host = control.view.tfSever.getText();
		name = control.view.tfID.getText();
	}
	
	//Check if the name and host typed are valid 
	public boolean isLegit(String name, String host) {
		String[] nameSeperated = name.split(" ");
		if (host.equals("") || name.equals("") || (nameSeperated.length > 1))
			return false;
		else return true;
	}
	
	//Create new socket, and then pass it to the thread and start it
	private void connectAndStartReadingThread() {
		try{
			socket = new Socket(host, PORT);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
		} catch(UnknownHostException uhe) {
			control.view.errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			control.view.errors.setText(ioe.getMessage());
		}
		thread = new ClientReadThread(in, control.view.result, this);
		thread.setName("Reading Thread for " + name);			// why Reading Thread for???
		thread.start();
	}
	
	//disconnect by interrupting the thread
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
		out.close();
		changeViewAfterDisconnect();
	}
	
	//send all the chat content up the server
	public void send() {
		try {
			out.println(control.view.userInput.getText());
			out.flush();
		} catch (NullPointerException e) {}
		changeViewAfterSend();
	}
	
	//get the name of the thread
	public String getName() {
		return name;
	}
	
	//send the name of the thread to the server
	public void sendName() {
		out.println(SpecialCommands.KEYWORD + SpecialCommands.getName + " " +name);
		out.flush();
	}
	
	//Change the view after Connect
	private void changeViewAfterConnect() {
		control.view.sendButton.setEnabled(true);
		control.view.sendButton.setContentAreaFilled(true);
		control.view.connectButton.setText("Disconnect");
		control.view.result.append("CONNECTED \n");
		control.setConnected(true);
		control.view.userInput.setText("");
		control.view.tfID.setEnabled(false);
	}
	
	//Change view after disconnect
	private void changeViewAfterDisconnect() {
		control.view.sendButton.setEnabled(false);
		control.view.sendButton.setContentAreaFilled(false);
		control.view.connectButton.setText("Connect");
		control.setConnected(false);
		control.view.result.append("DISCONECTED FROM THE SERVER \n");
		control.view.userInput.setText("");
		control.view.tfID.setEnabled(true);
	}
	
	//Change view after Send
	private void changeViewAfterSend() {
		control.view.userInput.setText("");
	}
}


