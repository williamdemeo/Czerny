package automaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.uacalc.alg.op.Operation;
import org.uacalc.alg.op.Operations;
import org.uacalc.terms.Term;
import org.uacalc.util.IntArray;

public class Automaton {
	
	public class AlgebraSizeException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3202170856421987935L;

		public AlgebraSizeException(String message) {
			super(message);
		}
	}
	
	String name;
	int size;
	ArrayList<Operation> letters;
	Set<IntArray> words;
	boolean isSynchronizing;
	Term minResetWord;
	int minResetWordLength;

	public Automaton(String name) {
		this.name = name;
		letters = new ArrayList<Operation>();
		words = null;
		minResetWordLength=-1;
	}

	public Automaton(String name, int size) {
		this.name = name;
		this.size = size;
		letters = new ArrayList<Operation>();
		words = null;
		minResetWordLength=-1;
	}

	public Automaton(int n, List<Operation> unaryOps, Term minResetWord, int minReset, boolean sync) {
		this.name = "unset";
		this.size = n;
		this.letters = (ArrayList<Operation>) unaryOps;
		this.minResetWord = minResetWord;
		this.minResetWordLength = minReset;
		isSynchronizing=sync;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSynchronizing() {
		return isSynchronizing;
	}

	public void setSynchronizing(boolean isSynchronizing) {
		this.isSynchronizing = isSynchronizing;
	}

	public ArrayList<Operation> getLetters() {
		return letters;
	}

	public Set<IntArray> getWords() {
		return words;
	}

	public boolean addLetter(int n, int[] f) throws AlgebraSizeException {
		//String name = Integer.toString(this.letters.size());
		// TODO implement this method
		return false;
		
	}

	public boolean addLetter(String name, int n, int[] f) throws AlgebraSizeException {
		if (n < getSize()){
			throw new AlgebraSizeException("Operation is not of the correction size.");
		} else if (n > getSize()) {
			System.out.println("only using first" + getSize() + "elements of f"); 
		}
		return this.letters.add(Operations.makeIntOperation(name,1, getSize(), f));
	}
	
	public Operation getLetterOperation(int k) {
		return this.letters.get(k);
	}
	public int[] getLetterTable(int k) {
		Operation fOp = getLetterOperation(k);
		return fOp.getTable();
		
	}

	public int getMinResetWordLength() {
		return minResetWordLength;
	}
	
	public String toString(){
		String description = new String();
		int[] table;
//		description = "name: " + this.name 
//				+ "\nletters: ";
		description = "letters: ";
		for (Operation letter : letters) {
			table = letter.getTable();
			description = description + "(";
			int j = 0;
			for (; j<(table.length-1); j++){ 
				description = description + Integer.toString(table[j]) + ", ";
			}
			description = description + Integer.toString(table[j]) + "), ";
		}
		description = description + "\nmin reset word: " + minResetWord.toString();
		description = description + "   (length " + Integer.toString(minResetWordLength) + ")\n";

		return description;
	}
	
	
}
