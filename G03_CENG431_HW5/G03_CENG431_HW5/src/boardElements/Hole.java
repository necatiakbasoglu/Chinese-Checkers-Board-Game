package boardElements;

import visitor.IVisitor;

public class Hole {
	
	private IHoleElement holeElement;
	private Coordinate coordinate;
	private String defaultSymbol; 
	public Hole(Coordinate coordinate) {
		holeElement=null;
		defaultSymbol ="O";
		this.setCoordinate(coordinate);
	}
	
	public String getSymbol() {
		if(holeElement!=null)   			//eger holun içi dolu ise tasýn sembolunu getir degilse default olaný kullan
			return holeElement.getSymbol();
		return defaultSymbol;
	}
	
	public IHoleElement getHoleElement() {
		return holeElement;
	}

	public void setHoleElement(IHoleElement holeElement) {
		this.holeElement = holeElement;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public boolean accept(IVisitor visitor) {
		return visitor.visit(this);
	}
	
	
}
