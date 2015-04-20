import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ServiceChat implements Runnable {
	private Socket client;
	private ServerChat server;
	private Scanner in;
	private PrintWriter out;
	private int clientNumber;
	
	public ServiceChat(Socket client, ServerChat server) {
		this.client = client;
		this.server = server;
		this.clientNumber = clientNumber;
	}

	public void run() {
		try {
			in  = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			doService();
			client.close();
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
			String input = in.nextLine();
			System.out.println(input);
			String name = in.nextLine();
			System.out.println("name: " + name);
			server.printAll(input, name);
		}
	}
}

