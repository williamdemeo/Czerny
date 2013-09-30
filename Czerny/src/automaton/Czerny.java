package automaton;


import org.uacalc.io.*;
import org.uacalc.util.IntArray;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import org.uacalc.alg.*;
import org.uacalc.alg.conlat.*;
import org.uacalc.alg.op.Operation;
import org.uacalc.alg.op.OperationSymbol;
import org.uacalc.alg.op.Operations;

import java.io.*;
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
		int nn = (int) java.lang.Math.pow(n,n);

		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) arr[i] = i; 

		ICombinatoricsVector<Integer> initialVector = Factory.createVector(arr);
		ICombinatoricsVector<Integer> idem;

		Generator<Integer> generator = Factory.createPermutationGenerator(initialVector);

		int[] h = new int[n];
		List<Integer> f;
		List<Integer> g;
		int[] ftable;

		for (int i=0; i<nn; i++ ) {
			h = numToFun(i, n);
			//idem = Factory.createVector(h);
			//g = idem.getVector();

			for (ICombinatoricsVector<Integer> perm : generator) {
				f = perm.getVector();
				ftable = toIntArray(f);

				//ftable = ArrayUtils.toPrimitive(f.toArray(new Integer[f.size()]));
				//Operation fOp = Operations.makeIntOperation(new OperationSymbol("f",1), n, f);

				Operation fOp = Operations.makeIntOperation("f",1, n, ftable);
				//Operation gOp = Operations.makeIntOperation(new OperationSymbol("g",1), n, g);
				Operation gOp = Operations.makeIntOperation("g", 1, n, h);

				// Build unary algebra using f and g as operations.
				List<Operation> unaryOps = new ArrayList<Operation>();
				unaryOps.add(fOp);
				unaryOps.add(gOp);

				// Check that unaryOps contains what we think it should:
				Iterator<Operation> iter = unaryOps.iterator();
				System.out.print("ops: ");
				while (iter.hasNext())
				{		 
					Operation opi = iter.next();
					System.out.print("(");
					for (int j=0; j<n-1; j++){
						System.out.print(opi.intValueAt(j) + ", ");	  
					}
					System.out.print(opi.intValueAt(n-1) + "),  ");
				}
				System.out.println();

				SmallAlgebra automaton = new BasicAlgebra("automaton", n, unaryOps);

				FreeAlgebra f1 = new FreeAlgebra("czerny5", automaton, 1, true, false);
				System.out.println("|F(1)| = " + f1.cardinality());
				Set<IntArray> uni = f1.universe();
				int k = 0;
				for (IntArray u : uni) {
					System.out.print(k++ + ": ");
					System.out.println(u);
				}

			}


		}
	}
}

