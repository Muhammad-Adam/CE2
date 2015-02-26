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

}
