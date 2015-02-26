import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** This class will do all the creating, editing and format the content of the text file
 * 	Note that only valid commands will use this class
 */

public class Storage {
	
	private String fileName;
	
	public Storage(String fileName) {
		this.fileName = fileName;
	}
	
	public void createOrOpenTextFile() {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Add a line to the text file
	public void addLine(String description) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			writer.println(description);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Delete a line from the txt file
	public String deleteLine(String lineNumber) {
		ArrayList<String> allLines = storeInArrayList();
		int counter = 1;
		int index = Integer.parseInt(lineNumber);
		String lineDeleted = "";
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, false));		
			for (String line: allLines) {
				if (counter != index) {
					writer.println(line);
				} else {
					lineDeleted = line;
				}
				counter++;
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineDeleted;
	}
	
	// Format the contents of the txt file so that it prints line by line
	public String printLines() {
		ArrayList<String> allLines = storeInArrayList();
		String displayString = "";
		int bulletpoint = 1;
		for(int index = 0; index < allLines.size(); index++) {
			if (index == allLines.size()-1) {
				displayString += bulletpoint + ". " + allLines.get(index); 
			} else {
				displayString += bulletpoint + ". " + allLines.get(index) + "\n";
				bulletpoint++;
			}
		}
		return displayString;
	}
	
	// Clear all the contents of the txt file
	public void clearLines() {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Store the contents of the txt to an ArrayList
	private ArrayList<String> storeInArrayList() {
		ArrayList<String> listOfText = new ArrayList<String>();
		File content = new File(fileName);
		try {
			Scanner textFile = new Scanner(content);
		
			while (textFile.hasNextLine()) {
				listOfText.add(textFile.nextLine());
			}
		
			textFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listOfText;
	}

	public void sortContent() {
		ArrayList<String> allLines = storeInArrayList();
		Collections.sort(allLines);
		clearLines();
		for (String line: allLines) {
			addLine(line);
		}
	}
}
