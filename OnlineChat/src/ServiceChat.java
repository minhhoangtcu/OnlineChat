import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServiceChat implements Runnable {
	private Socket client;
	private ServerChat server;
	private Scanner in;
	private PrintWriter out;
	private int clientID;
	
	public ServiceChat(Socket client, ServerChat server, int clientID) {
		this.client = client;
		this.server = server;
		this.clientID = clientID;
	}

	public void run() {
		try {
			in  = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			doService();
			client.close();
			removeFromServices();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeFromServices() {
		int index = server.services.indexOf(this);
		server.services.remove(index);
	}
	
	public void print(String input, String name) {
		out.println(name + ": " + input);
		out.flush();
	}
	
	public void print(String input) {
		out.println(input);
		out.flush();
	}
	
	public String getName() {
		out.println(SpecialCommands.KEYWORD + SpecialCommands.getName);
		out.flush();
		String name = in.nextLine();
		return name;
	}
	
	public int getID() {
		return clientID;
	}

	// TODO: MAKE THIS A THREAD
	private void doService() {
		while (true) {
			String input;
			try{
				input = in.nextLine();
			} catch (NoSuchElementException e) {
				break;
			}
			server.printAll(input, this);
		}
	}
}

