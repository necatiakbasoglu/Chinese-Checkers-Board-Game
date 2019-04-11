package strategy;

import java.util.List;

import board.Board;
import boardElements.Coordinate;

public class JumpMove implements IMoveStrategy {



	@Override
	public boolean move(Board onBoard, Coordinate source, Coordinate destination) {
		//System.out.println("------- JumpMove -------");
		int differenceRow, differenceColumn;
		Coordinate jumpableCoord;
		
		onBoard.addHoleElement(destination, onBoard.getHoleElement(source));
		onBoard.addHoleElement(source , null);
		
		List<Coordinate> cordsAroundDestination = onBoard.getCoordinateListFromAround(destination);
		
		differenceRow = destination.getRow() - source.getRow();
		differenceColumn = destination.getColumn() - source.getColumn();
		Coordinate jumpedPieceCord = new Coordinate(differenceRow/2,differenceColumn/2);
		jumpedPieceCord = source.sum(jumpedPieceCord);
		
		removeCord(cordsAroundDestination,jumpedPieceCord); // ustunden atladigi tasa bakmamasi icin

		for(Coordinate c : cordsAroundDestination) {
			if(onBoard.getHoleElement(c)!=null) { // destination etrafindaki tas olan kordinatlar
				differenceRow = c.getRow() - destination.getRow();
				differenceColumn = c.getColumn() - destination.getColumn();
				
				jumpableCoord = c.sum(new Coordinate(differenceRow,differenceColumn));
				
				if(onBoard.isSelectableCoordinate(jumpableCoord))
					if(onBoard.getHoleElement(jumpableCoord)==null)   // atlanabilecek yerde tas yoksa
						return true;	
			}
		}		
		
		return false;
	}
	

	private void removeCord(List<Coordinate> cords, Coordinate removing) {
		for(int i=0;i<cords.size();i++)
			if(cords.get(i).getRow() == removing.getRow() && cords.get(i).getColumn() == removing.getColumn())
				cords.remove(i);
	}

}
