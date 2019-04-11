package strategy;

import board.Board;
import boardElements.Coordinate;

public class SingleMove implements IMoveStrategy {

	@Override
	public boolean move(Board onBoard, Coordinate source, Coordinate destination) {
		//System.out.println("------- SingleMove -------");
		
		onBoard.addHoleElement(destination, onBoard.getHoleElement(source));
		onBoard.addHoleElement(source , null);
		return false;
		// hep false doner cunku oyuncu jump yapmamistir ve tek oynama hakki vardir
	}

}
