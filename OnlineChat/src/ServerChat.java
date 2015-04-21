import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ServerChat {
	ArrayList<ServiceChat> services;
	ServerSocket server;
	ServerView view;
	final int PORT = 6969;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.initClients();
	}
	
	public ServerChat() {
		services = new ArrayList<ServiceChat> ();
		view = new ServerView();
		view.setVisible(true);
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
			 System.out.println("Client " + services.size() +" connected");
			 ServiceChat service = new ServiceChat(client, this);
			 addClientIntoServicesAndStartIt(service);
		 }
	}
	
	private void addClientIntoServicesAndStartIt(ServiceChat service) {
		services.add(service);
		Thread t = new Thread(service);
		t.start();
	}
	
	public void printAll(String input, String name) {
		for (ServiceChat service: services) {
			service.print(input, name);
		}
	}
}
