/**************************************************************************/
/*  Program Name:    Lab# 4                                               */
/*                                                                        */
/*  Student Name:    Quang Nguyen,Minh Hoang, Kiet Nguyen, Le Bui         */
/*  Semester:        Spring,  2015                                        */
/*  Class & Section: CoSc 20203                                           */
/*  Instructor:      Dr. Rinewalt Dick                                    */
/*  Due Date:        April 29, 2015                                       */
/*                                                                        */
/*  Program Overview:                                                     */
/*      This applet use socket and multi-threading to create a server,    */
/*receive the chat content from each client connected and create a thread */
/*to handle new client chat's customer.
 */
/* */
/*                                                                        */
/*                                                                        */
/*  Program Limitations:                                                  */
/*      (1) No private chat function available yet.                       */
/*      (2) Cannot handle video, emoji, image files yet                   */
/*                                                                        */
/*  Programme Strength:                                                   */
/* 		(1) It can carry out special function such as kick, get the number*/
/*of users, get the log chat content                                      */
/*      (2) It has a GUI                                                  */
/*                                                                        */
/*  Significant Program Variables:                                        */
/*     socket, thread, ServerChat.                                        */ 

/**************************************************************************/

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
	private int numberOfClients;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.initClients();
	}
	
	public ServerChat() {
		services = new ArrayList<ServiceChat> ();
		control = new ServerControl(this);
		numberOfClients = 0;
		control.appendText("Server started. Type \"" + SpecialCommands.help + "\" to show more commands. \n");
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO properly handle the error
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
			 ServiceChat service = new ServiceChat(client, this, numberOfClients);
			 numberOfClients++;
			 addClientIntoServicesAndStartIt(service);
		 }
	}
	
	private void addClientIntoServicesAndStartIt(ServiceChat service) {
		services.add(service);
		Thread t = new Thread(service);
		t.start();
		control.appendText("Client ID:"+ service.getID() + " connected");
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
		for (ServiceChat service: services)
			if (clientID == service.getID()) return true;
		return false;
	}
	
	public ServiceChat getServiceWithThisID(int clientID) {
		for (ServiceChat service: services)
			if (clientID == service.getID())
				return service;
		return null; // TODO figure out a way to not return null
	}
	
	public String getNameOfThisServiceWithThisID(int clientID) {
		for (ServiceChat service: services)
			if (clientID == service.getID())
				return service.getName();
		return null; // TODO figure out a way to not return null
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
