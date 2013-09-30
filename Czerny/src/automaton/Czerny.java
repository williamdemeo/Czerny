package automaton;

import org.uacalc.terms.Term;
import org.uacalc.terms.Variable;
import org.uacalc.ui.tm.ProgressReport;
import org.uacalc.util.IntArray;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import org.uacalc.alg.*;
import org.uacalc.alg.op.Operation;
import org.uacalc.alg.op.Operations;

import java.util.*;

/**
 * This class provides some functions for working with finite automata.
 * In particular, we want to test the Czerny conjecture for small n
 * using the FreeAlgebra class. 
 * 
 * @author williamdemeo@gmail.com
 * @date 2013.03.28
 *
 */
public class Czerny {
	 /**
	   * Test if a unary operation is constant
	   */
	public static final boolean isConstant(Operation op) {
		int c = op.intValueAt(0); 
		for (int i = 0; i < op.getSetSize(); i++) {
			if (op.intValueAt(i) != c) return false;
	    }
	    return true;
	}	
	 /**
	   * Test if an IntArray is constant
	   */
	public static final boolean isConstant(IntArray op) {
		int[] u = op.getArray();
		int c = u[0];
		for (int i = 0; i < u.length; i++) {
			if (u[i] != c) return false;
	    }
	    return true;
	}	

	
    //Find maximum (largest) value in array using loop  
	public static int getMaxValue(int[] numbers){  
	    int maxValue = numbers[0];  
	    for(int i=1;i<numbers.length;i++){  
	        if(numbers[i] > maxValue){  
	            maxValue = numbers[i];  
	        }  
	    }  
	    return maxValue;  
	}  
	  
	//Find minimum (lowest) value in array using loop  
	public static int getMinValue(int[] numbers){  
	    int minValue = numbers[0];  
	    for(int i=1;i<numbers.length;i++){  
	        if(numbers[i] < minValue){  
	            minValue = numbers[i];  
	        }  
	    }  
	    return minValue;  
	}  
	  
	static int[] toIntArray(List<Integer> list){
		int[] ret = new int[list.size()];
		for(int i = 0;i < ret.length;i++)
			ret[i] = list.get(i);
		return ret;
	}

	// store the i-th map of n^n in the h vector
	public static int[] numToFun( int i, int n ) {
		int k = i;
		int[] h = new int[n];
		for (int m=0; m<n; m++) {
			h[m]= k%n;
			k=(k-h[m])/n;
		}
		return h;
	}

	public static ArrayList<ArrayList<Integer>> kernel(int[] f){
		ArrayList<ArrayList<Integer>> kernel = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> block;
		int fmin = getMinValue(f);
		int fmax = getMaxValue(f);
		boolean flag;
		for (int k = fmin; k<=fmax; k++) {
			flag = false;
			block = new ArrayList<Integer>();
			for (int j = 0; j< f.length; j++) {
				if (f[j]==k){
					flag=true;
					block.add(j);
				}
			}
			if (flag) {
				kernel.add(block);
			}
		}
		return kernel;
	}
	
	// return the number of blocks in the kernel of f
	public static int rank(int[] f) {
		return kernel(f).size();
	}
	
	/**
	 * 
	 * @param args  a single int representing the cardinality of the automata   
	 */
	public static void main(String[] args) {
		//if (args.length == 0) {
		//  System.out.println("Error: usage command arg1 arg2 ...");
		//  return;
		//}
		//final String command = args[0];
		if (args.length == 0) {
			System.out.println("input size of automaton");
			return;
		}
		// Input is the cardinality of the automaton
		int n = Integer.parseInt(args[0]);
		int rnk;
		if (args.length == 2) { rnk = Integer.parseInt(args[1]); } else { rnk = n-1; }

		int nn = (int) java.lang.Math.pow(n,n);
		int czernyBound = (int) java.lang.Math.pow(n-1,2);
		
		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) arr[i] = i; 

		ICombinatoricsVector<Integer> initialVector = Factory.createVector(arr);

		Generator<Integer> generator = Factory.createPermutationGenerator(initialVector);
		
		ArrayList<Automaton> savedAutomata = new ArrayList<Automaton>();
		int maxMinReset= -1;
		int[] gtable = new int[n];
		int[] ftable;
		ArrayList<Integer> f;
		int count = 0;
		for (int i=0; i<nn; i++ ) {
			gtable = numToFun(i, n);

			//if (rank(gtable) > 1 && rank(gtable)<n) { 
			if (rank(gtable)==rnk) { 
			for (ICombinatoricsVector<Integer> perm : generator) {
				f = (ArrayList<Integer>) perm.getVector();
				ftable = toIntArray(f);

				Operation fOp = Operations.makeIntOperation("f",1, n, ftable);
				Operation gOp = Operations.makeIntOperation("g", 1, n, gtable);

				// Build unary algebra using f and g as operations.
				List<Operation> unaryOps = new ArrayList<Operation>();
				unaryOps.add(fOp);
				unaryOps.add(gOp);

				System.out.println();
				System.out.println("======== Automaton " + count++ + " ========");

				SmallAlgebra automaton = new BasicAlgebra("automaton", n, unaryOps);
				ProgressReport pr = new ProgressReport();
				FreeAlgebra f1  = new FreeAlgebra(automaton, 1, true, false, pr);
				    
				System.out.println("|F(1)| = " + f1.cardinality());
				//Set<IntArray> uni = f1.universe();
	//			java.util.List<IntArray> uni = f1.getUniverseList();
				//				Term[] terms = f1.getTerms();
				java.util.Map<IntArray,Term> tmap =f1.getTermMap();

				// Print out basic operations (i.e. the letters of the alphabet)
				System.out.print("letters: ");
				System.out.print("(");
				for (int j=0; j<n-1; j++){ System.out.print(gtable[j] + ", "); }
				System.out.print(gtable[n-1] + "),  (");
				for (int j=0; j<n-1; j++){ System.out.print(ftable[j] + ", "); }
				System.out.print(ftable[n-1] + ")");

				System.out.println();
				
				ArrayList<Term> constantTerms = getConstantTerms(n, tmap);
				Term minResetWord = null;
				if (constantTerms==null) {
					System.out.println("not synchronizing");
				}
				else {
					System.out.print("\nReset words:  ");
					int minReset=-1;
					for (Term term : constantTerms) {
						if (minReset==-1 || term.length()-1<minReset) {
							minReset = term.length()-1;
							minResetWord = term;
						}
						// Print constant terms:
						System.out.print(term.toString() + ",  ");
					}
					if (minReset==czernyBound) {
						savedAutomata.add(new Automaton(n, unaryOps, minResetWord, minReset, true));
					}

					System.out.print("\n\nMin reset length: " + minReset);
					if (maxMinReset==-1 || maxMinReset < minReset) {
						maxMinReset=minReset;
						if (maxMinReset > czernyBound) {
							System.out.println("POSSIBLE VOILATION OF CZERNY CONJECTURE");
							return;
						}
					}
					System.out.println("      MaxMin reset length: " + maxMinReset);
				}

			}
			}
		}
		System.out.println("\n======================  SUMMARY  =======================");
		System.out.print("\nMAX MIN RESET WORD LENGTH: " + maxMinReset);
		System.out.println("   Czerny number: (n-1)^2 = " + czernyBound);
		if (czernyBound!=maxMinReset) {
			System.out.println("POSSIBLE VIOLATION OF CZERNY CONJECTURE");
		}
		System.out.println("\nSlowly synchronizing automata:\n");
		//int num = 0;
		int cnt = 1;
		for (Automaton a : savedAutomata) {
			//num++;
			if (a.getMinResetWordLength() == czernyBound) {
				System.out.println(Integer.toString(cnt++) + "."); // (" + Integer.toString(num) + ")");
				System.out.println(a.toString());
			}
		}

	}
	
	
	private static ArrayList<Term> getConstantTerms(int n, Map<IntArray, Term> tmap) {
		ArrayList<Term> constantTerms = new ArrayList<Term>();
		boolean synchronizing = false;
		// store all constant functions as IntArrays
		int[] c = new int[n];
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				c[j]=i;
			}
			Term term = tmap.get(new IntArray(c));
			if (term !=null) {
				synchronizing=true;
				constantTerms.add(term);
			}
		}
		if (!synchronizing) {
			constantTerms=null;
		}
		return constantTerms;
	}
	
	public static List<Term> getConstantTerms(FreeAlgebra free) {
		List<Term> ans = new ArrayList<Term>();
		Term[] terms = free.getTerms();
		int n = free.generators().size();
		List<Variable> varlist = new ArrayList<Variable>(n);
		for (int i = 0 ; i < n; i++) {
			varlist.add((Variable)terms[i]);// the first n are Variables
			ans.add(terms[i]);
		}
		for (int i = n; i < terms.length; i++) {
			Term t = terms[i];
			Operation op = t.interpretation(free, varlist, true);
			if (isConstant(op)) ans.add(t);
		}
		return ans;
	}
    
}

