package CE2;

/** This class will help to decode what kind of command entered and its relevant information
 * 	and whether or not a valid command is entered
 */

public class Decipher {
	
	private String[] stringArr;
	
	public Decipher(String command) {
		stringArr = command.split(" ", 2);
	}
	
	public String getCommandType() {
		return stringArr[0];
	}
	
	public String getDescription() {
		return stringArr[1];
	}
	
	// Verify the amount of parts to the command and then call the appropriate method
	public boolean isGoodCommand() {
		if (this.getCommandParts() == 2) {
			return isValidTwoPartsCommand();
		} else {
			return isValidOnePartCommand();
		}
	}
	
	private int getCommandParts() {
		return stringArr.length;
	}
	
	// Checks whether the command is valid one-part command
	private boolean isValidOnePartCommand() {
		return getCommandType().equals("display") || getCommandType().equals("clear") || 
			   getCommandType().equals("exit") || getCommandType().equals("sort");
	}
	
	// Checks whether the command is a valid two-parts command
	private boolean isValidTwoPartsCommand() {
		boolean isValid = true;
		if (getCommandType().equals("delete") || getCommandType().equals("add") || 
			getCommandType().equals("search")) {
			try {
				Integer.parseInt(this.getDescription());
			} catch (NumberFormatException e) {
				if (this.getCommandType().equals("delete")) {
					isValid = false;
				}
			}
			return isValid;
		} else {
			return false;
		}
	}
}
