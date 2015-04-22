
public class SpecialCommands {
	
	public static String getName = "getName";
	public static String help = "help";
	public static String log = "log";
	private static String logStore = "";
	
	public static void appendLog(String text) {
		logStore = logStore + "\n" + text;
	}
	
	public static String getLog() {
		return logStore;
	}

}
