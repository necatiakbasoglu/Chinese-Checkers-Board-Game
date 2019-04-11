package board;

import java.util.ArrayList;
import java.util.List;

import boardElements.Coordinate;
import boardElements.Piece;
import chineseCheckers.*;

public class BoardHelper {
	private Board board;

	private List<Coordinate> middleUpperInitialCoordinate;
	private List<Coordinate> middleLowerInitialCoordinate;
	private List<Coordinate> rightUpperInitialCoordinate;
	private List<Coordinate> rightLowerInitialCoordinate;
	private List<Coordinate> leftUpperInitialCoordinate;
	private List<Coordinate> leftLowerInitialCoordinate;
	
	public BoardHelper(Board board) {
		this.board = board;
		middleUpperInitialCoordinate = new ArrayList<Coordinate>();
		middleLowerInitialCoordinate = new ArrayList<Coordinate>();
		rightUpperInitialCoordinate = new ArrayList<Coordinate>();
		rightLowerInitialCoordinate = new ArrayList<Coordinate>();
		leftUpperInitialCoordinate = new ArrayList<Coordinate>();
		leftLowerInitialCoordinate = new ArrayList<Coordinate>();
		fill();
		
	}
	
	
	
	public void addHoleElementFromList(List<Coordinate> coordinateList, String Symbol) {
		for(Coordinate cord : coordinateList)
			board.addHoleElement(cord,new Piece(Symbol));
	}
	
	public void initilizeBoard(List<Player> players) {
		
		if(players.size()==2) {
			addHoleElementFromList(middleUpperInitialCoordinate, players.get(0).getSymbol());// 1.Oyuncunun taslari
			players.get(0).setFinishCoordinates(middleLowerInitialCoordinate); // 1.Oyuncunun kazanabilmesi icin gereken kordinatlar
																		 	   // Oyun 1.oyuncunun taslarinin bu kordinatlarda olmasiyla biter
			addHoleElementFromList(middleLowerInitialCoordinate, players.get(1).getSymbol()); // 2.Oyuncu
			players.get(1).setFinishCoordinates(middleUpperInitialCoordinate);
			
			
		}else if(players.size()==3) {
			addHoleElementFromList(leftUpperInitialCoordinate, players.get(0).getSymbol());
			players.get(0).setFinishCoordinates(rightLowerInitialCoordinate);
			
			addHoleElementFromList(rightUpperInitialCoordinate, players.get(1).getSymbol());
			players.get(1).setFinishCoordinates(leftLowerInitialCoordinate);
			
			addHoleElementFromList(middleLowerInitialCoordinate, players.get(2).getSymbol());
			players.get(2).setFinishCoordinates(middleUpperInitialCoordinate);
			
		}else if(players.size()==4) {
			addHoleElementFromList(leftUpperInitialCoordinate, players.get(0).getSymbol());
			players.get(0).setFinishCoordinates(rightLowerInitialCoordinate);
			
			addHoleElementFromList(rightUpperInitialCoordinate, players.get(1).getSymbol());
			players.get(1).setFinishCoordinates(leftLowerInitialCoordinate);
			
			addHoleElementFromList(leftLowerInitialCoordinate, players.get(2).getSymbol());
			players.get(2).setFinishCoordinates(rightUpperInitialCoordinate);
			
			addHoleElementFromList(rightLowerInitialCoordinate, players.get(3).getSymbol());
			players.get(3).setFinishCoordinates(leftUpperInitialCoordinate);	
		}
		else if(players.size()==6) {
			addHoleElementFromList(middleUpperInitialCoordinate,players.get(0).getSymbol());
			players.get(0).setFinishCoordinates(middleLowerInitialCoordinate);
			
			addHoleElementFromList(middleLowerInitialCoordinate,players.get(1).getSymbol());
			players.get(1).setFinishCoordinates(middleUpperInitialCoordinate);
			
			addHoleElementFromList(rightUpperInitialCoordinate,players.get(2).getSymbol());
			players.get(2).setFinishCoordinates(leftLowerInitialCoordinate);
			
			addHoleElementFromList(rightLowerInitialCoordinate,players.get(3).getSymbol());
			players.get(3).setFinishCoordinates(leftUpperInitialCoordinate);
			
			addHoleElementFromList(leftUpperInitialCoordinate,players.get(4).getSymbol());
			players.get(4).setFinishCoordinates(rightLowerInitialCoordinate);
			
			addHoleElementFromList(leftLowerInitialCoordinate,players.get(5).getSymbol());
			players.get(5).setFinishCoordinates(rightUpperInitialCoordinate);
		}
		
	}
	
	private void fill() {
		//middleUpper
		addCoordinate(0, 12, 13,middleUpperInitialCoordinate);
		addCoordinate(1, 11, 14,middleUpperInitialCoordinate);
		addCoordinate(2, 10, 15,middleUpperInitialCoordinate);
		addCoordinate(3, 9, 16,middleUpperInitialCoordinate);
		//
		addCoordinate(4,0, 7, leftUpperInitialCoordinate);
		addCoordinate(5, 1, 6,leftUpperInitialCoordinate);
		addCoordinate(6, 2, 5,leftUpperInitialCoordinate);
		addCoordinate(7, 3, 4,leftUpperInitialCoordinate);
		//
		addCoordinate(4,18, 25,rightUpperInitialCoordinate);
		addCoordinate(5,19, 24,rightUpperInitialCoordinate);
		addCoordinate(6,20, 23,rightUpperInitialCoordinate);
		addCoordinate(7,21, 22,rightUpperInitialCoordinate);
		//
		//left lower
		addCoordinate(9,3, 4, leftLowerInitialCoordinate);
		addCoordinate(10,2, 5,leftLowerInitialCoordinate);
		addCoordinate(11, 1, 6,leftLowerInitialCoordinate);
		addCoordinate(12, 0, 7,leftLowerInitialCoordinate);
		//
		//rightLower
		addCoordinate(9,21,22, rightLowerInitialCoordinate);
		addCoordinate(10,20,23,rightLowerInitialCoordinate);
		addCoordinate(11,19,24,rightLowerInitialCoordinate);
		addCoordinate(12,18,25,rightLowerInitialCoordinate);
		//
		//middleLower
		addCoordinate(16, 12, 13,middleLowerInitialCoordinate);
		addCoordinate(15, 11, 14,middleLowerInitialCoordinate);
		addCoordinate(14, 10, 15,middleLowerInitialCoordinate);
		addCoordinate(13, 9, 16,middleLowerInitialCoordinate);
		
	}
	
	private void addCoordinate(int row,int lowerIndex,int upperIndex,List<Coordinate> coordinates) {
		for(int i=lowerIndex; i<upperIndex; i++) {
			if((row%2 ==0 && i%2==0) || (row%2!=0 && i%2!=0) )
				coordinates.add(new Coordinate(row,i));
		}
	}

}
