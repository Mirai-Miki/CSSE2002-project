package assignment2Test;

import static org.junit.Assert.*;

import org.junit.Test;
import assignment2.*;
import assignment2.Character;

public class TestTeam {

	@Test
	public void testTeam() {
		Team teamTest = new Team("Name1");
		assertTrue(teamTest.toString().contains("Team Name: Name1"));
		assertFalse(teamTest.toString().contains("Team Name: Name2"));
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void testTeamEmptyName() {
		Team teamTest = new Team("");
	}
	
	@SuppressWarnings("unused")
	@Test (expected = NullPointerException.class)
	public void testTeamNullName() {
		Team teamTest = new Team(null);
	}
	
	@Test
	public void testAddTeamMember() {
		Team team = new Team("Name1");
		SuperCharacter test1 = new SuperCharacter("Name1", "Description", 7);
		SuperCharacter test2 = new SuperCharacter("Name1", "Description", 15);
		SuperCharacter test3 = new SuperCharacter("Name2", "Description2", 2);
		Character test4 = new Character("Name3", "Description3");
		
		team.addTeamMember(test1);
		assertTrue(team.toString().contains("Team Members: Name1"));
		team.addTeamMember(test2);
		assertFalse(team.toString().contains("Team Members: Name1, Name1"));
		team.addTeamMember(test3);
		assertTrue(team.toString().contains("Name2"));
		team.addTeamMember(test4);
		assertTrue(team.toString().contains("Name3"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddTeamMemberNull() {
		Team team = new Team("Name1");
		team.addTeamMember(null);
	}
	
	@Test 
	public void testRemoveTeamMember() {
		Team team = new Team("Team");
		SuperCharacter test1 = new SuperCharacter("Name1", "Description", 7);
		SuperCharacter test2 = new SuperCharacter("Name2", "Description", 15);
		SuperCharacter test3 = new SuperCharacter("Name3", "Description2", 2);
		Character test4 = new Character("Name4", "Description3");
		
		team.addTeamMember(test1);
		team.addTeamMember(test2);
		team.addTeamMember(test3);
		team.addTeamMember(test4);
		assertTrue(team.toString().contains("Name1"));
		assertTrue(team.toString().contains("Name4"));
		
		team.removeTeamMember(test1);
		assertFalse(team.toString().contains("Name1"));		
		team.removeTeamMember(test4);
		assertFalse(team.toString().contains("Name4"));
		team.removeTeamMember(test3);
		team.removeTeamMember(test2);
		assertFalse(team.toString().contains("Name3"));
		assertFalse(team.toString().contains("Name2"));
	}
	
	@Test
	public void testPowerRanking() {
		Team team = new Team("Team");
		SuperCharacter test1 = new SuperCharacter("Name1", "Description", 7);
		SuperCharacter test2 = new SuperCharacter("Name2", "Description", 15);
		SuperCharacter test3 = new SuperCharacter("Name3", "Description2", 2);
		Character test4 = new Character("Name4", "Description3");
		team.addTeamMember(test1);		
		assertEquals(7, team.powerRanking());
		team.addTeamMember(test4);
		assertEquals(7, team.powerRanking());
		team.addTeamMember(test3);
		assertEquals(9, team.powerRanking());
		team.addTeamMember(test2);
		assertEquals(SuperCharacter.UNRANKABLE, team.powerRanking());
	}
	
	@Test
	public void testToString() {
		Team team = new Team("Team");
		SuperCharacter test1 = new SuperCharacter("Name1", "Description", 7);
		team.addTeamMember(test1);
		assertEquals("Team Name: Team"
				+ "\nTeam Members: Name1"
				+ "\nTeam Power Ranking: 7", team.toString());
		SuperCharacter test2 = new SuperCharacter("Name2", "Description", 75);
		team.addTeamMember(test2);
		assertEquals("Team Name: Team"
				+ "\nTeam Members: Name1, Name2"
				+ "\nTeam Power Ranking: Unrankable", team.toString());
	}
}
