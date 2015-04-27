package Server.chat;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Server.ServerChat;

public class ServiceReadThread extends Thread {
	private ServerChat server;
	private ServiceChat service;
	private Scanner in;

	public ServiceReadThread(ServerChat server, ServiceChat service, Scanner in) {
		this.server = server;
		this.service = service;
		this.in = in;
	}
	
	public void run() {
		String s;
		try {
			while ((s = in.nextLine()) != null) {
				if (s.equals(""));
				else server.printAll(s, service);
			}
		} catch (NoSuchElementException | IllegalStateException e) {
			// TODO write something to handle the error. 
		}
	}

}
