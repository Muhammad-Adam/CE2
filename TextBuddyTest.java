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

}
