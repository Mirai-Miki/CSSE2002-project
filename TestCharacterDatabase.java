package assignment2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the {@link CharacterDatabase} class.
 * 
 * @author Michael Bossner
 * @version 24/09/2017
 *
 */
public class TestCharacterDatabase {
	/** Instances of database to test */
	private CharacterDatabase database1;
	@SuppressWarnings("unused")
	private CharacterDatabase database2;
	/** Instances of characters to test */
	private SuperCharacter super1;
	private SuperCharacter super2;
	private SuperCharacter super3;
	private Character char1;
	private Character char2;
	private Character char3;
	private Character char4;
	
	/**
     * This method is run by JUnit before each test.
     */
	@Before
	public void setUp() {
		try {
			// initialize database before each test.
			database1 = new CharacterDatabase("database.dat");
		
			super1 = new SuperCharacter("super1", "Mage", 100);
			super2 = new SuperCharacter("super2", "Archer", 8);
			super3 = new SuperCharacter("super3", "Mage", 7);
		
			char1 = new Character("char1", "Merchant");
			char2 = new Character("char2", "Dancer");
			char3 = new Character("char3", "Fence");
			// Char4 uses same name as char3 for duplicate test.
			char4 = new Character("char3", "Dancer");
		
			super1.addTrait("King");
			super2.addTrait("Quick Step");
			super3.addTrait("Frail");
			char1.addTrait("Barter");
			char2.addTrait("Sure Footed");
			char3.addTrait("Barter");
		
			super1.addSuperPower("Omnipotence");
			super2.addSuperPower("Ice Arrow");
			super3.addSuperPower("Inferno");
			// adds Characters to database
			database1.add(super1);
			database1.add(super3);
			database1.add(char2);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests that the database cannot be created with a null filename.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testCharacterDatabaseNull() {
		try {
			database2 = new CharacterDatabase(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests that the database cannot be created with an empty filename.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testCharacterDatabaseEmpty() {
		try {
			database2 = new CharacterDatabase("");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Test Search method
	 */
	@Test
	public void testSearch() {
		
		try {
			// Tests the search finds the characters are in the Database
			assertEquals(super1, database1.search("super1"));
			assertEquals(super3, database1.search("super3"));
			assertEquals(char2, database1.search("char2"));						
			// Tests that the copy returned is a deep clone
			assertEquals(super1.toString(), database1.search("super1").toString());
			Character clone1 = database1.search("super1");
			clone1.setName("Clone1");
			assertNotEquals(super1, clone1);
			// Tests the search does not find Characters that are not in the Database
			assertEquals(null, database1.search("super2"));
			assertEquals(null, database1.search("char1"));
			assertEquals(null, database1.search("char3"));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests if searching for null throws an IllegalArgumentException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSearchNull() {
		try {
			database1.search(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests if searching with an empty name throws an IllegalArgumentException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSearchEmpty() {
		try {
			database1.search("");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		
		try {
			// checks the characters are not already in the database
			assertEquals(null, database1.search("super2"));
			assertEquals(null, database1.search("char1"));
			assertEquals(null, database1.search("char3"));
			// adds characters to the database
			database1.add(char1);
			database1.add(char3);
			database1.add(super2);
			// checks that the characters are now in the database
			assertEquals(super2, database1.search("super2"));
			assertEquals(char1, database1.search("char1"));
			assertEquals(char3, database1.search("char3"));
			// tests that a duplicate character is not added to the database
			database1.add(char4);
			database1.remove(char3);
			assertNotEquals(char4, database1.search("char3"));
			database1.add(char4);
			assertEquals(char4, database1.search("char3"));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * Tests that adding null will throw the NullPointerException
	 */
	@Test (expected = NullPointerException.class)
	public void testAddNull() {
		try {
			database1.add(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tests the Remove method
	 */
	@Test
	public void testRemove() {
		
		try {
			// Checks the characters are already in the Database
			assertEquals(super1, database1.search("super1"));
			assertEquals(super3, database1.search("super3"));
			assertEquals(char2, database1.search("char2"));
			// Removes the characters from the database
			database1.remove(super1);
			database1.remove(super3);
			database1.remove(char2);
			// Checks the characters have been removed from the database
			assertEquals(null, database1.search("super1"));
			assertEquals(null, database1.search("super3"));
			assertEquals(null, database1.search("char2"));
		}
		catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
