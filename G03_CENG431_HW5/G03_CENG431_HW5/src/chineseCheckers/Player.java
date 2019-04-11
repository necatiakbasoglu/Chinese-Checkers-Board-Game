package chineseCheckers;

import java.util.ArrayList;
import java.util.List;

import boardElements.Coordinate;

public class Player {
	private String name;
	private String symbol;
	private List<Coordinate> finishedGameCooordinates; // oyunun bitip bitmedigini anlamak icin
	
	public Player(String name, String symbol) {
		finishedGameCooordinates = new ArrayList<Coordinate>();
		setName(name);
		setSymbol(symbol);
	}
	
	public void setFinishCoordinates(List<Coordinate> cords) {
		this.finishedGameCooordinates = cords;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	public void move(Coordinate destination,Coordinate source) {
		//board.move()
	}
*/
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return "["+symbol+"]"+name;
	}
	
	public List<Coordinate> getFinishCoordinates(){
		return finishedGameCooordinates;
	}
}
