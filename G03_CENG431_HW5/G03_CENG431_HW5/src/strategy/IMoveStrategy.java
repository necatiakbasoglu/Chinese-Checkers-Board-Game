package strategy;

import board.Board;
import boardElements.Coordinate;

public interface IMoveStrategy {
	public boolean move(Board onBoard, Coordinate source, Coordinate destination);
}
