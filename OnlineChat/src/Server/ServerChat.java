/*
 * Program: Server
 * Authors: Minh Hoang, Quang Nguyen, Kiet Nguyen, Le Bui
 * 
 * Program Overview:
 * 		This is a server. It has an GUI!
 */

package Server;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import data.SpecialCommands;
import Server.chat.ServerControl;
import Server.chat.ServiceChat;

public class ServerChat {
	public ArrayList<ServiceChat> services;
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
		control = new ServerControl(this);
		currentClient = 0;
		control.appendText("Server started. Type \"" + SpecialCommands.help + "\" to show more commands. \n");
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
		control.appendText("Client " + service.getID() + " connected");
	}
	
	/*
	 * Call each of the service and tell them to print
	 */
	public void printAll(String input, ServiceChat sendingService) {
		//Show on server
		String text = sendingService.getName() + "(ID: " + sendingService.getID() + ")" + ": " + input;
		control.appendText(text);
		addToLog(text);
		
		//Call each service to print
		for (ServiceChat service: services) {
			service.print(input, sendingService.getName());
		}
	}
	
	public void printAll(String input) {
		control.appendText(input);
		addToLog("Console: " + input);
		
		for (ServiceChat service: services) {
			service.print(input);
		}
	}
	
	/*
	 * Check if the list of services to see if its client has the client ID or not 
	 */
	public boolean containClientID(int clientID) {
		for (ServiceChat service: services) {
			if (clientID == service.getID()) {
				return true;
			}
		}
		return false;
	}
	
	public ServiceChat getServiceWithThisID(int clientID) {
		for (ServiceChat service: services) {
			if (clientID == service.getID()) {
				return service;
			}
		}
		return null;
	}
	
	public String getNameOfThisServiceWithThisID(int clientID) {
		for (ServiceChat service: services) {
			if (clientID == service.getID()) {
				return service.getName();
			}
		}
		return null;
	}
	
	public void removeFromServices(ServiceChat service) {
		int index = services.indexOf(service);
		services.remove(index);
	}
	
	private void addToLog(String text) {
		String timeStamp = new Date().toString();
		SpecialCommands.appendLog(timeStamp + "\t" + text);
	}
}
