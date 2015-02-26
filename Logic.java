/** This class handles all the valid commands entered
 * 	This class will not handle invalid commands at all
 */

public class Logic {
	
	private static final String MESSAGE_ADDED = "added to %s: \"%s\"";
	private static final String MESSAGE_DELETED = "deleted from %s: \"%s\"";
	private static final String MESSAGE_NO_EXIST = "Line number %s does not exist";
	private static final String MESSAGE_EMPTY = "%s is empty";
	private static final String MESSAGE_CLEARED = "all content deleted from %s";
	private static final String MESSAGE_GOODBYE = "Goodbye!";

	private String fileName;
	private Storage myStorage;
	
	public Logic(String fileName) {
		this.fileName = fileName;
		myStorage = new Storage(fileName);
	}
	
	/*
	 * Method will call appropriate methods depending on the command type
	 * The only command types that use this method are "add" and "delete"
	 */
	public String execute(String commandType, String description) {
		if (commandType.equals("add")) {
			return addLineToFile(description);
		} else {
			return deleteLineInFile(description);
		}
	}
	
	/*
	 * Method will call appropriate methods depending on the command type
	 * The only command types that use this method are "display", "clear" and "exit"
	 */
	public String execute(String command) {
		if (command.equals("display")) {
			return printLinesOnScreen();
		} else if (command.equals("clear")) {
			return clearFile();
		} else {
			return printGoodbye();
		}
	}
	
	// Pass the argument to Storage object to add it
	private String addLineToFile(String info) {
		myStorage.addLine(info);
		return String.format(MESSAGE_ADDED, fileName, info);
	}
	
	// Pass the argument to Storage object to let it delete a line, if exist
	private String deleteLineInFile(String lineNumber) {
		String lineDeleted = myStorage.deleteLine(lineNumber);
		if (lineDeleted.equals("")) {
			return String.format(MESSAGE_NO_EXIST, lineNumber);
		} else {
			return String.format(MESSAGE_DELETED, fileName, lineDeleted);
		}
	}
	
	/*
	 * Call a method from Storage object to format the contents
	 * so that it will print line by line, if there are contents
	 */
	private String printLinesOnScreen() {
		String output = myStorage.printLines();
		if (output.equals("")) {
			return String.format(MESSAGE_EMPTY, fileName);
		} else {
			return output;
		}
	}
	
	// Call a method from Storage object to clear its contents
	private String clearFile() {
		myStorage.clearLines();
		return String.format(MESSAGE_CLEARED, fileName);
		
	}
	
	private String printGoodbye() {
		return MESSAGE_GOODBYE;
	}
}
