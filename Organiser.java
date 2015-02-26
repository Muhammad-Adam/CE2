/**
 * This class only serves the purpose of helping the main class to execute some methods
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
	
	public void createOrOpenFile() {
		Storage myStorage = new Storage(fileName);
		myStorage.createOrOpenTextFile();
	}
	
	public String promptForCommand() {
		return MESSAGE_COMMAND;
	}
	
	// Method will check what type of command before calling other appropriate methods from other class
	public String processCommand(String command) { 
		Decipher myDecipher = new Decipher(command);
		if (myDecipher.isGoodCommand()) {
			Logic myLogic = new Logic(fileName);
			String commandType = myDecipher.getCommandType();
			if (commandType.equals("add") || commandType.equals("delete")) {
				String description = myDecipher.getDescription();
				return myLogic.execute(commandType, description);
			} else {
				return myLogic.execute(commandType);
			}
		} else {
			return MESSAGE_UNKNOWN_COMMAND;
		}
	}
}