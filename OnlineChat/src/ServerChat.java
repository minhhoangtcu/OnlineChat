import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ServerChat {
	ArrayList<ServiceChat> services;
	ServerSocket server;
	ServerControl control;
	final int PORT = 6969;
	private int currentClient;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.initClients();
	}
	
	public ServerChat() {
		services = new ArrayList<ServiceChat> ();
		control = new ServerControl();
		currentClient = 0;
		control.appendText("Server started. Type \"help\" to show more commands. \n");
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
			 ServiceChat service = new ServiceChat(client, this, currentClient);
			 currentClient++;
			 addClientIntoServicesAndStartIt(service);
		 }
	}
	
	private void addClientIntoServicesAndStartIt(ServiceChat service) {
		services.add(service);
		Thread t = new Thread(service);
		t.start();
		control.appendText("Client " + service.getID() + " connected" + "\n");
	}
	
	/*
	 * Call each of the service and tell them to print
	 */
	public void printAll(String input, String name) {
		control.appendText(name + ": " + input + "\n");
		for (ServiceChat service: services) {
			service.print(input, name);
		}
	}
}
