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
	
	// Checks whether the command is valid
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
	
	private boolean isValidOnePartCommand() {
		if (this.getCommandType().equals("display") || this.getCommandType().equals("clear") || this.getCommandType().equals("exit")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isValidTwoPartsCommand() {
		boolean isValid = true;
		if (this.getCommandType().equals("delete") || this.getCommandType().equals("add")) {
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
