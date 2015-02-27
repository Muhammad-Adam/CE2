package CE2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** This class will do all the creating, editing, searching and format the content of the text file
 * 	Note that only valid commands will use this class
 */

public class Storage {
	
	private String fileName;
	
	public Storage(String fileName) {
		this.fileName = fileName;
	}
	
	public PrintWriter openTextFileOveride(boolean overide) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(fileName, !overide));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	public void closeTextFile(PrintWriter writer) {
		writer.close();
	}
	
	// Add a line to the text file
	public void addLine(String description) {
		PrintWriter writer = openTextFileOveride(false);
		writer.println(description);
		closeTextFile(writer);
	}
	
	// Delete a line from the text file
	public String deleteLine(String lineNumber) {
		ArrayList<String> allLines = storeInArrayList();
		PrintWriter writer = openTextFileOveride(true);		
		String lineDeleted = deleteLineHelper(lineNumber, allLines, writer);
		closeTextFile(writer);
		return lineDeleted;
	}
	
	// Format the contents of the text file so that it prints line by line
	public String printLines() {
		ArrayList<String> allLines = storeInArrayList();
		return printLinesHelper(allLines, "", 1);
	}
	
	// Clear all the contents of the text file
	public void clearLines() {
		PrintWriter writer = createFreshTextFile();
		closeTextFile(writer);
	}
	
	// Sort alphabetically the contents of the text file
	public void sortContent() {
		ArrayList<String> allLines = storeInArrayList();
		Collections.sort(allLines);
		clearLines();
		for (String line: allLines) {
			addLine(line);
		}
	}

	// Store all lines that contain the keyword into ArrayList before passing to other method
	public String searchFor(String keyword) {
		ArrayList<String> allLines = storeInArrayList();
		ArrayList<String> resultLines = new ArrayList<String>();
		for (int index = 0; index < allLines.size(); index++) {
			String[] cutUpLine = allLines.get(index).split(" ");
			if (searchForHelper(cutUpLine, keyword)) {
				resultLines.add(allLines.get(index));
			}
		}
		return printLinesHelper(resultLines, "", 1);
	}
	
	// Helper function for the searchFor method
	private boolean searchForHelper(String[] line, String keyword) {
		for (String word: line) {
			if (word.equals(keyword)) {
				return true;
			}
		}
		return false;
	}

	// Helper function for the deleteLine method
	private String deleteLineHelper(String lineNumber, ArrayList<String> allLines, PrintWriter writer) {
		int counter = 1;
		int index = Integer.parseInt(lineNumber);
		String lineDeleted = "";
		for (String line: allLines) {
			if (counter != index) {
				writer.println(line);
			} else {
				lineDeleted = line;
			}
			counter++;
		}
		return lineDeleted;
	}

	// Helper function for the printLines method
	private String printLinesHelper(ArrayList<String> allLines, String displayString, int bulletpoint) {
		for (int index = 0; index < allLines.size(); index++) {
			displayString += bulletpoint + ". " + allLines.get(index);
			if (index != allLines.size()-1) {
				displayString += "\n";
				bulletpoint++;
			}
		}
		return displayString;
	}

	// Store the contents of the text file to an ArrayList
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
	
	// Helper function for clearLines method
	private PrintWriter createFreshTextFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}
}
