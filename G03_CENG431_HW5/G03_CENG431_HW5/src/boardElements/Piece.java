package boardElements;

public class Piece implements IHoleElement{
	private String symbol ;
	private Coordinate coordinate;
	
	public Piece(String symbol) {
		this.symbol = symbol;
		//this.coordinate = coordinate;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Coordinate getCoordinat() {
		return coordinate;
	}

	public void setCoordinat(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public boolean isCoordinat(int row,int column) {
		if(coordinate.getRow()==row && coordinate.getColumn()==column)
			return true;
		return false;
	}
}
