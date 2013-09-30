package automaton;

import java.util.ArrayList;

public class Automaton {

	private String name;
	private int size;
	private int state;  // the current state
	private ArrayList<Operation> alphabet;
	private Boolean isSynchronizing;
	private ArrayList<Operation> resetWord;
	
	public Automaton(String name, int size, int state,
			ArrayList<Operation> alphabet, Boolean isSynchronizing,
			ArrayList<Operation> resetWord) {
		super();
		this.name = name;
		this.size = size;
		this.state = state;
		this.alphabet = alphabet;
		this.isSynchronizing = isSynchronizing;
		this.resetWord = resetWord;
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getSize() { return size;	}
	public void setSize(int size) { this.size = size; }

	public int getState() {	return state; }
	public void setState(int state) { this.state = state; }

	public ArrayList<Operation> getAlphabet() { return alphabet; }
	public void setAlphabet(ArrayList<Operation> sigma) { this.alphabet = sigma; }


	public Boolean getIsSynchronizing() {
		return isSynchronizing;
	}

	public void setIsSynchronizing(Boolean isSynchronizing) {
		this.isSynchronizing = isSynchronizing;
	}

	public ArrayList<Operation> getResetWord() {
		return resetWord;
	}

	public void setResetWord(ArrayList<Operation> resetWord) {
		this.resetWord = resetWord;
	}
	
	public Boolean transition(Operation f) throws IndexOutOfBoundsException {
		if (state > -1 && state < f.getDomain()) {
			setState(f.getValueAt(state));
			return true;
		} else  {
			throw new IndexOutOfBoundsException("Current state is outside domain of the given transition function.");
		}
		
	}
	
	
	
}
