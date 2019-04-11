package strategy;

import board.Board;
import boardElements.Coordinate;

public class NoMove implements IMoveStrategy {

	@Override
	public boolean move(Board onBoard, Coordinate source, Coordinate destination) {
		// oyuncu jump yaptiginda ya jump yaptigi kordinatta kalir
		// ya da mumkunse tekrar jump yapabilir
		
		// NoMove ayni kordinatta kaldigi zaman calisir ve hamle yapmaz
		return false;
	}

}
