package board;

import java.util.ArrayList;
import java.util.List;

import boardElements.Coordinate;
import boardElements.Hole;
import boardElements.IHoleElement;
import boardElements.Piece;
import chineseCheckers.*;
import strategy.IMoveStrategy;
import visitor.IVisitor;


/**
 * board uzerinde sadece oyunun oynanabildigi yildiz seklindeki yerde hole objeleri var, diger alanlar null
 * hole objesi HoleElement(piece) objesini tutabiliyor 
 *
 */


public class Board {
	private Hole[][] holesOnBoard;
	private char[] alphabet = "abcdefghýjklmnopqrstuvwxy".toUpperCase().toCharArray();
	private IMoveStrategy moveStrategy;
	
	public Board() {
		holesOnBoard = new Hole[17][25];
		addAllHoles();
	}
	
	public void addHoleElement(Coordinate coordinate,IHoleElement holeElement) {
		holesOnBoard[coordinate.getRow()][coordinate.getColumn()].setHoleElement(holeElement);
	}
	
	public IHoleElement getHoleElement(Coordinate cord) {
		if(isSelectableCoordinate(cord)) 
			return holesOnBoard[cord.getRow()][cord.getColumn()].getHoleElement();
		else
			return null;
		
	}
	
	public void printBoard() {
		heplerPrint();
		System.out.println("");
		for(int i=0; i<17; i++) {
			if(i+1<10)
				System.out.print(i+1+" : ");
			else
				System.out.print(i+1+": ");
			for(int j=0; j<25; j++) {
				if(holesOnBoard[i][j]!=null) 								//board da hole varsa onun sembolunu getir ve yazdýr
					System.out.print(holesOnBoard[i][j].getSymbol()+" ");
				else
					System.out.print("  ");
			}
			System.out.println("");
		}
	}

	
	private void add(Hole hole) {
		Coordinate coor = hole.getCoordinate();
		holesOnBoard[coor.getRow()][coor.getColumn()] = hole;
	}
	
	private void addAllHoles() {
		helperForHoleAdding(0, 12, 13);
		helperForHoleAdding(1, 11, 14);
		helperForHoleAdding(2, 10, 15);
		helperForHoleAdding(3, 9, 16);
		//
		helperForHoleAdding(4, 0, 25);
		helperForHoleAdding(5, 1, 24);
		helperForHoleAdding(6, 2, 23);
		helperForHoleAdding(7, 3, 22);
		helperForHoleAdding(8, 4, 21);
		//
		helperForHoleAdding(9, 3, 22);
		helperForHoleAdding(10, 2, 23);
		helperForHoleAdding(11, 1, 24);
		helperForHoleAdding(12, 0, 25);
		//
		helperForHoleAdding(13, 9, 16);
		helperForHoleAdding(14, 10, 15);
		helperForHoleAdding(15, 11, 14);
		helperForHoleAdding(16, 12, 13);
	}
	
	
	private void helperForHoleAdding(int row,int low,int high) {
		for(int i=low; i<high; i++) {
			if((row%2 ==0 && i%2==0) || (row%2!=0 && i%2!=0) )
			add(new Hole(new Coordinate(row, i)));
		}
	}
	
	
	public void heplerPrint() {
		System.out.print("    ");
		for(int i=0; i<25; i++) {
			System.out.print(alphabet[i]+" ");
		}
	}
	
	
	public boolean isSelectedCoordinateEmpty(Coordinate cord) {
		boolean flag=false;
			if(holesOnBoard[cord.getRow()][cord.getColumn()].getHoleElement()==null) { // hole içerisinde taþ yoksa
				flag = true;
			}
		return flag;
	}
	
	
	public boolean isSelectableCoordinate(Coordinate cord) { // seçilen kordinat board içinde mi
		boolean flag=false;
		if(cord.getRow()>=0 && cord.getColumn()>=0 && cord.getRow()<17 && cord.getColumn()<25) {
			if(holesOnBoard[cord.getRow()][cord.getColumn()]!=null) {
				flag = true;
			}
		}
		return flag;
	}
	
	
	public List<Coordinate> getCoordinateListFromAround(Coordinate cord){  // verilen kordinatin etrafindaki gecerli kordinat listesi
		List<Coordinate> cords = new ArrayList<Coordinate>();
		Coordinate left = new Coordinate(cord.getRow(), cord.getColumn()-2);
		Coordinate right = new Coordinate(cord.getRow(), cord.getColumn()+2);
		Coordinate topLeft = new Coordinate(cord.getRow()-1, cord.getColumn()-1);
		Coordinate bottomLeft = new Coordinate(cord.getRow()+1, cord.getColumn()-1);
		Coordinate topRight = new Coordinate(cord.getRow()-1, cord.getColumn()+1);
		Coordinate bottomRight = new Coordinate(cord.getRow()+1, cord.getColumn()+1);
		if(isSelectableCoordinate(left))
			cords.add(left);
		if(isSelectableCoordinate(right))
			cords.add(right);
		if(isSelectableCoordinate(topLeft))
			cords.add(topLeft);
		if(isSelectableCoordinate(bottomLeft))
			cords.add(bottomLeft);
		if(isSelectableCoordinate(topRight))
			cords.add(topRight);
		if(isSelectableCoordinate(bottomRight))
			cords.add(bottomRight);
		return cords;
	}
	
	
	public boolean isThereEmptyHoleAroundGivenCoordinate(Coordinate cord) {
		boolean flag = false;
		//System.out.println("isThereEmptyHoleAroundGivenCoordinate");
		for(Coordinate c : getCoordinateListFromAround(cord)) {
			
			if(isSelectableCoordinate(c) && isSelectedCoordinateEmpty(c)) {
			//if(isSelectableCoordinate(c) && visit(new Visitor(),c)) {

				//System.out.println(c.getRow()+" : "+c.getColumn());
				flag = true;
				addHoleElement(c, new Piece("&"));
			}
		}

		return flag;
	}

	

	
	public void setStrategy(IMoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}
	
	
	public boolean isPlayerHasThisPiece(Player player, Coordinate cord) {
		if(getHoleElement(cord).getSymbol().equals(player.getSymbol()))
			return true;
		else
			return false;
	}
	
	
	
	public boolean movePiece(Coordinate source, Coordinate destination) {
		//moveStrategy.move(this, source, destination);
		return moveStrategy.move(this, source, destination);
	}	
	
	public boolean visit(IVisitor visitor, Coordinate cord) {
		boolean flag = false;

		if(isSelectableCoordinate(cord))
			flag = holesOnBoard[cord.getRow()][cord.getColumn()].accept(visitor);
				
		return flag;
	}
	
	public IMoveStrategy getStrategy() {
		return moveStrategy;
	}
	
}
