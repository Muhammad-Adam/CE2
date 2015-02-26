import java.util.Scanner;

/**
 * @author Muhammad Adam
 * Student ID: A0121813U

 * This class is used to create a text file where user can add, delete, clear and display text.
 * User can store as many lines of text as he or she wants.
 * Limited to commands: "add", "clear", "delete", "search", "sort", "display" and "exit". NO OTHER COMMANDS OR SHORTCUT AVAILABLE
 * Note that commands are also cAsE-sEnSiTiVe
 * If user were to add an exact copy of text, the class WOULD NOT ignore the command and just execute it.
 * The command format is given by the example interaction below:
 
Welcome to TextBuddy. mytextfile.txt is ready for use
command: add rehearse for oral presentation
added to mytextfile.txt: "rehearse for oral presentation"
command: display
1. rehearse for oral presentation
command: add remember to buy groceries for mother
added to mytextfile.txt: "remember to buy groceries for mother"
command: display
1. rehearse for oral presentation
2. remember to buy groceries for mother
command: delete 1
deleted from mytextfile.txt: "rehearse for oral presentation"
command: display
1. remember to buy groceries for mother
command: clear
all content deleted from mytextfile.txt
command: display
mytextfile.txt is empty
command: exit
Goodbye!

*/

public class TextBuddy {
	
	private static Scanner userInput = new Scanner(System.in);
	private static String command = "startup";
	
	// Loop an Organiser object and its methods until an "exit" command is given
	public static void main(String[] args) {
		Organiser myOrganiser = new Organiser(args[0]);
		myOrganiser.createOrOpenFile();  // --> Important line if the text file does not exist
		println(myOrganiser.showWelcomeMessage());
		while (!command.equals("exit")) {
			print(myOrganiser.promptForCommand());
			command = userInput.nextLine();
			println(myOrganiser.processCommand(command));
		}	
    }
	
	private static void println(String line) {
		System.out.println(line);
	}
	
	private static void print(String line) {
		System.out.print(line);
	}
}