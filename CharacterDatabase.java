package assignment2;

import java.util.*;
import java.io.*;

/**
 * A Database of Characters.
 * <p>
 * The Database of Characters is a Set of Characters stored in a file.
 * The Database may not contain any duplicate Characters. A duplicate
 * Character is a Character that contains the same name as another Character.
 * 
 * @author Michael Bossner 
 * @version 24/09/2017
 *
 */
public class CharacterDatabase {
	/* A set containing the Characters */
	private HashSet<Character> database;
	/* File name for the database file */
	private String fileName;
	
	/*
	 * Invariants:
	 * 
	 * fileName != null || !fileName.isEmpty()
	 * database != null || !database.contains(null)
	 */
	
	/**
	 * Creates a new Character Database file with the given name.
	 * The filename cannot be null and cannot be empty.
	 * The database cannot be null and cannot contain null objects.
	 * 
	 * @param fn The name of the file to be created for the database.
	 * @throws IOException Error trying to create the file.
	 * @throws IllegalStateException the database does not meet the invariant.
	 */
	public CharacterDatabase(String fn) throws IOException {
		database = new HashSet<Character>();
		setFileName(fn);
		
		
		/* Creates a new file */
		try (ObjectOutputStream oos = 
			new ObjectOutputStream(new FileOutputStream(fileName))) {
			validateDatabase();
			oos.writeObject(database);
			oos.close();
		} catch (IOException e) {
			/* if it fails to create the file it will print a stack trace and
			 * try to create the file a second time if that fails it 
			 * throws IOExeption.
			 */
			e.printStackTrace();
			try (ObjectOutputStream oos = 
					new ObjectOutputStream(new FileOutputStream(fileName))) {
				validateDatabase();
				oos.writeObject(database);
				oos.close();
			}
		}
	}
	
	/**
	 * Validates the filename and sets it if it meets the invariant.
	 * 
	 * @param fn the name of the file. It cannot be null or empty.
	 * @throws IllegalArgumentException if the filename does not meet the invariant
	 */
	private void setFileName(String fn) {
		validateName(fn);
		fileName = fn;
	}
	
	/**
	 * Reads the set of Characters from the set stored in the file.
	 * @throws IOException The file with the database cannot be accessed.
	 */
	@SuppressWarnings("unchecked")
	private void load() throws IOException {
		try (ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(fileName))) {
			
			try { database = (HashSet<Character>) ois.readObject(); } 
			catch (ClassNotFoundException e) { e.printStackTrace(); }
			ois.close();
		}	
	}
	
	/**
	 * Saves the set of Characters to a file.
	 */
	private void save() {
		try (ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(database);
			oos.close();
		}
		catch (IOException e) {
			System.err.println("Save Failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a Character to a set of Characters and stores the set in a file.
	 * The Character must not be null.
	 * 
	 * @param n The character to be added to the database. Must not be null.
	 * @throws IOException The file with the database cannot be accessed.
	 * @throws NullPointerException the Character cannot be null.
	 * @throws IllegalStateException the database does not meet the invariant.
	 * 
	 */
	public void add(Character n) throws IOException {
		// check for null
		load();
		validateCharacter(n);
		if (duplicateCheck(n)) {			
			database.add(n);
			validateDatabase();
			save(); 
		}
	}
	
	/**
	 * Removes a Character from a set of Characters and stores the set in a file.
	 * 
	 * @param n The character to be removed from the database
	 * @throws IOException The file with the database cannot be accessed.
	 * @throws IllegalStateException the database does not meet the invariant.
	 */
	public void remove(Character n) throws IOException {
		load();
		database.remove(n);
		validateDatabase();
		save();		
	}
	
	/**
	 * Returns a deep clone of the searched character if it is in the database.
	 * If the character is not in the database return null.
	 * Character Name to be searched must not be null or an empty string.
	 * 
	 * @param n the name of the character to be searched for must not be null or an empty string
	 * @return a deep clone of the searched character if it exists, if not return null.
	 * @throws IOException The file with the database cannot be accessed.
	 * @throws IllegalArgumentException Name of Character does not meet invariant.
	 */
	public Character search(String n) throws IOException {
		validateName(n);
		load();
		for (Character character : database) {
			if (n.equals(character.getName())) {
				return (Character) character.clone();
			}
		}
		return null;
	}
	
	/**
	 * Checks if a character already exists in the database.
	 * A character is a duplicate if the name of the characters match.
	 * 
	 * @return false if a character already exists in the database. Returns
	 * 		   true if the character does not exist in the database. 
	 */
	private boolean duplicateCheck(Character n) {
		for (Character character : database) {
			if (n.equals(character)) {
				return false;
			}			
		}
		return true;
	}
	
	/**
	 * Checks that the character is not null.
	 * @param c Character to be added
	 */
	private void validateCharacter(Character c) {
		if (c == null) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Checks if the name of the file & character meets the invariants. 
	 * If it does not then IllegalArgumentException will be thrown.
	 * 
	 * The invariant is n != null || !n.isEmpty()
	 * 
	 * @throws IllegalArgumentException the fileNames does not meet the invariant.
	 */
	private void validateName(String n) {
		if (n == null || n.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Checks if the database meets the invariants. If it does not then
	 * IllegalStateException will be thrown.
	 * 
	 * The invariant is database != null || !database.contain(null)
	 * 
	 * @throws IllegalStateException the database does not meet the invariant.
	 */
	private void validateDatabase() {
		if (database == null || database.contains(null)) {
			throw new IllegalStateException();
		}
	}
}
