package chineseCheckers;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.BoardHelper;
import boardElements.Coordinate;
import boardElements.Piece;
import presentationLayer.View;
import strategy.JumpMove;
import strategy.NoMove;
import strategy.SingleMove;
import visitor.Visitor;

public class Game {
	private Board gameBoard;
	private BoardHelper boardHelper;
	private View view;
	private List<Player> players;
	private List<String> symbols;

	boolean jump = false;
	private int turnPlayerIndex;
	
	
	public Game() {
		symbols = new ArrayList<String>();
		symbols.add("#");symbols.add("Ø");symbols.add("@");symbols.add("¤");symbols.add("$");symbols.add("&");
		view = new View();
		players = new ArrayList<Player>();
		gameBoard = new Board();
		boardHelper = new BoardHelper(gameBoard);

		
		
	/* --------------------------------------------------------------------	test for finishing play	
	 *												test oncesi yukaridaki createPlayers(); comment yapilmali*/
	 	
		Player p1 = new Player("Kaan", "A");
		Player p2 = new Player("Necati", "¤");		
		addPlayer(p1);
		addPlayer(p2);
		//boardHelper.initilizeBoard(players);
		gameBoard.addHoleElement(new Coordinate(1, "M"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(2, "L"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(2, "N"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(3, "K"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(3, "M"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(3, "O"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(4, "J"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(4, "L"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(4, "N"), new Piece("¤"));
		gameBoard.addHoleElement(new Coordinate(4, "P"), null);
		gameBoard.addHoleElement(new Coordinate(5, "Q"), new Piece("¤"));
		
		
		//gameBoard.addHoleElement(new Coordinate(5, "A"), new Piece("¤"));
		
		
		//getMoveFromUser();
		
	}
	

	
	private Coordinate getCorrectSourceCoordinateFromUser(Player player) {
		int flag = 1;
		Coordinate cord=null;
		while(flag>0) {
			flag = 0;
			cord = view.getCoordinate("------ Enter SOURCE piece coordinate ------");
			//kordinati dogru secti mi
			if(!gameBoard.isSelectableCoordinate(cord)) {
				view.displayError("Wrong coordinate please enter correct coordinate!!!\n");
				flag++;
			}
			// secilen kordinatta tas var mi
			else if(!gameBoard.visit(new Visitor(), cord)) { // secilen yerin dolu olmasi gerek
				view.displayError("There is not a piece on this coordinate please select piece!!!\n");
				flag++;			
			}
			// kullanici kendi tasini mi secti
			else if(!gameBoard.isPlayerHasThisPiece(player, cord)) {
				view.displayError("This is not your piece please select YOUR "+player.getSymbol()+" piece!!!\n");
				flag++;
			}
		
		}	
		return cord;
	}
	
	private Coordinate getCorrectDestinationCoordinateFromUser() {
		int flag = 1;
		Coordinate cord=null;
		while(flag>0) {
			flag = 0;
			cord = view.getCoordinate("------ Enter DESTINATION piece coordinate ------");
			
			if(!gameBoard.isSelectableCoordinate(cord)) {
				view.displayError("Wrong coordinate please enter correct coordinate!!!\n");
				flag++;
			}				
			else if(!jump && gameBoard.visit(new Visitor(), cord)) { // visit piece to empty or not // bos yer secilmeli
				view.displayError("There is a piece on this coordinate please select empty one!!!\n");
				flag++;
			}
		}
		return cord;
	}
	
	private void init() {
		//createPlayers();
		//boardHelper.initilizeBoard(players);
		displayPlayers();
		turnPlayerIndex = 0;		
	}
	
	public void start() {
		init();
		Coordinate selectedSourceCord = null;
		
		while(!isGameEnd()){	
		
			gameBoard.printBoard();

			view.displayTurn(players.get(turnPlayerIndex));
			
			
			if(selectedSourceCord==null)
				selectedSourceCord = getCorrectSourceCoordinateFromUser(players.get(turnPlayerIndex));

			Coordinate selectedDestinationCord = getCorrectDestinationCoordinateFromUser();

			if(setStrategy(selectedSourceCord, selectedDestinationCord)) {  // strateji belirlenebildi mi

				if(gameBoard.movePiece(selectedSourceCord,selectedDestinationCord)) { // true donerse jump yapilmis
					turnPlayerIndex -= 1; // tekrar ayni oyuncuya oynama hakki verilmesi icin
					selectedSourceCord = new Coordinate(selectedDestinationCord.getRow(), selectedDestinationCord.getColumn());
					jump = true;
				}else {
					jump = false;
					selectedSourceCord=null;   // tek hamle hakki var
					
				}
					
			}else {
				view.displayError("Your choice is wrong! please try again!");
				turnPlayerIndex-=1;
			}
			changeTurn();
			
		}
		
	}
	
	
	private boolean isGameEnd() {
		int counter=0;
		for(Player p : players) {
			counter=0;
			for(Coordinate c : p.getFinishCoordinates()) {
				if(gameBoard.visit(new Visitor(), c))
					if(gameBoard.isPlayerHasThisPiece(p, c)) {
						counter++;
					}
			}
			if(counter==10) {
				gameBoard.printBoard();
				view.displayInformation("\n\n------------------------- Game is End! -------------------------\n");
				view.displayInformation("WINNER : " + p);
				return true;
			}
			
		}
		return false;
	}
	
	private boolean setStrategy(Coordinate source, Coordinate destination) { // strateji kordinat farkina bakilarak karar veriliyor
		boolean flag = false;
		int rowDifference, columnDifference;
		rowDifference = Math.abs(destination.getRow() - source.getRow());
		columnDifference = Math.abs(destination.getColumn()- source.getColumn());
		
		if((rowDifference == 1 && columnDifference ==1) || (rowDifference == 0 && columnDifference == 2)) { 
			if(jump) {	// jump yapilmissa tek hamle yapilamaz
				gameBoard.setStrategy(null);
				flag = false;
			}
			else {
				gameBoard.setStrategy(new SingleMove());
				flag = true;
			}
				
		}else if((rowDifference == 2 && columnDifference == 2) || (rowDifference == 0 && columnDifference == 4)) {
			gameBoard.setStrategy(new JumpMove());
			flag = true;
		}
		else if(jump) {
			if(source.isEqual(destination)) {
				//jump dan sonra ayný yeri secerse move yapma
				gameBoard.setStrategy(new NoMove());
				flag = true;
			}else {
				gameBoard.setStrategy(null);
				flag = false;
			}
		}
		else {
				gameBoard.setStrategy(null);
				flag = false;	
		}
		return flag;	
	}
	
	
	
	private void displayPlayers() {
		for(Player p : players)
			view.displayPlayer(p);
	}
	
	private void createPlayers() {
		int amountOfPlayer = view.getPlayerCount();
		while(!(amountOfPlayer==2 || amountOfPlayer==3 || amountOfPlayer==4 || amountOfPlayer==6)) {
			view.displayError("Select 2, 3, 4 or 6 players\n");
			amountOfPlayer = view.getPlayerCount();
		}
		
		for(int i=0; i<amountOfPlayer; i++) 
			addPlayer(new Player(view.getPlayerName(i+1),symbols.get(i)));
	}
	
	private void addPlayer(Player player) {
		players.add(player);
	}
	
	private void changeTurn() {
		if(turnPlayerIndex < players.size()-1)
			turnPlayerIndex+=1;
		else
			turnPlayerIndex = 0;
	}
	
}
