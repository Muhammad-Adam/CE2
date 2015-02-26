import static org.junit.Assert.*;
import org.junit.Test;


public class TextBuddyTest {

	@Test
	public void testDecipherValidCommand() {
		
		Decipher decipherTest = new Decipher("add do homework by this wednesday");
		assertEquals("add", decipherTest.getCommandType());
		assertEquals("do homework by this wednesday", decipherTest.getDescription());
		assertTrue(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("add  double spacing");
		assertEquals("add", decipherTest.getCommandType());
		assertEquals(" double spacing", decipherTest.getDescription());
		assertTrue(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("delete 3");
		assertEquals("delete", decipherTest.getCommandType());
		assertEquals("3", decipherTest.getDescription());
		assertTrue(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("clear");
		assertEquals("clear", decipherTest.getCommandType());
		assertTrue(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("display");
		assertEquals("display", decipherTest.getCommandType());
		assertTrue(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("exit");
		assertEquals("exit", decipherTest.getCommandType());
		assertTrue(decipherTest.isGoodCommand());
	}
	
	@Test
	public void testDecipherInvalidCommand() {
		
		Decipher decipherTest = new Decipher("hello 123");
		assertEquals("hello", decipherTest.getCommandType());
		assertEquals("123", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("add");
		assertEquals("add", decipherTest.getCommandType());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("delete");
		assertEquals("delete", decipherTest.getCommandType());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("delete abc");
		assertEquals("delete", decipherTest.getCommandType());
		assertEquals("abc", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("clear 1");
		assertEquals("clear", decipherTest.getCommandType());
		assertEquals("1", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("display 4c");
		assertEquals("display", decipherTest.getCommandType());
		assertEquals("4c", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("exit program");
		assertEquals("exit", decipherTest.getCommandType());
		assertEquals("program", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher(" add do homework");
		assertEquals("", decipherTest.getCommandType());
		assertEquals("add do homework", decipherTest.getDescription());
		assertFalse(decipherTest.isGoodCommand());
		
		decipherTest = new Decipher("Clear");
		assertEquals("Clear", decipherTest.getCommandType());
		assertFalse(decipherTest.isGoodCommand());
	}
	
	@Test
	public void testStorage() {
		
		Storage storageTest = new Storage("storageTest.txt");
		storageTest.addLine("do homework by Wed");
		assertEquals("1. do homework by Wed", storageTest.printLines());
		
		storageTest.addLine("complete project today");
		storageTest.addLine("3ldh5 sjk5h2");
		assertEquals("1. do homework by Wed\n2. complete project today\n3. 3ldh5 sjk5h2", storageTest.printLines());
		assertEquals("", storageTest.deleteLine("100"));
		assertEquals("do homework by Wed", storageTest.deleteLine("1"));
		assertEquals("3ldh5 sjk5h2", storageTest.deleteLine("2"));
		assertEquals("1. complete project today", storageTest.printLines());
		
		storageTest.clearLines();
		assertEquals("", storageTest.printLines());
		assertEquals("", storageTest.deleteLine("1"));
		assertEquals("", storageTest.deleteLine("10"));
	}
	
	@Test
	public void testLogic() {
		
		Logic logicTest = new Logic("logicTest.txt");
		assertEquals("added to logicTest.txt: \"do homework by Wed\"", logicTest.execute("add", "do homework by Wed"));
		assertEquals("added to logicTest.txt: \"complete project\"", logicTest.execute("add", "complete project"));
		assertEquals("deleted from logicTest.txt: \"do homework by Wed\"", logicTest.execute("delete", "1"));
		assertEquals("Line number 2 does not exist", logicTest.execute("delete", "2"));
		assertEquals("deleted from logicTest.txt: \"complete project\"", logicTest.execute("delete", "1"));
		
		assertEquals("all content deleted from logicTest.txt", logicTest.execute("clear"));
		assertEquals("logicTest.txt is empty", logicTest.execute("display"));
		assertEquals("added to logicTest.txt: \"do homework by Wed\"", logicTest.execute("add", "do homework by Wed"));
		assertEquals("added to logicTest.txt: \"complete project\"", logicTest.execute("add", "complete project"));
		assertEquals("added to logicTest.txt: \"WamBaDada 2356\"", logicTest.execute("add", "WamBaDada 2356"));
		assertEquals("1. do homework by Wed\n2. complete project\n3. WamBaDada 2356", logicTest.execute("display"));
		
		assertEquals("all content deleted from logicTest.txt", logicTest.execute("clear"));
		assertEquals("Goodbye!", logicTest.execute("exit"));
	}
	
	@Test
	public void testOrganiserInvalidCommand() {
		
		Organiser organiserTest = new Organiser("organiserTest.txt");
		assertEquals("Welcome to TextBuddy. organiserTest.txt is ready for use", organiserTest.showWelcomeMessage());
		assertEquals("command: ", organiserTest.promptForCommand());
		assertEquals("Unknown command format", organiserTest.processCommand("add"));
		assertEquals("Unknown command format", organiserTest.processCommand("delete a"));
		assertEquals("Unknown command format", organiserTest.processCommand(" add text"));
		assertEquals("Unknown command format", organiserTest.processCommand("display 2"));
		assertEquals("Unknown command format", organiserTest.processCommand("exit program"));
		assertEquals("Unknown command format", organiserTest.processCommand("Clear"));
		assertEquals("Unknown command format", organiserTest.processCommand("123 add5"));
	}
	
	@Test
	public void testOrganiserValidCommand() {
		
		Organiser organiserTest = new Organiser("organiserTest.txt");
		assertEquals("Welcome to TextBuddy. organiserTest.txt is ready for use", organiserTest.showWelcomeMessage());
		assertEquals("command: ", organiserTest.promptForCommand());
		assertEquals("added to organiserTest.txt: \"do homework by Wed\"", organiserTest.processCommand("add do homework by Wed"));
		assertEquals("added to organiserTest.txt: \"QwErTy\"", organiserTest.processCommand("add QwErTy"));
		assertEquals("1. do homework by Wed\n2. QwErTy", organiserTest.processCommand("display"));
		assertEquals("deleted from organiserTest.txt: \"QwErTy\"", organiserTest.processCommand("delete 2"));
		assertEquals("Line number 2 does not exist", organiserTest.processCommand("delete 2"));
		assertEquals("deleted from organiserTest.txt: \"do homework by Wed\"", organiserTest.processCommand("delete 1"));
		assertEquals("organiserTest.txt is empty", organiserTest.processCommand("display"));
		
		assertEquals("added to organiserTest.txt: \"a\"", organiserTest.processCommand("add a"));
		assertEquals("added to organiserTest.txt: \"b\"", organiserTest.processCommand("add b"));
		assertEquals("added to organiserTest.txt: \"c\"", organiserTest.processCommand("add c"));
		assertEquals("1. a\n2. b\n3. c", organiserTest.processCommand("display"));
		assertEquals("all content deleted from organiserTest.txt", organiserTest.processCommand("clear"));
		assertEquals("organiserTest.txt is empty", organiserTest.processCommand("display"));
	}
	
	@Test
	public void testSort() {
		
		Storage storageTest = new Storage("sortTest.txt");
		storageTest.clearLines();
		storageTest.addLine("pear");
		storageTest.addLine("apple");
		storageTest.addLine("grapes");
		storageTest.addLine("apricot");
		storageTest.addLine("durian");
		storageTest.sortContent();
		assertEquals("1. apple\n2. apricot\n3. durian\n4. grapes\n5. pear", storageTest.printLines());
	}

}
