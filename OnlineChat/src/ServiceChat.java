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
	
	public ServiceChat(Socket client, ServerChat server) {
		this.client = client;
		this.server = server;
	}

	public void run() {
		try {
			in  = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			doService();
			client.close();
			int index = server.services.indexOf(this);
			server.services.remove(index);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String input, String name) {
		out.println(name + ": " + input);
		out.flush();
	}

	private void doService() {
		while (true) {
			String input;
			try{
				input = in.nextLine();
			} catch (NoSuchElementException e) {
				break;
			}
			String name = in.nextLine();
			server.printAll(input, name);
		}
	}
}

