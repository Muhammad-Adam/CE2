package CE2;

/**
 * This class only serves the purpose of helping the main class to execute methods
 * when the main class is looping.
 */

public class Organiser {
	
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command format";
	
	private String fileName;
	
	public Organiser(String fileName) {
		this.fileName = fileName;
	}
	
	public String showWelcomeMessage() {
		return String.format(MESSAGE_WELCOME, fileName);
	}
	
	// Create a Storage object to create a text file if it does not exist
	public void createOrOpenFile() {
		Storage myStorage = new Storage(fileName);
		myStorage.closeTextFile(myStorage.openTextFileOveride(false));
	}
	
	public String promptForCommand() {
		return MESSAGE_COMMAND;
	}
	
	// Method creates Decipher object and check if valid before calling other method
	public String processCommand(String command) { 
		Decipher myDecipher = new Decipher(command);
		if (myDecipher.isGoodCommand()) {
			return divertToMethods(command, myDecipher);
		} else {
			return MESSAGE_UNKNOWN_COMMAND;
		}
	}
	
	// Create a Logic object and pass is to appropriate method depending on command type
	private String divertToMethods(String command, Decipher myDecipher) {
		Logic myLogic = new Logic(fileName);
		String commandType = myDecipher.getCommandType();
		if (commandGroupOne(commandType)) {
			String description = myDecipher.getDescription();
			return myLogic.execute(commandType, description);
		} else {
			return myLogic.execute(commandType);
		}
	}
	
	private boolean commandGroupOne(String commandType) {
		return commandType.equals("add") || commandType.equals("delete") || 
			   commandType.equals("search");
	}
}