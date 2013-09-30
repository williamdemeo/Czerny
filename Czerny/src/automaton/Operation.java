package automaton;

public class Operation {
	private String symbol;
	private int[] table;
	private int domain;  // a non-negative integer
	
	public int getDomain() {
		return domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public void Operation(String symbol) {
		setSymbol(symbol);
	}
	
	public void Operation(String symbol, int[] table) {
		setSymbol(symbol);
		setTable(table);
	}
	
	public String getSymbol() {return symbol;}
	public void setSymbol(String symbol) {this.symbol = symbol;}

	public int[] getTable() {return table;}
	public void setTable(int[] table) {this.table = table;}
	
	public int getValueAt(int x){ return table[x];}
	
}
