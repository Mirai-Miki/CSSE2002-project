package assignment2;

import java.util.HashSet;

/**
 * A very simple class representing a Set of Characters.
 * 
 * @author 	Michael Bossner
 * @version 1.0
 */
public class Team {
	private String team_name;
	private HashSet<Character> team = new HashSet<Character>();
	
	/**
	 * Constructor
	 * 
	 * @param name 						The name of the team of super characters
	 * 									as a non-empty string
	 * @throws IllegalArgumentException The name is empty
	 * @throws NullPointerException		The name is Null
	 */
	public Team(String name) {
		if (name == null) {
			throw new NullPointerException("Name cannot be Null");
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		team_name = name;
	}	
	
	/**
	 * Adds a character to a set of character. If the name and description 
	 * match another character already in the set the character will not be 
	 * added.
	 * The character to be added can not be Null.
	 * 
	 * @param name The character to be added to the team of characters
	 * @throws NullPointerException The character is null
	 */
	public void addTeamMember(Character name) {
		if (name == null) {
			throw new NullPointerException("Character can not be Null");
		}
		if (!compareTeam(name)) {
			team.add(name);
		}
	}
	
	/*
	 * Helper method that compares a character against a set of characters
	 * to see if there name and description match a character already in the 
	 * set and returns true if there is and false if there is not.
	 * 
	 * @param member The character to be compared against the members in the set 
	 */
	private boolean compareTeam(Character member) {
		for (Character individuel : team) {
			if (individuel.equals(member)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes a character object from the set if they are currently in the set.
	 * If they are not in the set nothing will happen.
	 * 
	 * @param name Name of character object to be removed from the set
	 */
	public void removeTeamMember(Character name) {
		team.remove(name);		
	}
	
	/**
	 * Adds the power ranking of all the super characters in a team and 
	 * returns the total power ranking for that team.
	 * If any super character has a power ranking of unrankable then the teams
	 * power ranking will also be unrankable.
	 * If there are no super characters in a team then power ranking will be 0.
	 * 
	 * @return returns the total power ranking for a team of characters
	 */
	public int powerRanking() {
		int rank = 0;		
		for (Character individual : team) {
			if (individual instanceof SuperCharacter) {
				if (((SuperCharacter) individual).getPowerRanking() == 
						SuperCharacter.UNRANKABLE){
					rank = SuperCharacter.UNRANKABLE;
					return rank;
				}
				else {rank += ((SuperCharacter) individual).getPowerRanking();}
			}
		}
		return rank;
	}
	
	/**
	 * Returns a string representation of a team of characters in the format of
	 * 
	 * "Team Name:" the name of the team
	 * "Team Members:" a list of the team members names
	 * "Team Power ranking:" the total power ranking of a team
	 * 
	 * @return A string representation of a team
	 */
	public String toString() {
		String rank;
		String members = "";
		
		// checks if the power rank of the team is unrankable
		if (powerRanking() == SuperCharacter.UNRANKABLE) {
			rank = "Unrankable";
		}
		else { rank = Integer.toString(powerRanking()); }
		
		// retrieves the names of all team members and adds them to a string
		for (Character individual : team) {
			members += (individual.getName()) + ", ";
		}
		if (members.length() != 0) {
		members = members.substring(0, members.length()-2);
		}
		
		return "Team Name: " + team_name + "\nTeam Members: " + members +
				"\nTeam Power Ranking: " + rank;
	}
}
