package boardElements;

public class Coordinate {
	private char[] alphabet = "abcdefghýjklmnopqrstuvwxy".toUpperCase().toCharArray();
	private int row;
	private int column;
	
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Coordinate(int x, String y) {
		this.row = x-1;
		if(y.equals("i")||y.equals("Ý"))
			y = "I";
		this.column = findIndex(y.toUpperCase());
	}
	
	private int findIndex(String s) {
		for(int i=0;i<alphabet.length;i++) {
			if(String.valueOf(alphabet[i]).equals(s))
				return i;
		}
		return -1;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int x) {
		this.row = x;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int y) {
		this.column = y; 
	}
	
	public Coordinate sum(Coordinate cord) {
		return new Coordinate( (row + cord.getRow()) , (column + cord.getColumn()) );
	}
	
	public String toString() {
		return "( Row : " + row + " | " + "Column : " + column+" )";
	}
	
	public boolean isEqual(Coordinate cord) {
		if(cord.getRow()==row && cord.getColumn()==column)
			return true;
		return false;
	}
}
