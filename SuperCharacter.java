package assignment2;

import java.util.HashSet;

/**
 * A class representing a super-powered character.
 * 
 * @author	Michael Bossner
 * @version 24/09/2017
 */
public class SuperCharacter extends Character {
	
	/** Default serial version ID */
	private static final long serialVersionUID = 1L;
	/**
	 * UNRANKABLE is any power ranking over 10 and will be set to integer.MAX_VALUE
	 */
	public final static int UNRANKABLE = Integer.MAX_VALUE;	
	private int power_ranking;
	private HashSet<String> super_power = new HashSet<String>();

	/**
	 * Constructor
	 * 
	 * @param char_name 	  	The name of the character as a non-empty string
	 * @param char_description  The description of the character as a 
	 * 							non-empty string
	 * @param p_rank			Power Ranking for a super character which is any
	 * 							integer value > 0 
	 * @throws IllegalPowerRankingException The power rank is not > 0
	 */
	public SuperCharacter(String char_name, String char_description, 
			int p_rank) {
		super(char_name, char_description);
		setPowerRanking(p_rank);
	}
	
	/**
	 * The power ranking is an integer representation of the super characters 
	 * power level. A power ranking must be an integer value > 0 and any 
	 * value > 10 will be classed as Unrankable and will be set to -1.
	 * The power ranking must not be < 1.
	 * 
	 * @param p_rank an integer > 0 to represent the power ranking
	 * @throws IllegalPowerRankingException The power rank is not > 0
	 */
	public void setPowerRanking(int p_rank) {
		if (p_rank >= 1 && p_rank <= 10) {
			power_ranking = p_rank;
		}
		else if (p_rank > 10) {
			power_ranking = UNRANKABLE;
		}
		else {
			throw new IllegalPowerRankingException("Power Rank for " 
		+ getName() + " must be > 0."); 
		}
	}
	
	/**
	 * Returns an integer value between 1 and 10. A power ranking greater then
	 * 10 will return -1 as the power ranking is Unrankable.
	 * 
	 * @return The power ranking as an integer >= 1 && <= 10. A power ranking of 
	 * 		   -1 is Unrankable.
	 */
	public int getPowerRanking() {
		return power_ranking;
	}
	
	/**
	 * Adds a string representation of a super power to a set of super powers
	 * for that character.
	 * A super power must not be an empty string or Null.
	 * 
	 * @param s_power					The super power of the character 
	 * 									to be added as a non-empty string
	 * @throws NullPointerException		The super power is Null
	 * @throws IllegalArgumentException The super power is an empty string
	 */
	public void addSuperPower(String s_power) {
		if (s_power == null) {
			throw new NullPointerException("Super Power must not be Null");
		}
		if (s_power.length() == 0) {
			throw new IllegalArgumentException("Super Power must not be empty");
		}
		super_power.add(s_power);
	}
	
	/**
	 * Removes a super power from the set of super power for this character
	 * if that super power is already in the set.
	 * If the super power is not in the set nothing is removed.
	 * 
	 * @param s_power	The super power to be removed from the list if it is 
	 * 					there else nothing will be removed.
	 */
	public void removeSuperPower(String s_power) {
		super_power.remove(s_power);
	}
	
	/**
	 * Returns a string representation of a super character in the format of
	 * 
	 * Name: Description
	 * "Traits:" List of traits
	 * "Power ranking:" value of Power Ranking
	 * "Super Powers:" list of super powers
	 * 
	 * @return A string representation of a super character
	 */
	public String toString() {
		String ranking;
		if (getPowerRanking() == UNRANKABLE) {
			ranking = "Unrankable";
		}
		else { ranking = Integer.toString(getPowerRanking());	}
		
		return super.toString() + "\nPower Ranking: " + ranking
		+ "\nSuper Powers: " + super_power;
	}
	
	/**
	 * Clones a Super Character object
	 * 
	 * @return a deep copy clone
	 */
	@Override
	public Object clone() {
		SuperCharacter clone = new SuperCharacter(name, description,
				power_ranking);
		
		// Clone Traits
		for (String t : character_traits)
			clone.character_traits.add(t);
		
		// Clone Super Powers
		for (String s : super_power)
			clone.super_power.add(s);
		
		return clone;
	}
}
