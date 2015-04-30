package data;
/*
 * This class stores a lot of keywords and data.
 */
public class SpecialCommands {
	// special commands to do special jobs such as kick, log, and get the number of users
	public static String getName = "getName";
	public static String help = "help";
	public static String log = "log";
	public static String kick = "kick";
	public static String users = "users";
	public final static String KEYWORD = "MINHHOANGQUANGNGUYEN"; // a secret password for the server and the client to talk with each other
	private static String logStore = "";
	
	public static void appendLog(String text) {
		logStore = logStore + text + "\n";
	}
	
	public static String getLog() {
		return logStore;
	}

}
