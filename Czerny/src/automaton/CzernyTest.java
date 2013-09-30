package automaton;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CzernyTest {

	@Test
	public void findSynchornizing() {
		
	}
	
	@Test
	public void kernel() {
		// -- TEST 1 of kernel method --
		int[] testf = {1, 1, 2, 4, 4, 4, 5}; 
		
		// kernel of f is |0,1|2|3,4,5|6|
		ArrayList<ArrayList<Integer>> trueKernel = new ArrayList<ArrayList<Integer>>();	
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(0,1)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(2)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(3,4,5)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(6)));
	
		ArrayList<ArrayList<Integer>> returnedKernel = new ArrayList<ArrayList<Integer>>();
		returnedKernel = Czerny.kernel(testf);
	
		System.out.println("True kernel: " + trueKernel.toString() + "\nRtrn kernel: " + returnedKernel.toString());
		org.junit.Assert.assertEquals("True kernel size not as expected", 4, trueKernel.size());
		org.junit.Assert.assertEquals("Returned kernel size not as expected", 4, returnedKernel.size());

		for (int j=0; j<trueKernel.size(); j++){
			org.junit.Assert.assertTrue("ArrayList elements not equal", trueKernel.get(j).equals(Czerny.kernel(testf).get(j)));
		}
	
		// -- TEST 2 of kernel method --
		testf = new int[] {2, 1, 2, 1, 4, 0, 0}; 
	
		// kernel of f is |5,6|1,3|0,2|4|
		trueKernel = new ArrayList<ArrayList<Integer>>();	
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(5,6)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(1,3)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(0,2)));
		trueKernel.add(new ArrayList<Integer>(Arrays.asList(4)));
	
		returnedKernel = new ArrayList<ArrayList<Integer>>();
		returnedKernel = Czerny.kernel(testf);
	
		System.out.println("True kernel: " + trueKernel.toString() + "\nRtrn kernel: " + returnedKernel.toString());
		org.junit.Assert.assertEquals("True kernel size not as expected", 4, trueKernel.size());
		org.junit.Assert.assertEquals("Returned kernel size not as expected", 4, returnedKernel.size());

		for (int j=0; j<trueKernel.size(); j++){
			org.junit.Assert.assertTrue("ArrayList elements not equal", trueKernel.get(j).equals(Czerny.kernel(testf).get(j)));
		}
	}
	
	@Test
	public void rank() {
		// -- TEST 1 of rank method --
		int[] testf = {1, 1, 2, 4, 4, 4, 5}; 
		org.junit.Assert.assertEquals("Unexpected rank", 4, Czerny.rank(testf));

		// -- TEST 2 of rank method --
		testf = new int[] {2, 1, 2, 1, 4, 0, 0}; 
		org.junit.Assert.assertEquals("Unexpected rank", 4, Czerny.rank(testf));
	}

}
