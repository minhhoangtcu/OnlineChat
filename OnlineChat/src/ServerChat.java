import java.io.IOException;
import java.net.*;

public class ServerChat {
	ServiceChat[] services;
	ServerSocket server;
	ServerView view;
	final int PORT = 6969;
	final int MAX_NUMBER_OF_CLIENTS = 10;
	int currentNumberOfClients;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.initClients();
	}
	
	public ServerChat() {
		view = new ServerView();
		view.setVisible(true);
		services = new ServiceChat[MAX_NUMBER_OF_CLIENTS];
		currentNumberOfClients = 0;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server initiated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method handle error for the method startClients
	 */
	public void initClients() {
		try {
			startClients();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method wait and run thread of each client
	 */
	public void startClients() throws IOException{
		 while (true) {
			 Socket client = server.accept();
			 System.out.println("Client " + currentNumberOfClients +" connected");
			 ServiceChat service = new ServiceChat(client, this, currentNumberOfClients);
			 addClientIntoServicesAndStartIt(service);
		 }
	}
	
	private void addClientIntoServicesAndStartIt(ServiceChat service) {
		services[currentNumberOfClients] = service;
		currentNumberOfClients++;
		Thread t = new Thread(service);
		t.start();
	}
	
	public void printAll(String input) {
		for (int i = 0; i < currentNumberOfClients; i++) {
			services[i].print(input);
		}
	}
}
