package assignment2;

import java.io.Serializable;
import java.util.HashSet;

/**
 * A generic class representing a (fictional) character.
 * 
 * @author	Michael Bossner
 * @version 24/09/2017
 */
public class Character implements Serializable {
	
	/** Default serial version ID */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String description;
	protected HashSet<String> character_traits = new HashSet<String>();
	
	/**
	 * Constructor
	 * 
	 * @param char_name			The name of the character as a non-empty string
	 * @param char_description	The description of the character as a 
	 * 							non-empty string
	*/
	public Character(String char_name, String char_description) {
		setName(char_name);
		setDescription(char_description);
	}
	
	/**
	 * Sets the name of the character.
	 * Name cannot be an empty string or Null.
	 * 
	 * @param setName					The name of the character as a 
	 * 									non-empty string
	 * @throws NullPointerException		The setName is Null
	 * @throws IllegalArgumentException The setName is an empty string
	 */
	public void setName(String setName) {
		if (setName == null) {
			throw new NullPointerException("Name cannot be Null");
		}
		if (setName.length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		name = setName;
	}
		
	/**
	 * Sets the description of the character.
	 * Description cannot be an empty string or Null.
	 * 
	 * @param setDecription				The description of the character as a 
	 * 									non-empty string
	 * @throws NullPointerException		The setDescription is Null
	 * @throws IllegalArgumentException The setDescription is an empty string
	 */
	public void setDescription(String setDescription) {
		if (setDescription == null) {
			throw new NullPointerException("Description cannot be Null");
		}
		if (setDescription.length() == 0) {
			throw new IllegalArgumentException("Description cannot be empty");
		}
		description = setDescription;
	}
	
	/**
	 * Returns the name of the character
	 * 
	 * @return The name of the character in a string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the description of the character
	 * 
	 * @return The description of the character in a string
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Adds a string representation of a trait to a set of traits for that
	 * character.
	 * The trait cannot be an empty string or Null.
	 * 
	 * @param trait						The trait of the character to be added 
	 * 									as a non-empty string
	 * @throws NullPointerException		The trait is Null
	 * @throws IllegalArgumentException The trait is an empty string
	 */
	public void addTrait(String trait) {
		if (trait == null) {
			throw new NullPointerException("Trait cannot be Null");
		}
		if (trait.length() == 0) {
			throw new IllegalArgumentException("Trait cannot be empty");
		}
		character_traits.add(trait);
	}
	
	/**
	 * Removes a trait from the set of traits for this character if it is 
	 * already in the set of traits otherwise nothing will be removed.
	 * 
	 * @param trait The trait to be removed from the set if it is there else
	 * 				nothing will be removed
	 */
	public void removeTrait(String trait) {
		character_traits.remove(trait);
	}
	
	/**
	 * Returns a string representation of a character in the format of
	 * 
	 * Name: Description
	 * "Traits": List of traits
	 * 
	 * @return A string representation of a character
	 */
	public String toString() {
		return name + ": " + description + "\nTraits: " + character_traits;
	}
	
	/*
	 * Old Equals Method
	 * 
	 * Compares 2 character objects and returns True if both characters are 
	 * the same or false if they are not. A character is the same if the 
	 * this.name == other.name && this.description == other.description.
	 * Object cannot be null.
	 * 
	 * @param object The other character to be compared
	 * @return		 True if both this.name==other.name && this.description ==
	 *				 other.description else False
	 * @throws NullPointerException The character is Null
	 *
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			throw new NullPointerException("object cannot be Null");
		}
		try {
			final Character other = (Character) object;
			return this.getName() == other.getName() && this.getDescription() 
					== other.getDescription();
		}
		catch (ClassCastException cce) {
	        return false;
	    }
	}*/
	
	/**
	 * @return True is the name of both character are the same
	 */
	public boolean equals(Object other) {
	    try {
	        Character c = (Character) other;
	        return name.equals(c.getName());
	    }
	    catch (ClassCastException | NullPointerException cce) {
	        return false;
	    }
	}
	
	/**
	 * @return a hashcode for name
	 */
	public int hashCode( ) {
	    return name.hashCode( );
	}
	
	/**
	 * Creates a clone of a character object
	 *
	 * @return a deep copy clone
	 */
	public Object clone() {
		Character clone = new Character(name, description);
		// !! Note: the clone() method of the traits object CANNOT be used here
		// traits are added individually to the clone
		for (String t : character_traits)
			clone.character_traits.add(t);
		return clone;
	}

}
