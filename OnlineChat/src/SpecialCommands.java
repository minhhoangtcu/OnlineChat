/*
 * This class stores a lot of keywords and data.
 */
public class SpecialCommands {
	
	public static String getName = "getName";
	public static String help = "help";
	public static String log = "log";
	public static String kick = "kick";
	public final static String KEYWORD = "MINHHOANGQUANGNGUYEN"; // a secret password for the server and the client to talk with each other
	private static String logStore = "";
	
	public static void appendLog(String text) {
		logStore = logStore + "\n" + text;
	}
	
	public static String getLog() {
		return logStore;
	}

}
