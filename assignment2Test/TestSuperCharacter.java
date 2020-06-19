package assignment2Test;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment2.*;

public class TestSuperCharacter {
	
	@Test
	public void testSuperCharacter() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		SuperCharacter tester1 = new SuperCharacter("Name1", "Description1", 
				15);
		
		assertTrue(tester.toString().contains("Name: Description"
				+ "\nTraits: []"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: []"));
		
		assertTrue(tester1.toString().contains("Name1: Description1"
				+ "\nTraits: []"
				+ "\nPower Ranking: Unrankable"
				+ "\nSuper Powers: []"));
	}
	
	@Test
	public void testSetName() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertTrue(tester.toString().contains("Name: Description"
				+ "\nTraits: []"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: []"));
		
		tester.setName("New Name");
		
		assertTrue(tester.toString().contains("New Name: Description"
				+ "\nTraits: []"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: []"));
	}
	
	@SuppressWarnings("unused")
	@Test (expected = NullPointerException.class)
	public void testSetNameNull() {
		SuperCharacter tester = new SuperCharacter(null, "Description", 7);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameEmpty() {
		SuperCharacter tester = new SuperCharacter("", "Description", 7);
	}
	
	@Test
	public void testGetName() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		SuperCharacter tester1 = new SuperCharacter("N", "Description", 7);
		
		assertEquals("Name", tester.getName());
		assertEquals("N", tester1.getName());
	}
	
	@Test
	public void testSetDescription() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertTrue(tester.toString().contains("Name: Description"
				+ "\nTraits: []"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: []"));
		
		tester.setDescription("New Description");
		
		assertTrue(tester.toString().contains("Name: New Description"
				+ "\nTraits: []"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: []"));
	}
	
	@SuppressWarnings("unused")
	@Test (expected = NullPointerException.class)
	public void testSetDescriptionNull() {
		SuperCharacter tester = new SuperCharacter("Name", null, 7);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void testSetDescriptionEmpty() {
		SuperCharacter tester = new SuperCharacter("Name", "", 7);
	}
	
	@Test
	public void testGetDescription() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		SuperCharacter tester1 = new SuperCharacter("N", "D", 7);
		
		assertEquals("Description", tester.getDescription());
		assertEquals("D", tester1.getDescription());
	}

	@Test
	public void testSetPowerRanking() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);		
		
		assertTrue(tester.toString().contains("Power Ranking: 7"));
		tester.setPowerRanking(5);
		assertTrue(tester.toString().contains("Power Ranking: 5"));
		tester.setPowerRanking(25);
		assertTrue(tester.toString().contains("Power Ranking: Unrankable"));
	}
	
	@Test(expected = IllegalPowerRankingException.class)
	public void testSetPowerRankingInvalidInt() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);		
		tester.setPowerRanking(-7);
	}
	
	@Test
	public void testGetPowerRanking() {
		SuperCharacter test = new SuperCharacter("Name", "Description", 7);
		SuperCharacter test1 = new SuperCharacter("Name", "Description", 15);
		
		assertEquals(7, test.getPowerRanking());
		assertEquals(SuperCharacter.UNRANKABLE, test1.getPowerRanking());
	}
	
	@Test
	public void testAddTrait() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertFalse(tester.toString().contains("firstTest"));
		assertFalse(tester.toString().contains("secondTest"));
		
		tester.addTrait("firstTest");		
		assertTrue(tester.toString().contains("firstTest"));		
		tester.addTrait("secondTest");
		
		
		assertTrue(tester.toString().contains("secondTest"));
		assertTrue(tester.toString().contains("firstTest"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddTraitNull() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		tester.addTrait(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddTraitEmpty() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		tester.addTrait("");
	}
	
	@Test
	public void testRemoveTrait() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertFalse(tester.toString().contains("firstTest"));
		tester.addTrait("firstTest");
		tester.addTrait("secondTest");
		tester.addTrait("thirdTest");	
		
		assertTrue(tester.toString().contains("firstTest"));
		tester.removeTrait("firstTest");
		assertFalse(tester.toString().contains("firstTest"));
		assertTrue(tester.toString().contains("secondTest"));
		tester.removeTrait("secondTest");
		assertFalse(tester.toString().contains("secondTest"));
	}
	
	@Test
	public void testAddSuperPower() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertFalse(tester.toString().contains("firstTest"));
		assertFalse(tester.toString().contains("secondTest"));
		
		tester.addSuperPower("firstTest");
		assertTrue(tester.toString().contains("firstTest"));
		tester.addSuperPower("secondTest");
		
		assertTrue(tester.toString().contains("firstTest"));		
		assertTrue(tester.toString().contains("secondTest"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddSuperPowerNull() {
		SuperCharacter tester = new SuperCharacter("name", "Description", 7);
		tester.addSuperPower(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSuperPowerEmpty() {
		SuperCharacter tester = new SuperCharacter("name", "Description", 7);
		tester.addSuperPower("");
	}
	
	@Test
	public void testRemoveSuperPower() {
		SuperCharacter tester = new SuperCharacter("Name", "Description", 7);
		
		assertFalse(tester.toString().contains("firstTest"));
		tester.addSuperPower("firstTest");
		tester.addSuperPower("secondTest");
		tester.addSuperPower("thirdTest");
		
		assertTrue(tester.toString().contains("firstTest"));
		tester.removeSuperPower("firstTest");
		assertFalse(tester.toString().contains("firstTest"));
		assertTrue(tester.toString().contains("secondTest"));
		tester.removeSuperPower("secondTest");
		assertFalse(tester.toString().contains("secondTest"));		
	}
	
	@Test
	public void testEquals() {
		SuperCharacter test1 = new SuperCharacter("Name", "Description", 7);
		SuperCharacter test2 = new SuperCharacter("Name", "Description", 15);
		assertTrue(test1.equals(test2));
		
		SuperCharacter test3 = new SuperCharacter("Name1", "Description", 7);
		SuperCharacter test4 = new SuperCharacter("Name", "1Description", 15);
		assertFalse(test3.equals(test4));
		//assertFalse(test1.equals(test4));
	}
	
	@Test 
	public void testEqualsNull() {
		SuperCharacter test1 = new SuperCharacter("Name", "Description", 7);
		assertFalse(test1.equals(null));
	}
	
	@Test
	public void testToString() {
		SuperCharacter test1 = new SuperCharacter("Name", "Description", 7);
		test1.addSuperPower("test");
		test1.addSuperPower("test1");
		test1.addTrait("test");
		
		assertEquals("Name: Description"
				+ "\nTraits: [test]"
				+ "\nPower Ranking: 7"
				+ "\nSuper Powers: [test, test1]", test1.toString());
		
		test1.setPowerRanking(15);
		assertEquals("Name: Description"
				+ "\nTraits: [test]"
				+ "\nPower Ranking: Unrankable"
				+ "\nSuper Powers: [test, test1]", test1.toString());
		
	}
}
