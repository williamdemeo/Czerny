package automaton;

import static org.junit.Assert.*;
//import junit.framework.TestCase;

import org.junit.Test;

public class AutomatonTest {

	private int[] f = {0, 1, 2, 3, 4};
	//private int[] g = {1, 1, 2, 3, 4};
	
	@Test
	public void Automaton() {
		Automaton testA = new Automaton("Czerny5");
		assertEquals("Czerny5", testA.getName());
	}
	
	@Test
	public void addLetter(){
		Automaton testA = new Automaton("Czerny5");
		assertEquals(0, testA.getLetters().size());
				
		try {
			assertTrue("New letter should have been added", testA.addLetter(5, f));
		}
		catch (Automaton.AlgebraSizeException e){
			e.printStackTrace();
		}
		assertEquals(f, testA.getLetterTable(0));

	}

}
